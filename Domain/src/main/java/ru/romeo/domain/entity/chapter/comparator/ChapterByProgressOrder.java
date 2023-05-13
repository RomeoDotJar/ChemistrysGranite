package ru.romeo.domain.entity.chapter.comparator;

import java.util.Comparator;

import ru.romeo.domain.entity.chapter.Chapter;

public class ChapterByProgressOrder implements Comparator<Chapter> {
    @Override
    public int compare(Chapter c0, Chapter c1) {
        Integer name0 = c0.getProgress();
        Integer name1 = c1.getProgress();
        int result = name0.compareTo(name1);
        return result == 0 ?
                ((Integer)c0.getOrder()).compareTo(c1.getOrder()) :
                result;
    }
}