package com.example.cobata.utils;


import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cobata.interfaces.BaseRequestListener;
import com.example.cobata.interfaces.Const;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.VolleyLog.TAG;

public class RequestUtils {

    public static void getAPI(String url, int requestMethod, JSONObject param, final BaseRequestListener listener, Context context) {

        Log.d(Const.TAG, "getAPI: "+ url);

        try {
            Log.d(Const.TAG, param.toString(4));
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
                if(error instanceof ServerError)
                    Log.d(Const.TAG, "onErrorResponse: server error");
                else if(error instanceof AuthFailureError)
                    Log.d(Const.TAG, "onErrorResponse: AuthFailureError error");
                else if(error instanceof TimeoutError)
                    Log.d(Const.TAG, "onErrorResponse: TimeoutError error");
                else if(error instanceof NetworkError)
                    Log.d(Const.TAG, "onErrorResponse: NetworkError error");
                else if(error instanceof ParseError)
                    Log.d(Const.TAG, "onErrorResponse: ParseError error");
                Log.d(Const.TAG, "onErrorResponse: " + error.getMessage());
                Log.d(Const.TAG, "onErrorResponse: " + error.getLocalizedMessage());
                Log.d(Const.TAG, "onErrorResponse: " + error.getMessage());
                listener.onRequestFinish(null, error.getMessage());
            }
        }

        );
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        VolleyUtils.getInstance(context).addToRequestQueue(request);

    }
}
