package ru.romeo.cg.fragment.edu.lesson;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ru.romeo.cg.R;

public class LessonViewHolder extends RecyclerView.ViewHolder {
    TextView labTitle;
    TextView labDesc;
    FrameLayout imgPrev;

    public LessonViewHolder(View itemView) {
        super(itemView);
        labTitle = itemView.findViewById(R.id.labTitleL);
        labDesc = itemView.findViewById(R.id.labDescL);
        imgPrev = itemView.findViewById(R.id.imgPrevL);
    }
}
