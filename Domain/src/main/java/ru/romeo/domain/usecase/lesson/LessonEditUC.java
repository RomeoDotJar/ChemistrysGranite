package ru.romeo.domain.usecase.lesson;

import ru.romeo.domain.entity.lesson.Lesson;
import ru.romeo.domain.repo.LessonDomainRepo;

public class LessonEditUC {
    private final LessonDomainRepo repo;

    public LessonEditUC(LessonDomainRepo repo0) {
        repo=repo0;
    }

    public void lessonEdit(Lesson c) {
        repo.lessonEdit(c);
    }
}
