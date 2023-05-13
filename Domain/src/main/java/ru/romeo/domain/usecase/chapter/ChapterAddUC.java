package ru.romeo.domain.usecase.chapter;

import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.repo.ChapterDomainRepo;

public class ChapterAddUC {
    private final ChapterDomainRepo repo;

    public ChapterAddUC(ChapterDomainRepo repo0) {
        repo=repo0;
    }

    public void chapterAdd(Chapter c) {
        repo.chapterAdd(c);
    }
}
