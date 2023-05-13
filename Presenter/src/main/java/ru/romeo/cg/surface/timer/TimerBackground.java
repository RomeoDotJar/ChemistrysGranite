package ru.romeo.cg.surface.timer;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ru.romeo.cg.R;
import ru.romeo.cg.surface.particle.GenericCircle;
import ru.romeo.cg.surface.sprite.Sprite;

public class TimerBackground {

    protected Context context;
    private final String LOG_TAG = TimerBackground.class.getSimpleName();
    private final Timer timer;
    private final MyTimerTask task;
    private boolean running;


    public TimerBackground(Context context, SurfaceHolder surfaceHolder) {
        showLog("constructor");
        this.context = context;
        timer = new Timer();
        task = new MyTimerTask(surfaceHolder, context);
    }


    public void startWork() {
        // ms, for 30 tic/sec
        int PERIOD = 32;//16.32.64
        // ms
        int DELAYED_START = 0;
        running = true;
        timer.scheduleAtFixedRate(task, DELAYED_START, PERIOD);
    }

    public boolean stopWork() {
        running = false;
        timer.cancel();
        return running;
    }

    private void showLog(String message) {
        Log.d(LOG_TAG, LOG_TAG + ": " + message);
    }

    public void updateTouchCoordinate(int newX, int newY) {
        task.updateTouchCoordinate(newX, newY);
    }

    private class MyTimerTask extends TimerTask {
        private static final String LOG_TAG = TimerBackground.class.getSimpleName()+MyTimerTask.class.getSimpleName();
        private static final int TOUCH_LIFESPAN = 12;
        private static final int SPAWN_COOLDOWN = 55;
        private static final int MAX_POINTS_BIG = 3;
        private final SurfaceHolder surfaceHolder;

        private static int step, touchX, touchY, touchAge, spawnTick;
        private static DisplayMetrics dm;
        private static ArrayList<GenericCircle> points, pointsBig;
        private static Paint p, pBg, pC, pCB;

        static {
            points = new ArrayList<>();
            pointsBig = new ArrayList<>();
            spawnTick = SPAWN_COOLDOWN;

            p = new Paint(Paint.ANTI_ALIAS_FLAG);

            pBg = new Paint(Paint.DITHER_FLAG);
            //pBg.setDither(true);
            //pBg.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));

            pC = new Paint(Paint.ANTI_ALIAS_FLAG);

            pCB = new Paint(Paint.ANTI_ALIAS_FLAG);
        }

        protected int getColor(int res_id) {
            return ContextCompat.getColor(context, res_id);
        }

        public MyTimerTask(SurfaceHolder surfaceHolder, Context context) {
            this.surfaceHolder = surfaceHolder;
            initDisplayMetrics(context);
            initColors();
            Shader shader = createBg(
                    (float) Math.sin((float)Sprite.stepCount/60)*240,
                    (float) Math.sin((float)Sprite.stepCount/120)*120,
                    -Sprite.stepCount%(dm.heightPixels* 2L)
            );
            pBg.setShader(shader);
        }

        private static void initDisplayMetrics(Context context) {
            showLog("initDisplayMetrics");
            dm = context.getResources().getDisplayMetrics();
            showLog("ScreenSize : x =  " + dm.widthPixels + ", y = " + dm.heightPixels);
            int minPx = dm.widthPixels;
            int minPy = dm.heightPixels;
            step = minPx > 1000 ? 128 : 80;
            //showLog("step = " + step);
            int delta = (minPx - (step << 3) >> 1);
            //showLog("delta = " + delta);
            //startX = startY = delta + (step >> 1);
            //stopX = minPx - delta - (step >> 1);
            //stopY = minPy - delta - (step >> 1);
        }

        public void updateTouchCoordinate(int newX, int newY) {
            touchX = newX;
            touchY = newY;
            touchAge = 0;
        }

        @Override
        public void run() {
            long t0 = System.nanoTime();
            if (running) {
                Canvas canvas = surfaceHolder.lockCanvas();
                if (canvas != null) {
                    try {
                        draw(canvas);
                        //Field.drawFieldLines(canvas);
                    } finally {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
            long t1 = System.nanoTime();
            long dt = (t1 - t0) / 1000000 + 1;
            if (dt > 25) showLog("dt = " + dt);
            if (dt > 32) showLogErr("Too long draw canvas !!!");
        }

        private void initColors() {
            pC.setColor(getColor(R.color.white)); // R.color.white
            pC.setAlpha(100);

            pCB.setColor(getColor(R.color.white)); // R.color.white
            pCB.setAlpha(55);
        }

        private Shader createBg(float frameX, float frameY, float offsetY) {
            int c0 = getColor(R.color.bg0l_default);//0xFFAAFBAA;//0xff84F9A0;
            int c1 = getColor(R.color.bg1l_default);//0xFFBFE8FA;//0xff8fE3f4;
            showLog(c0+", "+c1);
            LinearGradient shader = new LinearGradient(dm.widthPixels/2+frameX, offsetY, dm.widthPixels/2-frameX, dm.heightPixels+frameY+offsetY,
                    c0,//Color.BLACK,
                    c1,//Color.WHITE,
                    Shader.TileMode.MIRROR);
            return shader;// 0xFF84F9A0 0xff8fE3f4
        }

        private void draw(Canvas canvas) {
            float frame = (float) Math.sin((double) Sprite.stepCount/600);

            // #84fab0 → #8fd3f4
            //showLog(frame*200+"");
            canvas.drawRect(
                    new Rect(0, 0, dm.widthPixels, dm.heightPixels),
                    pBg
            );
            if (spawnTick > SPAWN_COOLDOWN) {
                spawnTick = 0;
                points.add(new GenericCircle(
                        (int) (Math.random()*dm.widthPixels)+20,
                        0,
                        GenericCircle.genSpd(5, 8),
                        GenericCircle.genSpd(64, 128)
                ));
            }
            for (int i = 0; i < MAX_POINTS_BIG; i++) {
                if (pointsBig.size() <= i) {
                    pointsBig.add(new GenericCircle(
                            (int) (Math.random()*dm.widthPixels)/MAX_POINTS_BIG+dm.widthPixels/MAX_POINTS_BIG*i+20,
                            dm.heightPixels/MAX_POINTS_BIG*i,
                            GenericCircle.genSpd(2, 4),
                            GenericCircle.genSpd(256, 512)
                    ));
                }
                GenericCircle pt = pointsBig.get(i);
                pt.update();
                if (pt.y-pt.getWeight() > dm.heightPixels) {
                    int weight = GenericCircle.genSpd(256, 512);
                    pointsBig.set(i, new GenericCircle(
                            (int) (Math.random()*dm.widthPixels)/MAX_POINTS_BIG+dm.widthPixels/MAX_POINTS_BIG*i+20,
                            -weight,
                            GenericCircle.genSpd(2, 4),
                            weight
                    ));
                    continue;
                }
                canvas.drawCircle(
                        pt.x,
                        pt.y,
                        pt.getWeight(),
                        pCB
                        );
            }
            for (int i = 0; i < points.size(); i++) {
                GenericCircle pt = points.get(i);
                pt.update();
                if (pt.y-pt.getWeight() > dm.heightPixels) {
                    points.remove(pt);
                    continue;
                }
                float rad = Math.abs(pt.getWeight()*(float) Math.sin((double)pt.y/dm.heightPixels))/1.6f;
                //if (Sprite.stepCount%5==0)
                //    showLog(pt.x/(float) dm.widthPixels+" "+pt.y/(float) dm.heightPixels+" "+rad);

                canvas.drawCircle(
                        pt.x,
                        pt.y,
                        rad+24,
                        pC
                );
            }
            spawnTick++;

            /*p.setColor(0xFF84F9A0);
            p.setAlpha(255);
            canvas.drawRect(
                    new Rect(0, 0, dm.widthPixels, dm.heightPixels), p);
            showLog(frame+"");
            p.setColor(0xff8fE3f4);
            p.setAlpha((int) Math.abs(frame*255));
            canvas.drawRect(
                    new Rect(0, 0, dm.widthPixels, dm.heightPixels), p);
*/
            p.setColor(Color.argb(Math.max(0, ((float)TOUCH_LIFESPAN-touchAge)/TOUCH_LIFESPAN), 0.8f, 0.8f, 1));
            canvas.drawCircle(touchX, touchY, 24+touchAge*10f, p);
            touchAge++;
        }

        private static void showLog(String message) {
            Log.d(LOG_TAG, LOG_TAG + ": " + message);
        }

        private static void showLogErr(String message) {
            Log.e(LOG_TAG, LOG_TAG + ": " + message);
        }
    }

}
