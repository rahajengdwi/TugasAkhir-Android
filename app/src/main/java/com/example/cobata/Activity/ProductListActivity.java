package com.example.cobata.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.example.cobata.R;
import com.example.cobata.Retrofit.Client;
import com.example.cobata.Retrofit.Server;
import com.example.cobata.Return.ResultHome;
import com.example.cobata.Return.ReturnRead;
import com.example.cobata.adapter.ProductAdapter;
import com.example.cobata.interfaces.BaseRequestListener;
import com.example.cobata.interfaces.Const;
import com.example.cobata.utils.LayoutUtils;
import com.example.cobata.utils.RequestUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity implements BaseRequestListener {

    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<ReturnRead> nama = new ArrayList<>();
    ProgressDialog dialog;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        recyclerView = findViewById(R.id.recycler_view);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Memuat");
        dialog.setMessage("Harap menunggu...");
        dialog.setCancelable(false);
        dialog.show();
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductListActivity.this, AddCustomerActivity.class));
            }
        });
        getuser();
//        callReadProductAPI();

    }

    @Override
    protected void onResume() {
        super.onResume();
//        callReadProductAPI();
        getuser();
    }

    private void callReadProductAPI() {
        dialog.show();
        RequestUtils.getAPI(Const.BASE_URL+Const.PRODUCT, Request.Method.GET, new JSONObject(), this, this);
    }

    @Override
    public void onRequestFinish(JSONObject param, String message) {

//        Log.d(Const.TAG, "onRequestFinish: ");
        dialog.dismiss();
//        if(param == null){
//            Toast.makeText(this, "error " +message, Toast.LENGTH_SHORT).show();
//            Log.d(Const.TAG, "onRequestFinish: " + message);
//            return;
//        }
//        ArrayList<ProductModel> list = new ArrayList<>();
//        try {
//            JSONArray result = param.getJSONArray("result");
//            for (int i=0; i<result.length(); i++){
//                JSONObject jsonProduct = result.getJSONObject(i);
//                ProductModel temp = new ProductModel();
//                temp.setName(jsonProduct.getString("display_name"));
//                list.add(temp);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    public void getuser()
    {
        Client api = Server.builder().create(Client.class);
        Call<ResultHome> cari = api.read();
        cari.enqueue(new Callback<ResultHome>() {
            @Override
            public void onResponse(Call<ResultHome> call, Response<ResultHome> response) {
                nama = response.body().getResult();
//                Log.d("jumlah", "onResponse: "+hasil.size());
//                for(int i=0;i<list.size();i++)
//                {
//                    Customer customer = new Customer(hasil.get(i).getId(),hasil.get(i).getNama(),hasil.get(i).getImage());
//                    list.add(customer);
//                }
                Log.d("cek", "onRequestFinish: "+nama.size());
                adapter = new ProductAdapter(nama,getApplicationContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(LayoutUtils.createGridLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, 2));
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResultHome> call, Throwable t) {

            }

        });
        dialog.dismiss();
    }
}
