package ru.romeo.cg.fragment.edu.lesson;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.Lesson;
import ru.romeo.domain.usecase.chapter.ChapterAddUC;
import ru.romeo.domain.usecase.chapter.ChapterDeleteByIdUC;
import ru.romeo.domain.usecase.chapter.ChapterGetAllUC;
import ru.romeo.domain.usecase.lesson.LessonAddUC;
import ru.romeo.domain.usecase.lesson.LessonDeleteByIdUC;
import ru.romeo.domain.usecase.lesson.LessonGetAllUC;

public class LessonViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Lesson>> lessonList;

    private LessonGetAllUC getAll;
    private LessonAddUC add;
    private LessonDeleteByIdUC del;


    public LessonViewModel(
            LessonGetAllUC getAll,
            LessonAddUC add,
            LessonDeleteByIdUC del
    ) {
        this.getAll=getAll;
        this.add   =add;
        this.del   =del;
    }

    public void add(Lesson people) {
        add.lessonAdd(people);
    }

    public void del(long id) {
        del.lessonDeleteById(id);
    }

    public MutableLiveData<ArrayList<Lesson>> getLessonList() {
        lessonList = getAll.lessonGetAll();
        return lessonList;
    }
}