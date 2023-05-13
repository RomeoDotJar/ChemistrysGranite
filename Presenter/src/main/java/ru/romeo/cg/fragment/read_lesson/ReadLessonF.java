package ru.romeo.cg.fragment.read_lesson;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import ru.romeo.cg.R;
import ru.romeo.cg.fragment.GenericF;
import ru.romeo.cg.fragment.about_lesson.AboutLessonF;
import ru.romeo.cg.provider.NavigationProvider;
import ru.romeo.cg.surface.ViewGeneric;
import ru.romeo.cg.surface.ViewReadLesson;
import ru.romeo.domain.entity.lesson.Lesson;

public class ReadLessonF extends GenericF {
    public static final String FRAGMENT_ID = "read_lesson";
    public static final String LESSON_GSON_KEY = "content";

    @Override
    public String getFI() {
        return FRAGMENT_ID;
    }

    private Lesson lesson;

    public static ReadLessonF newInstance(
            String lessonGson
    ) {
        ReadLessonF frag = new ReadLessonF();
        Bundle bundle = new Bundle();
        bundle.putString(LESSON_GSON_KEY, lessonGson);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_read_lesson,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initContent();
    }

    private void initView(View view) {
        ViewReadLesson surface = new ViewReadLesson(getContext());
        FrameLayout layout = getView().requireViewById(R.id.layoutView);
        layout.addView(surface);
    }

    private void initContent() {
        lesson = new Gson().fromJson(getArguments().getString(LESSON_GSON_KEY), Lesson.class);
    }
}
