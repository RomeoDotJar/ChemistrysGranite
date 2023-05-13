package ru.romeo.data.repo;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import ru.romeo.data.db.dao.RoomChapterDao;
import ru.romeo.data.db.entity.RoomChapter;
import ru.romeo.data.map.EntityMapper;
import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.repo.ChapterDomainRepo;

public class ChapterRoomRepo implements ChapterDomainRepo {
    private final RoomChapterDao roomChapterDao;
    private final MutableLiveData<ArrayList<Chapter>> chaptersListLiveData;
    private final MutableLiveData<Chapter> chapterLiveData;

    private static int autoIncrementId = 0;

    public ChapterRoomRepo(
            RoomChapterDao roomChapterDao
    ) {
        this.roomChapterDao = roomChapterDao;
        chaptersListLiveData = new MutableLiveData<>();
        chapterLiveData = new MutableLiveData<>();
        updateChapterListAsyncTask();
    }

    @Override
    public void chapterAdd(Chapter chapter) {
        AsyncTask.execute(() -> {
            RoomChapter rp = EntityMapper.toRoomChapter(chapter);
            synchronized (roomChapterDao) {
                roomChapterDao.roomChapterAdd(rp);
            }
        });
        updateChapterListAsyncTask();
    }

    @Override
    public void chapterEdit(Chapter chapter) {
        AsyncTask.execute(() -> {
            RoomChapter roomChapter_new = EntityMapper.toRoomChapter(
                    new Chapter(chapter));
            synchronized (roomChapterDao) {
                roomChapterDao.roomChapterDeleteById(chapter.getId());
                roomChapterDao.roomChapterAdd(roomChapter_new);
            }
        });
        updateChapterListAsyncTask();
    }

    @Override
    public void chapterDeleteById(long id) {
        AsyncTask.execute(() -> {
            synchronized (roomChapterDao) {
                roomChapterDao.roomChapterDeleteById(id);
            }
        });
        updateChapterListAsyncTask();
    }

    private void updateChapterListAsyncTask() {
        AsyncTask.execute(() -> {
            synchronized (roomChapterDao) {
                List<RoomChapter> roomChapterData = roomChapterDao.roomChapterGetAll();
                ArrayList<Chapter> data = new ArrayList<>();
                for (RoomChapter roomChapter : roomChapterData)
                    data.add(EntityMapper.toChapter(roomChapter));
                chaptersListLiveData.postValue(new ArrayList<>(data));
            }
        });
    }


    private void findChapterById(long id) {
        AsyncTask.execute(() -> {
            synchronized (roomChapterDao) {
                Chapter chapter = EntityMapper.toChapter(roomChapterDao.roomChapterGetById(id));
                chapterLiveData.postValue(chapter);
            }
        });
    }

    @Override
    public MutableLiveData<ArrayList<Chapter>> chapterGetAll() {
        return chaptersListLiveData;
    }

    @Override
    public MutableLiveData<Chapter> chapterGetById(long id) {
        findChapterById(id);
        return chapterLiveData;
    }
}
