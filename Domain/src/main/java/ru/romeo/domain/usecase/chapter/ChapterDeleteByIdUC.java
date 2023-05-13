package ru.romeo.domain.usecase.chapter;

import ru.romeo.domain.repo.ChapterDomainRepo;

public class ChapterDeleteByIdUC {
    private final ChapterDomainRepo repo;

    public ChapterDeleteByIdUC(ChapterDomainRepo repo0) {
        repo=repo0;
    }

    public void chapterDeleteById(long id) {
        repo.chapterDeleteById(id);
    }
}
