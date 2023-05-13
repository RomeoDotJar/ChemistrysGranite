package ru.romeo.domain.usecase.chapter;

import androidx.lifecycle.MutableLiveData;

import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.repo.ChapterDomainRepo;

public class ChapterGetByIdUC {
    private final ChapterDomainRepo repo;

    public ChapterGetByIdUC(ChapterDomainRepo repo0) {
        repo=repo0;
    }

    public MutableLiveData<Chapter> chapterGetAll(int id) {
        return repo.chapterGetById(id);
    }
}
