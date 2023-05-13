package ru.romeo.domain.repo;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import ru.romeo.domain.entity.chapter.Chapter;

public interface ChapterDomainRepo {
    void chapterAdd(Chapter c);

    void chapterEdit(Chapter c);

    void chapterDeleteById(long id);

    MutableLiveData<ArrayList<Chapter>> chapterGetAll();

    MutableLiveData<Chapter> chapterGetById(long id);
}
