package com.ffh.e_charging.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ffh.e_charging.Listener.OnFetchDataListener;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by innershows on 15/11/30.
 */
public class Net {


    private static RequestQueue queue;

    public static void init(Context context) {
        queue = Volley.newRequestQueue(context);
    }


    public static void get(String url, final OnFetchDataListener listener) {
        if (listener == null) {
            return;
        }
        queue.add(new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                request = null;
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;

                //验证错误
                if (networkResponse.statusCode == 401) {
                    getAccessKey();
                }
                request = null;
                listener.onFail(networkResponse.statusCode, new String(networkResponse.data));
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();

                map.put("Content-Type", "application/json");
                map.put("Authorization", "Basic " + PreferenceUtils.getString("accessToken", ""));

                return map;
            }
        });
    }

    private static StringRequest request;

    public static void post(String url, final OnFetchDataListener listener, final Map params) {

        if (listener == null) {
            return;
        }
        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                request = null;
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;

                //验证错误
                if (networkResponse.statusCode == 401) {
                    getAccessKey();
                    return;
                }
                request = null;
                listener.onFail(networkResponse.statusCode, new String(networkResponse.data));
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();

                map.put("Content-Type", "application/json");
                map.put("Authorization", "Basic " + PreferenceUtils.getString("accessToken", ""));

                return map;
            }

//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return params;
//            }


            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                String pa = gson.toJson(params);

                return pa.getBytes();
            }
        };
        queue.add(request);
    }

    /**
     * {"status":1,"accessToken":"ZWNoZ2JlNzVhZjJmNGE5Njk2ZGI3MjY3MGZjYTNjZjU6ZWNoYXJnaW5n"}
     */
    public static void getAccessKey() {
        x.http().get(new RequestParams(API.ACCESS_TOKEN_GET), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);

                    int status = obj.optInt("status");
                    if (1 == status) {
                        String accessToken = obj.optString("accessToken");
                        PreferenceUtils.put("accessToken", accessToken);

                        if (request != null) {
                            queue.add(request);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


}
