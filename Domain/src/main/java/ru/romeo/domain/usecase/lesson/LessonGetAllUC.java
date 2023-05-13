package ru.romeo.domain.usecase.lesson;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import ru.romeo.domain.entity.lesson.Lesson;
import ru.romeo.domain.repo.LessonDomainRepo;

public class LessonGetAllUC {
    private final LessonDomainRepo repo;

    public LessonGetAllUC(LessonDomainRepo repo0) {
        repo=repo0;
    }

    public MutableLiveData<ArrayList<Lesson>> lessonGetAll() {
        return repo.lessonGetAll();
    }
}
