package ru.romeo.cg.fragment.edu.chapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;

import ru.romeo.app.AppStart;
import ru.romeo.cg.R;
import ru.romeo.cg.activity.ActivityMain;
import ru.romeo.cg.fragment.GenericF;
import ru.romeo.cg.fragment.about_lesson.AboutLessonF;
import ru.romeo.cg.fragment.edu.lesson.AdapterLesson;
import ru.romeo.cg.fragment.edu.lesson.DiffCallbackLesson;
import ru.romeo.cg.fragment.edu.lesson.LessonVMFactory;
import ru.romeo.cg.fragment.edu.lesson.LessonViewModel;
import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.Lesson;

public class Adapter extends ListAdapter<Chapter, ViewHolder> {

    public static final int MAX_POOL_SIZE = 10;
    public static final int VIEW_TYPE_STYLE_0 = 0;

    public Listener longClickListener = null;
    public Listener clickListener = null;

    private Context ctx;
    private GenericF owner;


    public Adapter(DiffCallback diffCallback, Context context, GenericF owner) {
        super(diffCallback); ctx=context; this.owner=owner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;
        switch (viewType) {
            case VIEW_TYPE_STYLE_0:
                layout = R.layout.item_chapter_0;
                break;
            default:
                throw new RuntimeException("Unknown view type " + viewType);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    private void lessonClick(Lesson l) {
        AboutLessonF frag = AboutLessonF.newInstance(l.toString());
        ActivityMain act = ((ActivityMain)owner.getActivity());
        frag.show(act.getSupportFragmentManager(), frag.FRAGMENT_TAG);

        Log.d("Adapter", "lessonClick: "+l.getTitle());
    }

    private void lessonClickLong(Lesson l) {
        Log.d("Adapter", "lessonClickLong: "+l.toString());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Chapter people = getItem(position);
        String ln = String.valueOf(people.getTitle());
        viewHolder.labTitle.setText(ln);
        String desc = String.valueOf(people.getDesc());
        if (desc.length() > 50)
            desc = desc.substring(0, 50)+"...";
        viewHolder.labDesc.setText(desc);

        Chapter chapter = getCurrentList().get(position);
        AdapterLesson adapter = new AdapterLesson(new DiffCallbackLesson(), chapter);
        adapter.clickListener = this::lessonClick;
        adapter.longClickListener = this::lessonClickLong;

        LessonViewModel viewModel = new ViewModelProvider(
                owner,
                new LessonVMFactory(
                        AppStart.getInstance().getLessonGetAll(),
                        AppStart.getInstance().getLessonGetById(),
                        AppStart.getInstance().getLessonAdd(),
                        AppStart.getInstance().getLessonDel()
                ))
                .get(LessonViewModel.class);
        viewModel
                .getLessonList()
                .observe(
                        owner.getViewLifecycleOwner(),
                        peoples -> {
                            ArrayList<Lesson> sorted = new ArrayList<>();
                            for (Lesson lesson : peoples) {
                                Log.d("Adapter", "onBindViewHolder: "+chapter.getOrder()+", "+lesson.getOrder()+", "+((Integer)chapter.getOrder()).equals(lesson.getOrder()/10000));
                                if (((Integer)chapter.getOrder()).equals(lesson.getOrder()/10000))
                                    sorted.add(lesson);
                            }
                            adapter.submitList(sorted);
                        }
                );

        viewHolder.listLesson.setAdapter(adapter);
        viewHolder.listLesson.getRecycledViewPool().setMaxRecycledViews(
                AdapterLesson.VIEW_TYPE_STYLE_0, AdapterLesson.MAX_POOL_SIZE);
        viewHolder.listLesson.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false));

        Context context = viewHolder.itemView.getContext();
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
        Chapter people = getItem(position);
        if (people.getStyle() == 0) return VIEW_TYPE_STYLE_0;
        else return -1;
    }

    public interface Listener {
        void fire(Chapter c);
    }
}