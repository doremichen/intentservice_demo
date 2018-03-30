/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: DemoService.java
 * Brief: This service would handle the request from UI
 *        and then send response to the UI
 * 
 * Author: AdamChen
 * Create Date: 2018/3/30
 */

package com.adam.android.app.intent.service.example;

import android.app.IntentService;
import android.content.Intent;

/**
 * <h1>DemoService</h1>
 * 
 * @autor AdamChen
 * @since 2018/3/30
 */
public class DemoService extends IntentService {
    
    static final String PAKAGE_NAME = DemoService.class.getPackage().getName();
    private static final String CLASS_NAME = DemoService.class.getSimpleName();
    static final String CLASS_CANONICAL_NAME = DemoService.class.getCanonicalName();

    public DemoService() {
        super(CLASS_NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Utils.Info(this, "[onHandleIntent] enter");
        
        if (intent == null) {
            throw new RuntimeException("the intent is null");
        }
        
        String action = intent.getAction();
        
        if (Utils.ACTION_HANDLE_REQUEST.equals(action)) {
            
            final int value = intent.getIntExtra(Utils.REQUEST_DATA, -1);
            final String tag = intent.getStringExtra(Utils.TEXT_TAG);
            doLongOperation(value, tag);
            
        }
    }
    
    /**
     * 
     * <h1>doLongOperation</h1> do long operation process
     *
     * @param value
     * @param tag TODO
     * @return void
     *
     */
    private void doLongOperation(int value, String tag) {
        Utils.Info(this, "[doLongOperation] enter");
        
        try {
            
            Thread.sleep(1000L);
            
            // Calculate the value
            int retValue = (int) (value + Math.random()*100);
            
            // Send the response data to UI
            Utils.handleResponse(this, retValue, tag);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Utils.Info(this, "[onDestroy] enter");
    }
    
    

}
