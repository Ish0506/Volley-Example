package com.example.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyVolleyRequest {
    private static MyVolleyRequest instance;
    private RequestQueue requestQueue;
    private Context context;
    private ImageLoader imageLoader;
    private IVolley iVolley;

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    private MyVolleyRequest(Context context, IVolley iVolley){
        this.context = context;
        this.iVolley = iVolley;
        requestQueue = getRequestQueue();
        this.imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private LruCache<String, Bitmap> cache = new LruCache<>(10);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);

            }
        });

    }
    private MyVolleyRequest(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
        this.imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private LruCache<String, Bitmap> cache = new LruCache<>(10);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);

            }
        });

    }
    public static synchronized MyVolleyRequest getInstance(Context context){
        instance = new MyVolleyRequest(context);
        return instance;
    }
    public static synchronized MyVolleyRequest getInstance(Context context, IVolley iVolley ){
        instance = new MyVolleyRequest(context, iVolley);
        return instance;


    }
    private RequestQueue getRequestQueue(){
        if(requestQueue== null){
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;

    }
    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
    //GET METHOD
    public void getRequest(String url){
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                iVolley.onResponse(response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iVolley.onResponse(error.getMessage());

            }
        });
        addToRequestQueue(getRequest);
    }
    //Post
    public void postRequest(String url){
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iVolley.onResponse(response.toString());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iVolley.onResponse(error.getMessage());
            }
        })
        {
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name"," Charlie");
                params.put("value", "This is value from android app");
                return params;
            }
        };
        addToRequestQueue(postRequest);
    }
    //put
    public void putRequest(String url){
        StringRequest postRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iVolley.onResponse(response.toString());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iVolley.onResponse(error.getMessage());
            }
        })
        {
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name"," Charlie");
                params.put("value", "This is value from android app");
                return params;
            }
        };
        addToRequestQueue(postRequest);
    }
    //patch
    public void patchRequest(String url){
        StringRequest postRequest = new StringRequest(Request.Method.PATCH, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iVolley.onResponse(response.toString());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iVolley.onResponse(error.getMessage());
            }
        })
        {
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name"," Charlie");
                params.put("value", "This is value from android app");
                return params;
            }
        };
        addToRequestQueue(postRequest);
    }

    public void deleteRequest(String url){
        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iVolley.onResponse(response.toString());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iVolley.onResponse(error.getMessage());
            }
        });
        addToRequestQueue(deleteRequest);

    }
}
