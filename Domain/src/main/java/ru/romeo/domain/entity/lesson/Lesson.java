package ru.romeo.domain.entity.lesson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.Comparator;

import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.comparator.LessonById;
import ru.romeo.domain.entity.lesson.comparator.LessonByOrder;
import ru.romeo.domain.entity.lesson.comparator.LessonByProgressOrder;
import ru.romeo.domain.entity.lesson.comparator.LessonByTitle;

public class Lesson {
    public static final Comparator<Lesson> byId, byTitle, byProgressOrder, byOrder;

    static {
        byOrder = new LessonByOrder();
        byId = new LessonById();
        byTitle = new LessonByTitle();
        byProgressOrder = new LessonByProgressOrder();
    }

    private final int style, progress, order;
    private final long id;

    private final String title, content, desc;

    public long getId() {
        return id;
    }

    public int getStyle() {
        return style;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
    public String getDesc() {
        return desc;
    }

    public int getProgress() {return progress;}

    public int getOrder() {return order;}

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public Lesson(long id0, String title0, String content0, String desc0, int style0, int progress0, int order0) {
        id   =id0;
        title=title0;
        content=content0;
        desc=desc0;
        style=style0;
        progress=progress0;
        order=order0;
    }

    public Lesson(Lesson c) {
        id=c.getId();
        title=c.getTitle();
        content=c.getContent();
        desc=c.getDesc();
        style=c.getStyle();
        progress=c.getProgress();
        order=c.getOrder();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Lesson guest = (Lesson) obj;
        return ((Long)id).equals(guest.getId());
    }
}
