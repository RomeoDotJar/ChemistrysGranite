package ru.romeo.cg.surface;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

import ru.romeo.cg.fragment.welcome.WelcomeF;
import ru.romeo.cg.provider.NavigationProvider;
import ru.romeo.cg.surface.timer.TimerBackground;
import ru.romeo.cg.surface.timer.TimerLifecycle;

public class ViewNavigation extends ViewGeneric implements SurfaceHolder.Callback  {
    private final String LOG_TAG = ViewNavigation.class.getSimpleName();
    private FragmentManager fm;
    private NavigationProvider nav;

    public ViewNavigation(Context context, FragmentManager fm) {
        super(context);
        this.fm = fm;
        showLog("construct");
        getHolder().addCallback(this);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {
            List<Fragment> list = fm.getFragments();
            Fragment curr = null;
            for (Fragment q : list) {
                if (q.isVisible()) {
                    curr = q;
                    break;
                }
            }
            if (curr == null)
                return true;
            switch (curr.getTag()) {
                case WelcomeF.FRAGMENT_ID:
                    nav.showNavHostFragment();
                    break;
                case "nav":
                    showLog("AMOGUS AMOGUS AMOGUS AMOGUS AMOGUS");
            }
            //Log.w("onTouchEvent", "Click on coord : " + (int) event.getX() + ", " + (int) event.getY());
        }
        return true;//super.onTouchEvent(event);
    }

    public void setNavigationProvider(NavigationProvider provider) {
        nav = provider;
    }
}
