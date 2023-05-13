package ru.romeo.cg.surface;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

import ru.romeo.cg.fragment.welcome.WelcomeF;
import ru.romeo.cg.provider.NavigationProvider;
import ru.romeo.cg.surface.timer.TimerBackground;
import ru.romeo.cg.surface.timer.TimerLifecycle;

public class ViewGeneric extends SurfaceView implements SurfaceHolder.Callback {
    private final String LOG_TAG = ViewGeneric.class.getSimpleName();
    private TimerBackground timerBackground;
    protected TimerLifecycle timerLifecycle;

    public ViewGeneric(Context context) {
        super(context);
        showLog("construct");
        getHolder().addCallback(this);
    }

    protected void showLog(String message) {
        Log.d(LOG_TAG, LOG_TAG + ": " + message);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        showLog("created");
        timerBackground = new TimerBackground(getContext(), getHolder());
        timerBackground.startWork();
        timerLifecycle = new TimerLifecycle();
        timerLifecycle.startWork();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                retry = timerBackground.stopWork() || timerLifecycle.stopWork();
            } catch (Exception ignored) {
                retry = false;
            }
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {
            timerBackground.updateTouchCoordinate((int) event.getX(), (int) event.getY());
        }
        return true;
    }
}
