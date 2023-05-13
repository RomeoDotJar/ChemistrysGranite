package ru.romeo.cg.fragment.edu.chapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ru.romeo.cg.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView labTitle;
    TextView labDesc;
    RecyclerView listLesson;

    public ViewHolder(View itemView) {
        super(itemView);
        labTitle = itemView.findViewById(R.id.labTitle);
        labDesc = itemView.findViewById(R.id.labDesc);
        listLesson = itemView.findViewById(R.id.listLesson);
    }
}
