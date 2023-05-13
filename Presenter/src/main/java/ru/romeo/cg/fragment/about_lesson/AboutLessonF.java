package ru.romeo.cg.fragment.about_lesson;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.gson.Gson;

import ru.romeo.cg.R;
import ru.romeo.cg.activity.ActivityMain;
import ru.romeo.cg.fragment.GenericF;
import ru.romeo.cg.fragment.read_lesson.ReadLessonF;
import ru.romeo.domain.entity.lesson.Lesson;

public class AboutLessonF extends DialogFragment {
    public static final String FRAGMENT_TAG = "about_lesson";
    public static final String LESSON_GSON_KEY = "content";

    private TextView title, desc, order;
    private ImageView prev;
    private ProgressBar progressBar;
    private Button reject, confirm;
    private Lesson lesson;

    public static AboutLessonF newInstance(
        String lessonGson
    ) {
        AboutLessonF frag = new AboutLessonF();
        Bundle bundle = new Bundle();
        bundle.putString(LESSON_GSON_KEY, lessonGson);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(
                R.layout.dialog_about_lesson,
                null
        );

        AlertDialog alert = new AlertDialog.Builder(getActivity())
                .setView(view)
                //.setMessage(R.string.expectingContent)
                .create();

        initView(view);
        initContent();

        return alert;
    }

    private void onConfirmClick() {
        return;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(FRAGMENT_TAG, "onResume");

        if (getDialog() == null)
            return;

        initView(getView());
        initContent();

        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.96f);
        getDialog().getWindow().setLayout(width, getDialog().getWindow().getAttributes().height);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.round_corners_dialog);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.dialog_about_lesson,
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
        title = view.requireViewById(R.id.labTitleAL);
        desc = view.requireViewById(R.id.labDescAL);
        order = view.requireViewById(R.id.labOrderAL);
        prev = view.requireViewById(R.id.imgPrevAL);
        progressBar = view.findViewById(R.id.progressBarAL);

        confirm = view.findViewById(R.id.btnConfirmAL);
        confirm.setOnClickListener(new OnConfirmListener());
        reject = view.findViewById(R.id.btnRejectAL);
        reject.setOnClickListener(new OnRejectListener());
    }

    private void initContent() {
        lesson = new Gson().fromJson(getArguments().getString(LESSON_GSON_KEY), Lesson.class);

        title.setText(lesson.getTitle());
        desc.setText(lesson.getDesc());
        order.setText(
                "§"+lesson.getOrder()/10000+"."+(lesson.getOrder() - lesson.getOrder()/10000*10000 + 1 )
        );
        prev.setImageResource(R.drawable.ic_launcher_background);

        Drawable draw=getResources().getDrawable(R.drawable.lesson_progress_bar);
        progressBar.setProgressDrawable(draw);
        progressBar.setProgress(lesson.getProgress(), true);
    }

    private class OnRejectListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            getDialog().dismiss();
        }
    }

    private class OnConfirmListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            getDialog().dismiss();
            ((ActivityMain)getActivity())
                    .addFragment(ReadLessonF.newInstance(lesson.toString()), ReadLessonF.FRAGMENT_ID);
        }
    }
}
