package ru.romeo.cg.fragment.edu.chapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import ru.romeo.domain.entity.chapter.Chapter;

public class DiffCallback extends DiffUtil.ItemCallback<Chapter> {

    @Override
    public boolean areItemsTheSame(@NonNull Chapter oldPeople, @NonNull Chapter newPeople) {
        return ((Long)oldPeople.getId()).equals(newPeople.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Chapter oldPeople, @NonNull Chapter newPeople) {
        return oldPeople.equals(newPeople);
    }
}
