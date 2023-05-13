package ru.romeo.domain.usecase.chapter;

import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.repo.ChapterDomainRepo;

public class ChapterEditUC {
    private final ChapterDomainRepo repo;

    public ChapterEditUC(ChapterDomainRepo repo0) {
        repo=repo0;
    }

    public void chapterEdit(Chapter c) {
        repo.chapterEdit(c);
    }
}
