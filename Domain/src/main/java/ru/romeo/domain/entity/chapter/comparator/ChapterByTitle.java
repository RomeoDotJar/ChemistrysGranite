package ru.romeo.domain.entity.chapter.comparator;

import java.util.Comparator;

import ru.romeo.domain.entity.chapter.Chapter;

public class ChapterByTitle implements Comparator<Chapter> {
    @Override
    public int compare(Chapter c0, Chapter c1) {
        String name0 = c0.getTitle();
        String name1 = c1.getTitle();
        return name0.compareTo(name1);
    }
}
