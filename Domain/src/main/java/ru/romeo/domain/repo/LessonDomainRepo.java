package ru.romeo.domain.repo;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.Lesson;

public interface LessonDomainRepo {
    void lessonAdd(Lesson c);

    void lessonEdit(Lesson c);

    void lessonDeleteById(long id);

    MutableLiveData<ArrayList<Lesson>> lessonGetAll();

    MutableLiveData<Lesson> lessonGetById(long id);
}
