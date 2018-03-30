/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: Utils.java
 * Brief: This is utility class
 * 
 * Author: AdamChen
 * Create Date: 2018/3/30
 */

package com.adam.android.app.intent.service.example;

import java.lang.ref.SoftReference;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * <h1>Utils</h1>
 * 
 * @autor AdamChen
 * @since 2018/3/30
 */
public final class Utils {
    
    // Debug TAG
    private static final String TAG = "Demo";
    
    public static final String ACTION_HANDLE_REQUEST = "com.adam.service.handle.request";
    
    public static final String ACTION_RESPONSE = "com.adam.service.send.response";
    
    // Use to packed the request
    public static final String REQUEST_DATA = "request.data";
    
    // Use to packed the response
    public static final String RESPONSE_DATA = "response.data";
    
    // Use to record the text tag
    public static final String TEXT_TAG = "text.tag";
    
    
    // Log function
    public static void Info(Object obj, String str) {
        Log.i(TAG, obj.getClass().getSimpleName() + ": " + str);
    }
    
    public static void Info(Class<?> cls, String str) {
        Log.i(TAG, cls.getSimpleName() + ": " + str);
    }

    /**
     * 
     * <h1>handleRequest</h1> Handle request
     *
     * @param context
     * @param Tag TODO
     * @param value
     * @return void
     *
     */
    public static void handleRequest(Context context, String Tag, int value) {
        
        Utils.Info(Utils.class, "[handleRequest] enter");
        
        Intent intent = new Intent();
        intent.setClassName(DemoService.PAKAGE_NAME, DemoService.CLASS_CANONICAL_NAME);
        
        intent.setAction(ACTION_HANDLE_REQUEST);
        intent.putExtra(REQUEST_DATA, value);
        intent.putExtra(TEXT_TAG, Tag);
        
        SoftReference<Context> reference = new SoftReference<Context>(context);
        Context cnt = reference.get();
        cnt.startService(intent);

    }
    
    /**
     * 
     * <h1>handleResponse</h1> Handle response
     *
     * @param context
     * @param value
     * @param tag TODO
     * @return void
     *
     */
    public static void handleResponse(Context context, int value, String tag) {
        
        Utils.Info(Utils.class, "[handleResponse] enter");
        
        Intent intent = new Intent();
        intent.setAction(Utils.ACTION_RESPONSE);
        intent.putExtra(Utils.RESPONSE_DATA, value);
        intent.putExtra(TEXT_TAG, tag);
        
        SoftReference<Context> reference = new SoftReference<Context>(context);
        Context cnt = reference.get();
        cnt.sendBroadcast(intent);
    }

}
