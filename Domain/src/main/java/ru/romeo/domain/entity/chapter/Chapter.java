package ru.romeo.domain.entity.chapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.Comparator;

import ru.romeo.domain.entity.chapter.comparator.ChapterById;
import ru.romeo.domain.entity.chapter.comparator.ChapterByOrder;
import ru.romeo.domain.entity.chapter.comparator.ChapterByProgressOrder;
import ru.romeo.domain.entity.chapter.comparator.ChapterByTitle;

public class Chapter {
    public static final Comparator<Chapter> byId, byTitle, byProgressOrder, byOrder;

    static {
        byOrder = new ChapterByOrder();
        byId = new ChapterById();
        byTitle = new ChapterByTitle();
        byProgressOrder = new ChapterByProgressOrder();
    }

    private final int style, progress, order;
    private final long id;

    private final String title, desc;

    public long getId() {
        return id;
    }

    public int getStyle() {
        return style;
    }

    public String getTitle() {
        return title;
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

    public Chapter(long id0, String title0, String desc0, int style0, int progress0, int order0) {
        id   =id0;
        title=title0;
        desc =desc0;
        style=style0;
        progress=progress0;
        order=order0;
    }

    public Chapter(Chapter c) {
        id=c.getId();
        title=c.getTitle();
        desc=c.getDesc();
        style=c.getStyle();
        progress=c.getProgress();
        order=c.getOrder();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Chapter guest = (Chapter) obj;
        return ((Long)id).equals(guest.getId());
    }
}
