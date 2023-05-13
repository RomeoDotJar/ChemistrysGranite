package ru.romeo.domain.entity.lesson.comparator;

import java.util.Comparator;

import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.Lesson;

public class LessonById implements Comparator<Lesson> {
    @Override
    public int compare(Lesson c0, Lesson c1) {
        Long name0 = c0.getId();
        long name1 = c1.getId();
        return name0.compareTo(name1);
    }
}
