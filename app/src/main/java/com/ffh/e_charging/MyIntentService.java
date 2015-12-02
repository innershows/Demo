package com.ffh.e_charging;

import android.app.IntentService;
import android.content.Intent;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {


    public static boolean isStarted = false;
    //60分钟
    public static int currentMinute = 59;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MyIntentService() {
        super("count_down");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        currentMinute = intent.getIntExtra("retain", 0);

        isStarted = true;
        while (currentMinute > 0) {
            try {
                //一分钟
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentMinute--;
            Intent intent1 = new Intent(this, MyReceiver.class);
            intent1.putExtra("count", currentMinute);
            sendBroadcast(intent1);
        }

        isStarted = false;
    }
}
