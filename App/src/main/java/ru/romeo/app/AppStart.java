package ru.romeo.app;

import android.app.Application;

import ru.romeo.data.db.database.AppDatabase;
import ru.romeo.data.repo.ChapterRoomRepo;
import ru.romeo.data.repo.LessonRoomRepo;
import ru.romeo.domain.usecase.chapter.ChapterAddUC;
import ru.romeo.domain.usecase.chapter.ChapterDeleteByIdUC;
import ru.romeo.domain.usecase.chapter.ChapterGetAllUC;
import ru.romeo.domain.usecase.chapter.ChapterGetByIdUC;
import ru.romeo.domain.usecase.lesson.LessonAddUC;
import ru.romeo.domain.usecase.lesson.LessonDeleteByIdUC;
import ru.romeo.domain.usecase.lesson.LessonGetAllUC;
import ru.romeo.domain.usecase.lesson.LessonGetByIdUC;

public class AppStart extends Application {
    private ChapterGetByIdUC getById;
    private ChapterGetAllUC getAll;
    private ChapterAddUC add;
    private ChapterDeleteByIdUC del;

    private LessonGetByIdUC lessonGetById;
    private LessonGetAllUC lessonGetAll;
    private LessonAddUC lessonAdd;
    private LessonDeleteByIdUC lessonDel;

    public ChapterGetByIdUC getChapterGetById() {
        return getById;
    }

    public ChapterGetAllUC getChapterGetAll() {
        return getAll;
    }

    public LessonGetByIdUC getLessonGetById() {
        return lessonGetById;
    }

    public LessonGetAllUC getLessonGetAll() {
        return lessonGetAll;
    }
    public LessonDeleteByIdUC getLessonDel() {
        return lessonDel;
    }

    private static AppStart instance;

    public ChapterAddUC getAdd() {
        return add;
    }

    public ChapterDeleteByIdUC getDel() {
        return del;
    }

    public LessonAddUC getLessonAdd() { return lessonAdd; }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppDatabase database = AppDatabase.getInstance(this);
        ChapterRoomRepo chapterRepo =
                new ChapterRoomRepo(database.roomChapterDao());
        LessonRoomRepo lessonRepo =
                new LessonRoomRepo(database.roomLessonDao());

        getById = new ChapterGetByIdUC(chapterRepo);
        getAll = new ChapterGetAllUC(chapterRepo);
        add = new ChapterAddUC(chapterRepo);
        del = new ChapterDeleteByIdUC(chapterRepo);

        lessonGetById = new LessonGetByIdUC(lessonRepo);
        lessonGetAll = new LessonGetAllUC(lessonRepo);
        lessonAdd = new LessonAddUC(lessonRepo);
        lessonDel = new LessonDeleteByIdUC(lessonRepo);
    }

    public static AppStart getInstance() {
        return instance;
    }
}
