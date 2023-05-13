package ru.romeo.domain.entity.lesson.comparator;

import java.util.Comparator;

import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.Lesson;

public class LessonByTitle implements Comparator<Lesson> {
    @Override
    public int compare(Lesson c0, Lesson c1) {
        String name0 = c0.getTitle();
        String name1 = c1.getTitle();
        return name0.compareTo(name1);
    }
}
