package ru.romeo.cg.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;

import ru.romeo.app.AppStart;
import ru.romeo.cg.R;
import ru.romeo.cg.fragment.GenericF;
import ru.romeo.cg.provider.NavigationProvider;
import ru.romeo.data.gen.DataGen;
import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.Lesson;

public class HomeF extends GenericF {
    public static final String FRAGMENT_ID = "home";

    public static HomeF newInstance() {
        HomeF frag = new HomeF();
        return frag;
    }

    @Override
    public String getFI() {
        return FRAGMENT_ID;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_home,
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
    }
}
