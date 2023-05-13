package ru.romeo.domain.usecase.lesson;

import ru.romeo.domain.repo.LessonDomainRepo;

public class LessonDeleteByIdUC {
    private final LessonDomainRepo repo;

    public LessonDeleteByIdUC(LessonDomainRepo repo0) {
        repo=repo0;
    }

    public void lessonDeleteById(long id) {
        repo.lessonDeleteById(id);
    }
}
