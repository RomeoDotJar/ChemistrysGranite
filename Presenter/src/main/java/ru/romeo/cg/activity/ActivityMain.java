package ru.romeo.cg.activity;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceFragmentCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import ru.romeo.app.AppStart;
import ru.romeo.cg.R;
import ru.romeo.cg.databinding.ActivityMainBinding;
import ru.romeo.cg.fragment.about_lesson.AboutLessonF;
import ru.romeo.cg.fragment.welcome.WelcomeF;
import ru.romeo.cg.surface.ViewNavigation;
import ru.romeo.data.gen.DataGen;
import ru.romeo.data.pref.PrefHelper;
import ru.romeo.cg.provider.NavigationProvider;
import ru.romeo.cg.surface.ViewGeneric;
import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.Lesson;

public class ActivityMain extends AppCompatActivity implements NavigationProvider {

    private ActivityMainBinding binding;
    private FragmentContainerView container = null;

    public NavHostFragment getNavHostFragment() {
        return navHostFragment;
    }

    private NavHostFragment navHostFragment;
    private AppBarLayout appbar;
    private BottomNavigationView botNav;
    private String lastFragTag = null;
    private int lastEntryCount = 0;
    private int bsSize = 0;
    //private boolean isOnePane;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PrefHelper.init(this);
        initView();
        initToolbar();
        initBotNav();
        initFragments();
        startFragmentWelcome();//isOnePane);
        initCanvas();
        setBackStackChangedListener();
    }

    private void initView() {
        appbar = requireViewById(R.id.appbar);
        botNav = requireViewById(R.id.botNav);
    }

    private void setBackStackChangedListener() {
        getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        Log.d("BACKSTACK_LISTENER", "Received backstack change. TAG = " + lastFragTag);
                        FragmentManager fm = getSupportFragmentManager();

                        if (fm != null) {
                            int entryCount = fm.getBackStackEntryCount();
                            Log.d("BACKSTACK_LISTENER", "Received backstack change. ENTRY_COUNTS = {NEW: "+entryCount+", LAST: "+lastEntryCount+"}");;

                            if (entryCount < lastEntryCount) {
                                String tag = lastFragTag;
                                if (Objects.equals(null, tag) || tag.equals(WelcomeF.FRAGMENT_ID))
                                    finishAffinity();
                                else if (tag.equals("settings") || tag.equals(AboutLessonF.FRAGMENT_TAG)) {
                                    showSupportActionBar();
                                    showBotNav();
                                } else if (tag.equals(navHostFragment.getTag())) {
                                    hideBotNav();
                                    hideSupportActionBar();
                                }
                            }
                            else {
                                String tag = lastFragTag;
                                if (tag.equals(AboutLessonF.FRAGMENT_TAG)) {
                                    hideSupportActionBar();
                                }
                            }

                            for (Fragment frag : fm.getFragments()) {
                                if (frag.isVisible()) {
                                    lastFragTag = frag.getTag();
                                    break;
                                }
                            }
                            lastEntryCount = entryCount;
                        }
                    }
                });
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        public static SettingsFragment newInstance() {
            return new SettingsFragment();
        }

        private SettingsFragment() {

        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.pref_root, rootKey);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if (item.getItemId() == R.id.action_settings) {
                Log.d("ActivityMain", "BEBRA BEBRA BEBRA BEBRA BEBRA BEBRA BEBRA BEBRA BEBRA BEBRA");
                //FrameLayout layout = requireViewById(R.id.layoutView);
                //layout.removeAllViews();
                hideSupportActionBar();
                hideBotNav();

                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
                        .add(R.id.container_frag, SettingsFragment.newInstance(), "settings")
                        .hide(getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getFragments().size()-2))
                        .addToBackStack(null)
                        .commit();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        // assigning ID of the toolbar to a variable
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // using toolbar as ActionBar
        setSupportActionBar(toolbar);
    }

    private void initBotNav() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_item_rest, R.id.nav_item_learn, R.id.nav_item_peek)
                .build();
        navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.container_frag);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.botNav, navController);
    }

    private void initFragments() {
        container = findViewById(R.id.container_frag);
        //isOnePane = isOnePaneMode();
    }

    //private boolean isOnePaneMode() {
    //    return fragmentAboutPeople == null;
    //}

    protected void initCanvas() {
        ViewNavigation surface = new ViewNavigation(getBaseContext(), getSupportFragmentManager());
        surface.setNavigationProvider(this);
        FrameLayout layout = requireViewById(R.id.layoutView);
        layout.addView(surface);
    }

    public void startFragmentWelcome() {//Boolean isOnePane) {
        WelcomeF fragWelcome = WelcomeF.newInstance();//isOnePane);
//        getSupportFragmentManager()
//                .popBackStack();
        getSupportFragmentManager().beginTransaction()
                .hide(navHostFragment)
                .commit();
        addFragment(fragWelcome, fragWelcome.getFI());
        hideSupportActionBar();
        hideBotNav();
    }

    public void showSupportActionBar() {
        appbar.setVisibility(VISIBLE);
    }

    public void hideSupportActionBar() {
        appbar.setVisibility(INVISIBLE);
    }

    public void showBotNav() {
            //botNav.setTranslationY(256);
            botNav.setVisibility(VISIBLE);
            botNav.animate()
                    .alpha(1f)
                    .translationY(0)
                    .setDuration(1000);
    }

    public void hideBotNav() {
            //Log.d(FRAGMENT_ID, FRAGMENT_ID+": detected visible botnav");
            //botNav.setTranslationY(0);
            botNav.animate()
                    .alpha(0f)
                    .translationY(256)
                    .setDuration(1000)
                    .withEndAction(() ->
                            botNav.setVisibility(INVISIBLE)
                    );
    }

    @Override
    public void addFragment(Fragment frag, String tag) {
        Fragment last = getSupportFragmentManager().findFragmentByTag(lastFragTag);
        if (last != null)
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.slide_in, R.anim.slide_out)
                    .add(R.id.container_frag, frag, tag)
                    .hide(last)
                    .addToBackStack(null)
                    .commit();
        else
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.slide_in, R.anim.slide_out)
                    .add(R.id.container_frag, frag, tag)
                    .addToBackStack(null)
                    .commit();
        lastFragTag = tag;
    }

    @Override
    public void showNavHostFragment() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.stall, R.anim.fade_out)
                .show(navHostFragment)
                .hide(getSupportFragmentManager().getFragments().get(1))
                .addToBackStack(null)
                .commit();
        lastFragTag = navHostFragment.getTag();
        showSupportActionBar();
        showBotNav();
    }
}