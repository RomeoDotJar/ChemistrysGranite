package ru.romeo.cg.fragment.edu.chapter;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.romeo.domain.usecase.chapter.ChapterAddUC;
import ru.romeo.domain.usecase.chapter.ChapterDeleteByIdUC;
import ru.romeo.domain.usecase.chapter.ChapterGetAllUC;
import ru.romeo.domain.usecase.chapter.ChapterGetByIdUC;

public class EduVMFactory implements ViewModelProvider.Factory {
    private ChapterGetAllUC _getAll;
    private ChapterGetByIdUC _getById;
    private ChapterDeleteByIdUC _del;
    private ChapterAddUC _add;


    public EduVMFactory(
            ChapterGetAllUC getAll,
            ChapterGetByIdUC getById,
            ChapterAddUC add,
            ChapterDeleteByIdUC del
    ) {
        this._getAll = getAll;
        this._getById = getById;
        this._add = add;
        this._del = del;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new EduViewModel(_getAll, _add, _del);
    }
}
