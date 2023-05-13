package ru.romeo.domain.entity.lesson.comparator;

import java.util.Comparator;

import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.Lesson;

public class LessonByOrder implements Comparator<Lesson> {
    @Override
    public int compare(Lesson c0, Lesson c1) {
        Integer name0 = c0.getOrder();
        int name1 = c1.getOrder();
        return name0.compareTo(name1);
    }
}
