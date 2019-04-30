package com.example.cobata.interfaces;

import org.json.JSONObject;

public interface BaseRequestListener {
    void onRequestFinish(JSONObject param, String message);

}
