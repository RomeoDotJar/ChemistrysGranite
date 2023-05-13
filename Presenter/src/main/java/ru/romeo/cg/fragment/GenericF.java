package ru.romeo.cg.fragment;

import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import ru.romeo.cg.R;
import ru.romeo.cg.activity.ActivityMain;
import ru.romeo.cg.provider.NavigationProvider;
import ru.romeo.cg.surface.ViewGeneric;

public abstract class GenericF extends Fragment {
    public final static String FRAGMENT_ID = null;

    public abstract String getFI();

    public void onFocusGain() {};
}
