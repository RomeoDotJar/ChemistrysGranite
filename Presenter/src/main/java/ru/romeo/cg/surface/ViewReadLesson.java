package ru.romeo.cg.surface;

import android.content.Context;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import ru.romeo.cg.surface.timer.TimerLifecycle;
import ru.romeo.cg.surface.timer.TimerZenBackground;

public class ViewReadLesson extends ViewGeneric {
    private final String LOG_TAG = ViewReadLesson.class.getSimpleName();

    private TimerZenBackground timerBackground;

    public ViewReadLesson(Context context) {
        super(context);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        showLog("created");
        timerBackground = new TimerZenBackground(getContext(), getHolder());
        timerBackground.startWork();
        timerLifecycle = new TimerLifecycle();
        timerLifecycle.startWork();
    }
}
