package ru.romeo.domain.entity.chapter.comparator;

import java.util.Comparator;

import ru.romeo.domain.entity.chapter.Chapter;

public class ChapterByOrder implements Comparator<Chapter> {
    @Override
    public int compare(Chapter c0, Chapter c1) {
        Integer name0 = c0.getOrder();
        int name1 = c1.getOrder();
        return name0.compareTo(name1);
    }
}
