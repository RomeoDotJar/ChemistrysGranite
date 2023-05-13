package ru.romeo.domain.entity.lesson.comparator;

import java.util.Comparator;

import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.Lesson;

public class LessonByProgressOrder implements Comparator<Lesson> {
    @Override
    public int compare(Lesson c0, Lesson c1) {
        Integer name0 = c0.getProgress();
        Integer name1 = c1.getProgress();
        int result = name0.compareTo(name1);
        return result == 0 ?
                ((Integer)c0.getOrder()).compareTo(c1.getOrder()) :
                result;
    }
}