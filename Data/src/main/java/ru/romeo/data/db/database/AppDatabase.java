package ru.romeo.data.db.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.romeo.data.db.dao.RoomChapterDao;
import ru.romeo.data.db.dao.RoomLessonDao;
import ru.romeo.data.db.entity.RoomChapter;
import ru.romeo.data.db.entity.RoomLesson;

@Database(entities = {RoomChapter.class, RoomLesson.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract RoomChapterDao roomChapterDao();
    public abstract RoomLessonDao roomLessonDao();

    public static AppDatabase getInstance(Context context) {
        AppDatabase tempInstance = instance;
        if (tempInstance != null)
            return tempInstance;
        else tempInstance = Room
                .databaseBuilder(
                        context,
                        AppDatabase.class,
                        AppDatabase.class.getSimpleName()
                )
                .fallbackToDestructiveMigration()
                .build();
        instance = tempInstance;
        return tempInstance;
    }
}