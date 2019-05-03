package com.example.cobata.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.example.cobata.R;
import com.example.cobata.interfaces.BaseRequestListener;
import com.example.cobata.interfaces.Const;
import com.example.cobata.utils.RequestUtils;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements BaseRequestListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callReadProductAPI();
    }

    private void callReadProductAPI() {
        RequestUtils.getAPI(Const.BASE_URL+Const.PRODUCT, Request.Method.GET, new JSONObject(), this, this);
    }

    @Override
    public void onRequestFinish(JSONObject param, String message) {

    }
}
