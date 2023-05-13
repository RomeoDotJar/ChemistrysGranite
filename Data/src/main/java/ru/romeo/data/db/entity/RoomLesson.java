package ru.romeo.data.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = RoomLesson.NAME_TABLE,
        indices = {@Index(RoomLesson.COL_ID)}
)
public class RoomLesson {
    public static final String NAME_TABLE = "Lessons";
    public static final String COL_ID = "Id";
    public static final String COL_TITLE = "Title";
    public static final String COL_DESC = "Desc";
    public static final String COL_CONTENT = "Content";
    public static final String COL_STYLE = "Style";
    public static final String COL_PROGRESS = "Progress";
    public static final String COL_ORDER = "Order";

    @PrimaryKey
    @NonNull
    @ColumnInfo(name=COL_ID)
    private final long id;

    @ColumnInfo(name=COL_TITLE)
    private final String title;

    @ColumnInfo(name=COL_DESC)
    private final String desc;

    @ColumnInfo(name=COL_CONTENT)
    private final String content;

    @ColumnInfo(name=COL_STYLE)
    private final int style;

    @ColumnInfo(name=COL_PROGRESS)
    private final int progress;

    @ColumnInfo(name=COL_ORDER)
    private final int order;

    public RoomLesson(long id, String title, String desc, String content, int style, int progress, int order) {
        this.id   =id;
        this.title=title;
        this.desc =desc;
        this.content=content;
        this.style=style;
        this.progress=progress;
        this.order=order;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getContent() {
        return content;
    }

    public int getStyle() {
        return style;
    }

    public int getProgress() {
        return progress;
    }

    public int getOrder() {
        return order;
    }
}