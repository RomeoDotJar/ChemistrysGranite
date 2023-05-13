package ru.romeo.cg.fragment.edu.lesson;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.romeo.domain.usecase.lesson.LessonAddUC;
import ru.romeo.domain.usecase.lesson.LessonDeleteByIdUC;
import ru.romeo.domain.usecase.lesson.LessonGetAllUC;
import ru.romeo.domain.usecase.lesson.LessonGetByIdUC;

public class LessonVMFactory implements ViewModelProvider.Factory {
    private LessonGetAllUC _getAll;
    private LessonGetByIdUC _getById;
    private LessonDeleteByIdUC _del;
    private LessonAddUC _add;


    public LessonVMFactory(
            LessonGetAllUC getAll,
            LessonGetByIdUC getById,
            LessonAddUC add,
            LessonDeleteByIdUC del
    ) {
        this._getAll = getAll;
        this._getById = getById;
        this._add = add;
        this._del = del;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new LessonViewModel(_getAll, _add, _del);
    }
}
