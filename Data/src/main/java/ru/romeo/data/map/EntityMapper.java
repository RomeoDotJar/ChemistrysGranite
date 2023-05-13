package ru.romeo.data.map;

import ru.romeo.data.db.entity.RoomChapter;
import ru.romeo.data.db.entity.RoomLesson;
import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.Lesson;

public class EntityMapper {

    public static RoomChapter toRoomChapter(Chapter Chapter) {
        return new RoomChapter(
                Chapter.getId(),
                Chapter.getTitle(),
                Chapter.getDesc(),
                Chapter.getStyle(),
                Chapter.getProgress(),
                Chapter.getOrder()
        );
    }

    public static Chapter toChapter(RoomChapter roomChapter) {
        return new Chapter(
                roomChapter.getId(),
                roomChapter.getTitle(),
                roomChapter.getDesc(),
                roomChapter.getStyle(),
                roomChapter.getProgress(),
                roomChapter.getOrder()
        );
    }

    public static RoomLesson toRoomLesson(Lesson Chapter) {
        return new RoomLesson(
                Chapter.getId(),
                Chapter.getTitle(),
                Chapter.getDesc(),
                Chapter.getContent(),
                Chapter.getStyle(),
                Chapter.getProgress(),
                Chapter.getOrder()
        );
    }

    public static Lesson toLesson(RoomLesson roomChapter) {
        return new Lesson(
                roomChapter.getId(),
                roomChapter.getTitle(),
                roomChapter.getContent(),
                roomChapter.getDesc(),
                roomChapter.getStyle(),
                roomChapter.getProgress(),
                roomChapter.getOrder()
        );
    }
}
