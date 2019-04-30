package com.example.cobata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.cobata.adapter.ProductAdapter;
import com.example.cobata.interfaces.BaseRequestListener;
import com.example.cobata.interfaces.Const;
import com.example.cobata.model.ProductModel;
import com.example.cobata.utils.LayoutUtils;
import com.example.cobata.utils.RequestUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity implements BaseRequestListener {

    RecyclerView recyclerView;
    ProductAdapter adapter;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        recyclerView = findViewById(R.id.recycler_view);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Memuat");
        dialog.setMessage("Harap menunggu...");
        dialog.setCancelable(false);
        callReadProductAPI();

    }

    private void callReadProductAPI() {
        dialog.show();
        RequestUtils.getAPI(Const.BASE_URL+Const.PRODUCT, Request.Method.GET, new JSONObject(), this, this);
    }

    @Override
    public void onRequestFinish(JSONObject param, String message) {

        Log.d(Const.TAG, "onRequestFinish: ");
        dialog.dismiss();
        if(param == null){
            Toast.makeText(this, "error " +message, Toast.LENGTH_SHORT).show();
            Log.d(Const.TAG, "onRequestFinish: " + message);
            return;
        }
        ArrayList<ProductModel> list = new ArrayList<>();
        try {
            JSONArray result = param.getJSONArray("result");
            for (int i=0; i<result.length(); i++){
                JSONObject jsonProduct = result.getJSONObject(i);
                ProductModel temp = new ProductModel();
                temp.setName(jsonProduct.getString("display_name"));
                list.add(temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new ProductAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(LayoutUtils.createGridLayoutManager(this, RecyclerView.VERTICAL, 2));
        adapter.notifyDataSetChanged();
    }
}
