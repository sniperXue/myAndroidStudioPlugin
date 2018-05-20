package com.vpedak.testsrecorder.core;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.IntentFilter;
import android.util.Log;
import com.vpedak.testsrecorder.core.events.PressBackAction;
import com.vpedak.testsrecorder.core.events.RecordingEvent;

import java.lang.reflect.Field;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityListener implements EventWriter.EventWriterListener {
    private Instrumentation instr;
    private volatile Instrumentation.ActivityMonitor monitor;
    private Activity activity = null;
    private ActivityProcessor activityProcessor;
    private long uniqueId;
    private boolean wasEvent = false;
    private EventWriter eventWriter;
    Timer timer0 = null;
    //需要定义一个栈，来存放很多个Activity
    private Stack<Activity> stack = new Stack<Activity>();

    //这两个属性是什么意思...........有什么用，后面的代码一直看不懂，好像是能够随时获取并操作activity的这么一个对象
    //Field用到了java反射机制，就是让对象自己告诉你他们的属性和属性值
    private Field fResumed;
    private Field fStopped;

    public ActivityListener(Instrumentation instr, Activity activity, long uniqueId) {
        this.instr = instr;
        IntentFilter filter = null;
        this.monitor = instr.addMonitor(filter, null, false);
        this.activity = activity;
        this.uniqueId = uniqueId;

        eventWriter = new EventWriter(uniqueId, this);
        //创建一个Activity加工者
        activityProcessor = new ActivityProcessor(uniqueId, instr, eventWriter);

        activityProcessor.processActivity(activity);

        //Q1.这段干什么没有看懂........
        try {
            //获取一个类的所有字段，但是这个类叫（mResumed、mStopped）并没有找到
            fResumed = Activity.class.getDeclaredField("mResumed");
            //作用就是让我们在用反射时访问私有变量
            fResumed.setAccessible(true);

            fStopped = Activity.class.getDeclaredField("mStopped");
            fStopped.setAccessible(true);
        } catch (NoSuchFieldException e) {
            Log.e(ActivityProcessor.ANDRIOD_TEST_RECORDER, "NoSuchFieldException", e);
        }
    }

    public void start() {
        if(null == timer0) {
            timer0 = new Timer();
        }
        timer0.schedule(new ActivityTask(this), 300L, 300L);
    }

    public void stop() {
        timer0.cancel();
    }

    public synchronized void  check() {
        //从监听器中拿出最近进去的Activity
        Activity test = monitor.getLastActivity();
        //如果拿出来的不为空，而且理解不了（）为什么不等于Activity，可能是不为最初初始化传进来的那个activity，没有停止测试该activity
        if (test != null && test != activity && !isStopped(test)) {
            boolean push = true;
            if (isResumed(test)) {
                try {
                    if (stack.peek().equals(test) ) {
                        stack.pop();
                        push = false;
                        //wasEvent初始化是false
                        if (!wasEvent) {
                            //如果是一个事件，就把事件写下来
                            eventWriter.writeEvent(new RecordingEvent(new Espresso(), new PressBackAction(), "Press on the back button"));
                        }
                    }
                } catch (EmptyStackException e) {
                    //no op;
                }
            }
            //如果是一个新的activity，在栈里面重新压如一个activity，
            if (push) {
                stack.push(activity);
            }
            //然后从android.monitor中获取新的activity
            activity = test;

            //Log.i("12345", activity.getLocalClassName());
            //对新获取的activity进行加工
            activityProcessor.processActivity(activity);
        }
        wasEvent = false;

        activityProcessor.processAllViews();
    }

    @Override
    public synchronized void onEventWritten() {
        wasEvent = true;
    }

    //就是检查有没有
    public static class ActivityTask extends TimerTask {
        private ActivityListener checker;

        public  ActivityTask(ActivityListener checker) {
            //初始化一个“检查器”还是ActivityListener类型
            this.checker = checker;
        }

        @Override
        public void run() {
            //又会调“检查器”的check方法
            checker.check();
        }
    }


    private boolean isResumed(Activity activity) {
        try {
            return (Boolean) fResumed.get(activity);
        } catch (IllegalAccessException e) {
            Log.e(ActivityProcessor.ANDRIOD_TEST_RECORDER, "IllegalAccessException", e);
            return false;
        }
    }

    private boolean isStopped(Activity activity) {
        try {
            return (Boolean) fStopped.get(activity);
        } catch (IllegalAccessException e) {
            Log.e(ActivityProcessor.ANDRIOD_TEST_RECORDER, "IllegalAccessException", e);
            return false;
        }
    }
}
