package ru.romeo.domain.usecase.chapter;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.repo.ChapterDomainRepo;

public class ChapterGetAllUC {
    private final ChapterDomainRepo repo;

    public ChapterGetAllUC(ChapterDomainRepo repo0) {
        repo=repo0;
    }

    public MutableLiveData<ArrayList<Chapter>> chapterGetAll() {
        return repo.chapterGetAll();
    }
}
