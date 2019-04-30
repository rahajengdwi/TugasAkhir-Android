package com.example.cobata.utils;


import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cobata.interfaces.BaseRequestListener;
import com.example.cobata.interfaces.Const;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.VolleyLog.TAG;

public class RequestUtils {

    public static void getAPI(String url, int requestMethod, JSONObject param, final BaseRequestListener listener, Context context) {

        Log.d(TAG, "getAPI: "+ url);

        try {
            Log.d(TAG, param.toString(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(requestMethod, url , param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d(Const.TAG, "onResponse: " +response.toString(4));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listener.onRequestFinish(response, response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(Const.TAG, "onErrorResponse: " + error.getMessage());
                listener.onRequestFinish(null, error.getMessage());
            }
        }

        );
        VolleyUtils.getInstance(context).addToRequestQueue(request);

    }
}
