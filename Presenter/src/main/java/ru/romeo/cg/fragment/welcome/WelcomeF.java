package ru.romeo.cg.fragment.welcome;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.romeo.app.AppStart;
import ru.romeo.cg.R;
import ru.romeo.cg.fragment.GenericF;
import ru.romeo.cg.fragment.home.HomeF;
import ru.romeo.cg.provider.NavigationProvider;
import ru.romeo.cg.surface.ViewGeneric;
import ru.romeo.data.gen.DataGen;
import ru.romeo.data.pref.PrefHelper;
import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.Lesson;

public class WelcomeF extends GenericF {
    public static final String FRAGMENT_ID = "welcome";

    @Override
    public String getFI() {
        return FRAGMENT_ID;
    }

    //private static final String ARGUMENT_IS_ONE_PANE_STATE = "is one pane";
    //private Boolean isOnePane;

    public static WelcomeF newInstance() {//Boolean isOnePane) {
        //Bundle args = new Bundle();
        //args.putBoolean(ARGUMENT_IS_ONE_PANE_STATE, isOnePane);
        WelcomeF frag = new WelcomeF();

        //frag.setArguments(args);
        return frag;
    }

    private void parseParams() {
        Bundle args = requireArguments();
        //if (!args.containsKey(ARGUMENT_IS_ONE_PANE_STATE))
        //    throw new RuntimeException("Arguments are absent");
        //isOnePane = args.getBoolean(ARGUMENT_IS_ONE_PANE_STATE);
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
                R.layout.fragment_welcome,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        PrefHelper.put(PrefHelper.LAST_CHAPTER, 0);
        PrefHelper.put(PrefHelper.LAST_LESSON, 0);

        AppStart.getInstance().getAdd().chapterAdd(DataGen.newChapter());
        AppStart.getInstance().getLessonAdd().lessonAdd(DataGen.newLesson());
        AppStart.getInstance().getLessonAdd().lessonAdd(DataGen.newLesson());

        AppStart.getInstance().getAdd().chapterAdd(DataGen.newChapter());
        AppStart.getInstance().getLessonAdd().lessonAdd(DataGen.newLesson());
        AppStart.getInstance().getLessonAdd().lessonAdd(DataGen.newLesson());
        AppStart.getInstance().getLessonAdd().lessonAdd(DataGen.newLesson());
        AppStart.getInstance().getLessonAdd().lessonAdd(DataGen.newLesson());
        AppStart.getInstance().getLessonAdd().lessonAdd(DataGen.newLesson());

        AppStart.getInstance().getAdd().chapterAdd(DataGen.newChapter());
        AppStart.getInstance().getLessonAdd().lessonAdd(DataGen.newLesson());
        AppStart.getInstance().getLessonAdd().lessonAdd(DataGen.newLesson());
        AppStart.getInstance().getLessonAdd().lessonAdd(DataGen.newLesson());
        AppStart.getInstance().getLessonAdd().lessonAdd(DataGen.newLesson());
        //initCanvas(FRAGMENT_ID);

        /*view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    Activity ctx = ((Activity)getContext());
                    BottomNavigationView botNav = ctx.requireViewById(R.id.botNav);
                    if (botNav.getVisibility()==VISIBLE) {
                        Log.d(FRAGMENT_ID, FRAGMENT_ID+": detected visible botnav");
                        botNav.setTranslationY(0);
                        botNav.setVisibility(INVISIBLE);
                        botNav.animate()
                                .alpha(0f)
                                .translationY(256)
                                .setDuration(1000);
                    }
                    return true;
                }
                return false;
            }
        } );*/

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(FRAGMENT_ID, FRAGMENT_ID+": onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(FRAGMENT_ID, FRAGMENT_ID+": onResume");
        super.onResume();
    }

    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);

    }

    @Override
    public void onFocusGain() {
        Log.d(FRAGMENT_ID, FRAGMENT_ID+": onFocusGain");

        super.onFocusGain();
    }
}
