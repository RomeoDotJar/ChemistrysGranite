package ru.romeo.domain.entity.chapter.comparator;

import java.util.Comparator;

import ru.romeo.domain.entity.chapter.Chapter;

public class ChapterById implements Comparator<Chapter> {
    @Override
    public int compare(Chapter c0, Chapter c1) {
        Long name0 = c0.getId();
        long name1 = c1.getId();
        return name0.compareTo(name1);
    }
}
