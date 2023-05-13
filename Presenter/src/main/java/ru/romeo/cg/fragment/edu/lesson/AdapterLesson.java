package ru.romeo.cg.fragment.edu.lesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.graphics.drawable.DrawableWrapperCompat;
import androidx.recyclerview.widget.ListAdapter;

import ru.romeo.cg.R;
import ru.romeo.cg.fragment.edu.chapter.Adapter;
import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.Lesson;

public class AdapterLesson extends ListAdapter<Lesson, LessonViewHolder> {

    public static final int MAX_POOL_SIZE = 10;
    public static final int VIEW_TYPE_STYLE_0 = 0;
    private final Chapter owner;

    public Listener longClickListener = null;
    public Listener clickListener = null;

    public AdapterLesson(DiffCallbackLesson diffCallback, Chapter owner) {
        super(diffCallback);
        this.owner = owner;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;
        switch (viewType) {
            case VIEW_TYPE_STYLE_0:
                layout = R.layout.item_lesson_0;
                break;
            default:
                throw new RuntimeException("Unknown view type " + viewType);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder viewHolder, int position) {
        Lesson people = getItem(position);
        String ln = String.valueOf(people.getTitle());
        viewHolder.labTitle.setText(ln);
        String desc = String.valueOf(people.getDesc());
        if (desc.length() > 20)
            desc = desc.substring(0, 20)+"...";
        viewHolder.labDesc.setText(desc);

        viewHolder.imgPrev.setBackgroundResource(R.drawable.ic_launcher_background);
//        Context context = viewHolder.itemView.getContext();
        //int resID = context.getResources().getIdentifier(mDrawableName, "drawable", context.getPackageName());
        //viewHolder.imPhoto.setImageResource(resID);
        viewHolder.itemView.setOnClickListener(v -> {
            //Snackbar.make(viewHolder.itemView, "people position = " + position + "\n" + "people _id = " + people.getId(), Snackbar.LENGTH_LONG).show();
            clickListener.fire(getCurrentList().get(position));
        });
        viewHolder.itemView.setOnLongClickListener(v -> {
            //Snackbar.make(viewHolder.itemView, "people position = " + position + "\n" + "people _id = " + people.getId(), Snackbar.LENGTH_LONG).show();
            longClickListener.fire(getCurrentList().get(position));
            return false;
        });
    }

    @Override
    public int getItemViewType(int position) {
        Lesson people = getItem(position);
        if (owner.getStyle() == 0) return VIEW_TYPE_STYLE_0;
        else return -1;
    }

    public interface Listener {
        void fire(Lesson l);
    }
}