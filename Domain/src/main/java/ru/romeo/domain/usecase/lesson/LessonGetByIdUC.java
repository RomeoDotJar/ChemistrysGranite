package ru.romeo.domain.usecase.lesson;

import androidx.lifecycle.MutableLiveData;

import ru.romeo.domain.entity.lesson.Lesson;
import ru.romeo.domain.repo.LessonDomainRepo;

public class LessonGetByIdUC {
    private final LessonDomainRepo repo;

    public LessonGetByIdUC(LessonDomainRepo repo0) {
        repo=repo0;
    }

    public MutableLiveData<Lesson> lessonGetAll(int id) {
        return repo.lessonGetById(id);
    }
}
