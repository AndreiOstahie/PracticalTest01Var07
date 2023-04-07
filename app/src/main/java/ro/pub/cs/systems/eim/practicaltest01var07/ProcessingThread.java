package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    private int num1, num2, num3, num4;

    public ProcessingThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Log.d("[TAG]", "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());
        while (isRunning) {
            sendMessage();
            sleep();
        }
    }

    private void sendMessage() {
        num1 = random.nextInt(100);
        num2 = random.nextInt(100);
        num3 = random.nextInt(100);
        num4 = random.nextInt(100);

        Intent intent = new Intent();
        intent.setAction(Constants.ACTION);
        intent.putExtra(Constants.RAND1, num1);
        intent.putExtra(Constants.RAND2, num2);
        intent.putExtra(Constants.RAND3, num3);
        intent.putExtra(Constants.RAND4, num4);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
