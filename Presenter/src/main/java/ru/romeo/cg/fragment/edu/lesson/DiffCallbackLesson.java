package ru.romeo.cg.fragment.edu.lesson;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import ru.romeo.domain.entity.lesson.Lesson;

public class DiffCallbackLesson extends DiffUtil.ItemCallback<Lesson> {

    @Override
    public boolean areItemsTheSame(@NonNull Lesson oldPeople, @NonNull Lesson newPeople) {
        return ((Long)oldPeople.getId()).equals(newPeople.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Lesson oldPeople, @NonNull Lesson newPeople) {
        return oldPeople.equals(newPeople);
    }
}
