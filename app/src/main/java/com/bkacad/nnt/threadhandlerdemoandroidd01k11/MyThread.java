package com.bkacad.nnt.threadhandlerdemoandroidd01k11;

import android.os.Handler;
import android.os.Message;

public class MyThread extends  Thread{
    private static final int MIN = 1, MAX = 100;
    private Handler handler;

    static final int NUMBER_FROM_THREAD = 1;
    static final int FINISH_FROM_THREAD = -1;

    public MyThread(Handler handler){
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        for(int i = MIN; i <= MAX ; i++){

            try {
                Thread.sleep(100);
                // Gui du lieu ve Main - xu ly sau
                Message msg = handler.obtainMessage(NUMBER_FROM_THREAD, i);
                handler.sendMessage(msg);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        handler.sendEmptyMessage(FINISH_FROM_THREAD);
    }
}
