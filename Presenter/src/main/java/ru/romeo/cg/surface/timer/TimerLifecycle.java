package ru.romeo.cg.surface.timer;

import java.util.Timer;
import java.util.TimerTask;

import ru.romeo.cg.surface.sprite.Sprite;

public class TimerLifecycle {
    private final Timer timer;
    private final MyTimerTask task;
    private boolean running;

    public TimerLifecycle() {
        timer = new Timer();
        task = new MyTimerTask();
    }


    public void startWork() {
        int PERIOD = 16;// ms, for 60 tic/sec
        int DELAYED_START = 0;// ms
        running = true;
        timer.scheduleAtFixedRate(task, DELAYED_START, PERIOD);
    }

    public boolean stopWork() {
        running = false;
        timer.cancel();
        return running;
    }

    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            if (running) setCurrentFrameCounter();
        }
    }

    private void setCurrentFrameCounter() {
        Sprite.updateNextFrame();
    }
}
