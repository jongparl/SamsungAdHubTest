package com.example.com.criteo.test;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.sec.android.ad.AdHubView;
import com.sec.android.ad.AdNotificationListener;
import com.sec.android.ad.info.AdSize;


public class CriteoMainActivity extends Activity {
	
	private TextView mTextView;
	private Runnable mRunnable;
	private Handler  mHandler;
	
	private TimerTask mTimerTask;
	private Timer 	  mTimer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.d("Criteo", "onCreate");
		
		//mTextView = (TextView)findViewById(R.id.textView1);
		
		mRunnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.d("Criteo", "Runnable is running");
				
			}			
		};
		
		//mHandler = new Handler();
		//mHandler.postDelayed(mRunnable, 1000);
		
		//CreateTimer(2000);
		
AdHubView adhubView;
        
        adhubView = (AdHubView)findViewById(R.id.AdLayout);
        adhubView.init(this, "xv0c000000001p", AdSize.BANNER_640x100);
        adhubView.setRefreshRate(15*1000);
        
        adhubView.setListener(new AdNotificationListener() {
        	public void onAdReceived(AdHubView arg0) {
        		// TODO Auto-generated method stub
        		Toast.makeText(CriteoMainActivity.this, "onAdReceived", Toast.LENGTH_SHORT).show();
        		Log.d("Criteo", "onAdReceived called");
        	}
        	public void onAdFailed(AdHubView arg0, Exception arg1) {
        		// TODO Auto-generated method stub
        		Toast.makeText(CriteoMainActivity.this, "onAdFailed: "+arg1.toString() , Toast.LENGTH_SHORT).show();
        		Log.d("Criteo", "onAdFailed called: " + arg1.toString());
        	}
        });
        
        adhubView.startAd();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.criteo_main, menu);
		return true;
	}
	
	public void CreateTimer(int duration){
		
		mTimerTask = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.d("Criteo", "Timer task is running.");
			}
			
		};
		
		mTimer = new Timer();
		mTimer.schedule(mTimerTask, duration, duration);
	
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mTimerTask.cancel();
		mTimer.cancel();
		super.onDestroy();
		
		
	}

}
