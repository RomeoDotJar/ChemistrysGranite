package ru.romeo.cg.fragment.edu.chapter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.usecase.chapter.ChapterAddUC;
import ru.romeo.domain.usecase.chapter.ChapterDeleteByIdUC;
import ru.romeo.domain.usecase.chapter.ChapterGetAllUC;

public class EduViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Chapter>> chapterList;

    private ChapterGetAllUC getAll;
    private ChapterAddUC add;
    private ChapterDeleteByIdUC del;


    public EduViewModel(
            ChapterGetAllUC getAll,
            ChapterAddUC add,
            ChapterDeleteByIdUC del
    ) {
        this.getAll=getAll;
        this.add   =add;
        this.del   =del;
    }

    public void add(Chapter people) {
        add.chapterAdd(people);
    }

    public void del(long id) {
        del.chapterDeleteById(id);
    }

    public MutableLiveData<ArrayList<Chapter>> getChapterList() {
        chapterList = getAll.chapterGetAll();
        return chapterList;
    }
}