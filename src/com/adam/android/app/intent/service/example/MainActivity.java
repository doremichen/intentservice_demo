/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: MainActivity.java
 * Brief: This is UI
 * 
 * Author: AdamChen
 * Create Date: 2018/3/30
 */

package com.adam.android.app.intent.service.example;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    
    private LinearLayout mUILayout;
   
    private UIReceiver mReceiver = new UIReceiver();
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Utils.Info(this, "[onCreate] enter");
		setContentView(R.layout.activity_main);
		
		mUILayout = (LinearLayout)this.findViewById(R.id.LinearLayout1);
		
		registerReceiver();
	}

	
	
	@Override
    protected void onDestroy() {
        super.onDestroy();
        Utils.Info(this, "[onDestroy] enter");
        
        // Unregister UI receiver
        this.unregisterReceiver(mReceiver);
    }



    private void registerReceiver() {
	    Utils.Info(this, "[registerReceiver] enter");
	    IntentFilter filter = new IntentFilter();
	    filter.addAction(Utils.ACTION_RESPONSE);
	    
	    this.registerReceiver(mReceiver, filter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private int counter = 0;
	
	public void addTask(View v) {
	    Utils.Info(this, "[addTask] enter");
	    int value = counter++;
	    String tagContent = "The input value: " + value;
	    Utils.Info(this, "tag: " + tagContent);
	    TextView textView = new TextView(this);
	    mUILayout.addView(textView);
	    
	    textView.setText(tagContent + " is operating...");
	    textView.setTag(tagContent);
	    
	    // Trriger service to do request
	    Utils.handleRequest(this, tagContent, value);
	    
	}
	
	private void handleResponse(int value, String tag) {
	    Utils.Info(this, "[handleResponse] enter");
	    TextView textView = (TextView)mUILayout.findViewWithTag(tag);
	    textView.setText("The response value is " + value);
	}
	
	/**
	 * 
	 * <h1>UIReceiver</h1> Handle the response from servcie
	 * 
	 * @autor AdamChen
	 * @since 2018/3/30
	 */
	private class UIReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Utils.Info(this, "[onReceive] enter");
            
            String action = intent.getAction();
            if (Utils.ACTION_RESPONSE.equals(action)) {
                int value = intent.getIntExtra(Utils.RESPONSE_DATA, -1);
                String textTag = intent.getStringExtra(Utils.TEXT_TAG);
                Utils.Info(this, "tag: " + textTag);
                handleResponse(value, textTag);
            }
           
        }
	    
	}
}
