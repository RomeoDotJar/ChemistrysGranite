package ru.romeo.cg.provider;

import androidx.fragment.app.Fragment;

public interface NavigationProvider {

    void addFragment(Fragment frag, String tag);

    void showNavHostFragment();
}
