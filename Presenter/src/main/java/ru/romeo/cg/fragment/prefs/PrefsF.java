package ru.romeo.cg.fragment.prefs;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;
import androidx.recyclerview.widget.RecyclerView;

import ru.romeo.cg.R;
import ru.romeo.cg.activity.ActivityMain;
import ru.romeo.cg.fragment.GenericF;
import ru.romeo.cg.fragment.home.HomeF;
import ru.romeo.cg.surface.ViewGeneric;
import ru.romeo.cg.surface.ViewNavigation;

public class PrefsF extends PreferenceFragmentCompat {
    public static final String FRAGMENT_ID = "settings";

    public static PrefsF newInstance() {
        PrefsF frag = new PrefsF();
        return frag;
    }

    public String getFI() {
        return FRAGMENT_ID;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pref_root, rootKey);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_settings,
                container,
                false
        );
    }

    protected void initCanvas() {
        ViewNavigation surface = new ViewNavigation(getContext(), getActivity().getSupportFragmentManager());
        surface.setNavigationProvider(((ActivityMain)getActivity()));
        FrameLayout layout = getView().requireViewById(R.id.layoutView);
        layout.addView(surface);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        initCanvas();
        super.onViewCreated(view, savedInstanceState);
    }
}
