package ru.romeo.data.repo;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import ru.romeo.data.db.dao.RoomChapterDao;
import ru.romeo.data.db.dao.RoomLessonDao;
import ru.romeo.data.db.entity.RoomChapter;
import ru.romeo.data.db.entity.RoomLesson;
import ru.romeo.data.map.EntityMapper;
import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.Lesson;
import ru.romeo.domain.repo.ChapterDomainRepo;
import ru.romeo.domain.repo.LessonDomainRepo;

public class LessonRoomRepo implements LessonDomainRepo {
    private final RoomLessonDao roomLessonDao;
    private final MutableLiveData<ArrayList<Lesson>> lessonsListLiveData;
    private final MutableLiveData<Lesson> lessonLiveData;

    private static int autoIncrementId = 0;

    public LessonRoomRepo(
            RoomLessonDao roomLessonDao
    ) {
        this.roomLessonDao = roomLessonDao;
        lessonsListLiveData = new MutableLiveData<>();
        lessonLiveData = new MutableLiveData<>();
        updateChapterListAsyncTask();
    }

    @Override
    public void lessonAdd(Lesson lesson) {
        AsyncTask.execute(() -> {
            RoomLesson rp = EntityMapper.toRoomLesson(lesson);
            synchronized (roomLessonDao) {
                roomLessonDao.roomLessonAdd(rp);
            }
        });
        updateChapterListAsyncTask();
    }

    @Override
    public void lessonEdit(Lesson lesson) {
        AsyncTask.execute(() -> {
            RoomLesson roomLesson_new = EntityMapper.toRoomLesson(
                    new Lesson(lesson));
            synchronized (roomLessonDao) {
                roomLessonDao.roomLessonDeleteById(lesson.getId());
                roomLessonDao.roomLessonAdd(roomLesson_new);
            }
        });
        updateChapterListAsyncTask();
    }

    @Override
    public void lessonDeleteById(long id) {
        AsyncTask.execute(() -> {
            synchronized (roomLessonDao) {
                roomLessonDao.roomLessonDeleteById(id);
            }
        });
        updateChapterListAsyncTask();
    }

    private void updateChapterListAsyncTask() {
        AsyncTask.execute(() -> {
            synchronized (roomLessonDao) {
                List<RoomLesson> roomLessonData = roomLessonDao.roomLessonGetAll();
                ArrayList<Lesson> data = new ArrayList<>();
                for (RoomLesson roomLesson : roomLessonData)
                    data.add(EntityMapper.toLesson(roomLesson));
                lessonsListLiveData.postValue(new ArrayList<>(data));
            }
        });
    }


    private void findLessonById(long id) {
        AsyncTask.execute(() -> {
            synchronized (roomLessonDao) {
                Lesson lesson = EntityMapper.toLesson(roomLessonDao.roomLessonGetById(id));
                lessonLiveData.postValue(lesson);
            }
        });
    }

    @Override
    public MutableLiveData<ArrayList<Lesson>> lessonGetAll() {
        return lessonsListLiveData;
    }

    @Override
    public MutableLiveData<Lesson> lessonGetById(long id) {
        findLessonById(id);
        return lessonLiveData;
    }
}
