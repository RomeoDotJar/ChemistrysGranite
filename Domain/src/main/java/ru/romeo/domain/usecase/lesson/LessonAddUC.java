package ru.romeo.domain.usecase.lesson;

import ru.romeo.domain.entity.lesson.Lesson;
import ru.romeo.domain.entity.lesson.Lesson;
import ru.romeo.domain.repo.LessonDomainRepo;
import ru.romeo.domain.repo.LessonDomainRepo;

public class LessonAddUC {
    private final LessonDomainRepo repo;

    public LessonAddUC(LessonDomainRepo repo0) {
        repo=repo0;
    }

    public void lessonAdd(Lesson c) {
        repo.lessonAdd(c);
    }
}
