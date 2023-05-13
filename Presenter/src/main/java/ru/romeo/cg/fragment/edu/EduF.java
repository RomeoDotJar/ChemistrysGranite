package ru.romeo.cg.fragment.edu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import ru.romeo.app.AppStart;
import ru.romeo.cg.R;
import ru.romeo.cg.activity.ActivityMain;
import ru.romeo.cg.fragment.GenericF;
import ru.romeo.cg.fragment.about_lesson.AboutLessonF;
import ru.romeo.cg.fragment.edu.chapter.Adapter;
import ru.romeo.cg.fragment.edu.chapter.DiffCallback;
import ru.romeo.cg.fragment.edu.chapter.EduVMFactory;
import ru.romeo.cg.fragment.edu.chapter.EduViewModel;
import ru.romeo.data.gen.DataGen;
import ru.romeo.data.pref.PrefHelper;
import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.Lesson;

public class EduF extends GenericF {
    public static final String FRAGMENT_ID = "edu";

    private EduViewModel viewModel;

    private RecyclerView recyclerView;
    private ItemTouchHelper chapterTouchHelper;

    private Adapter adapter;

    public static EduF newInstance() {
        EduF frag = new EduF();
        return frag;
    }

    @Override
    public String getFI() {
        return FRAGMENT_ID;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(FRAGMENT_ID, "onViewCreated: "+AppStart.getInstance().getChapterGetAll().chapterGetAll().getValue());
        Log.d(FRAGMENT_ID, "onViewCreated: "+AppStart.getInstance().getLessonGetAll().lessonGetAll().getValue());
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_edu,
                container,
                false
        );
    }

    private void setupRecyclerView() {
        recyclerView.getRecycledViewPool().setMaxRecycledViews(
                Adapter.VIEW_TYPE_STYLE_0, Adapter.MAX_POOL_SIZE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initViewModel();
        initView(view);
    }

    private void chapterClick(Chapter c) {
        Log.d(FRAGMENT_ID, "chapterClick: "+c.toString());
    }

    private void chapterClickLong(Chapter c) {
        Log.d(FRAGMENT_ID, "chapterClickLong: "+c.toString());
    }

    private void initAdapter() {
        adapter = new Adapter(new DiffCallback(), getContext(), this);
        adapter.clickListener = this::chapterClick;
        adapter.longClickListener = this::chapterClickLong;
        //adapter.peopleClickListener = this::startAboutPeople;
    }

    private void initViewModel() {
        AppStart app = AppStart.getInstance();
        viewModel = new ViewModelProvider(
                this,
                new EduVMFactory(
                        app.getChapterGetAll(),
                        app.getChapterGetById(),
                        app.getAdd(),
                        app.getDel()
                ))
                .get(EduViewModel.class);
        viewModel
                .getChapterList()
                .observe(
                        getViewLifecycleOwner(),
                        peoples -> {
                            adapter.submitList(peoples);
                        }
                );
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.chaptersView);
        recyclerView.setAdapter(adapter);
        setupRecyclerView();

        //peopleTouchHelper.attachToRecyclerView(recyclerView);
    }
}

