package com.example.cobata.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.cobata.R;
import com.example.cobata.Retrofit.Client;
import com.example.cobata.Retrofit.Server;
import com.example.cobata.Return.ReturnCreate;
import com.example.cobata.interfaces.BaseRequestListener;
import com.example.cobata.interfaces.Const;
import com.example.cobata.interfaces.ReturnListener;
import com.example.cobata.model.CountryList;
import com.example.cobata.utils.DialogUtils;
import com.example.cobata.utils.RequestUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AddCustomerActivity extends AppCompatActivity implements BaseRequestListener {

    EditText et_customer_name;
    EditText et_customer_street;
    EditText et_customer_city;
    EditText et_customer_country_id;
    EditText et_customer_phone;
    EditText et_customer_email;
    EditText et_customer_website;
    CheckBox cb_customer_active;
    CheckBox cb_customer_customer;
    CheckBox cb_customer_supplier;
    CheckBox cb_customer_employee;
    Button button_customer,btn;
    ImageView imageView;
    String base64;
    ArrayList<CountryList> ListCountry;
    ProgressDialog dialog;
    Bitmap bitmap;

    String list_id;
    private static final int OPEN_DOCUMENT_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        getSupportActionBar().setTitle("Add New Customer");
        
        dialog = new ProgressDialog(this);
        dialog.setTitle("Memuat");
        dialog.setMessage("Harap menunggu...");
        dialog.setCancelable(false);
        dialog.show();
        imageView = findViewById(R.id.cekgambar);
        initList();
        btn = findViewById(R.id.btngambar);
        et_customer_name = findViewById(R.id.name);
        et_customer_street = findViewById(R.id.street);
        et_customer_city = findViewById(R.id.city);
        et_customer_country_id = findViewById(R.id.country);
        et_customer_phone = findViewById(R.id.phone);
        et_customer_email = findViewById(R.id.email);
        et_customer_website = findViewById(R.id.website);
        cb_customer_active = findViewById(R.id.active);
        cb_customer_customer = findViewById(R.id.customer);
        cb_customer_supplier = findViewById(R.id.supplier);
        cb_customer_employee = findViewById(R.id.employee);
        button_customer = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFromGallery();
            }
        });
        button_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCustomer();
            }
        });

        et_customer_country_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListCountry();
            }
        });
        requestListCountry();
    }
    private void requestListCountry(){
        dialog.show();
        RequestUtils.getAPI(Const.BASE_URL + Const.COUNTRY, Request.Method.GET, new JSONObject(), new BaseRequestListener() {
            @Override
            public void onRequestFinish(JSONObject param, String message) {
                dialog.dismiss();
                initListCountry(param);
            }
        }, this);
    }

    private void initListCountry(JSONObject param) {
        if(param == null){
            Toast.makeText(this, "GAgal woi", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            JSONArray result = param.getJSONArray("result");
            for (int i=0; i < result.length(); i++){
                CountryList temp = new CountryList(); //nama class dii model
                JSONObject tempJSON = result.getJSONObject(i);
                temp.setId(tempJSON.getInt("id"));
                temp.setName(tempJSON.getString("name"));
                ListCountry.add(temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showListCountry() {

        final String[] list = getArrayList(ListCountry);
        DialogUtils.showSingleChoiceDialog(this, "Country", list, new ReturnListener() {
            @Override
            public void onDataReturn(int position) {
                et_customer_country_id.setText(list[position]);
                list_id = String.valueOf(ListCountry.get(position).getId());
            }
        });

    }

    private String[] getArrayList(ArrayList<CountryList> listCountry) {
        ArrayList<String> temp = new ArrayList<>();
        for (int i=0; i<ListCountry.size(); i++)
            temp.add(ListCountry.get(i).getName());
        return temp.toArray(new String[temp.size()]);
    }
//
    private void initList() {
        ListCountry = new ArrayList<>();
    }

//    private void createCustomer() {
//        Log.d(Const.TAG, "createCustomer: name = " +  et_customer_name.getText().toString());
//        Log.d(Const.TAG, "createCustomer: street = " + et_customer_street.getText().toString());
//        Log.d(Const.TAG, "createCustomer: city = " + et_customer_city.getText().toString());
//        Log.d(Const.TAG, "createCustomer: country = " + et_customer_country_id.getText().toString());
//        Log.d(Const.TAG, "createCustomer: country_id = " + list_id);
//        Log.d(Const.TAG, "createCustomer: phone = " + et_customer_phone.getText().toString());
//        Log.d(Const.TAG, "createCustomer: email = " + et_customer_email.getText().toString());
//        Log.d(Const.TAG, "createCustomer: website = " + et_customer_website.getText().toString());
//
//        JSONObject requestObject = new JSONObject();
//        try {
//            requestObject.accumulate("name", et_customer_name.getText().toString());
//            requestObject.accumulate("street", et_customer_street.getText().toString());
//            requestObject.accumulate("city", et_customer_city.getText().toString());
//            requestObject.accumulate("country", et_customer_country_id.getText().toString());
//            requestObject.accumulate("country_id", list_id);
//            requestObject.accumulate("phone", et_customer_phone.getText().toString());
//            requestObject.accumulate("email", et_customer_email.getText().toString());
//            requestObject.accumulate("website", et_customer_website.getText().toString());
//            requestObject.accumulate()
//            if(cb_customer_active.isChecked())
////                requestObject.accumulate("active", "1");
////            else
////                requestObject.accumulate("active", "0");
//            if(cb_customer_customer.isChecked())
//                requestObject.accumulate("customer", "1");
//            else
//                requestObject.accumulate("customer", "0");
//            if(cb_customer_supplier.isChecked())
//                requestObject.accumulate("supplier", "1");
//            else
//                requestObject.accumulate("supplier", "0");
//            if(cb_customer_employee.isChecked())
//                requestObject.accumulate("employee", "1");
//            else
//                requestObject.accumulate("employee", "0");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        dialog.show();
//        RequestUtils.getAPI(Const.BASE_URL+Const.CUSTOMER, Request.Method.POST, requestObject, this, this);
//    }

    @Override
    public void onRequestFinish(JSONObject param, String message) {
        dialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void createCustomer(){
        String active;
        if(cb_customer_active.isChecked())
                active ="1";
            else
                active ="0";
        String customer;
        if(cb_customer_customer.isChecked())
            customer = "1";
            else
            customer = "0";
        String supplier;
        if(cb_customer_supplier.isChecked())
            supplier ="1";
        else
            supplier = "0";
        String employee;
        if(cb_customer_employee.isChecked())
            employee = "1";
        else
            employee = "0";
//        String gambar = "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";
        Client api = Server.builder().create(Client.class);
        Call<ReturnCreate> cari = api.create(et_customer_name.getText().toString(),
                et_customer_street.getText().toString(),
                et_customer_city.getText().toString(),
               "1",
                et_customer_phone.getText().toString(),
                et_customer_email.getText().toString(),
                et_customer_website.getText().toString(),active,customer,supplier,employee,base64
                );
        cari.enqueue(new Callback<ReturnCreate>() {
            @Override
            public void onResponse(Call<ReturnCreate> call, Response<ReturnCreate> response) {
                Toast.makeText(AddCustomerActivity.this, "berhasil", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ReturnCreate> call, Throwable t) {
                Toast.makeText(AddCustomerActivity.this, "gagal", Toast.LENGTH_SHORT).show();

            }
        });
        dialog.dismiss();
    }

    private void pickFromGallery(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_DOCUMENT_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == OPEN_DOCUMENT_CODE && resultCode == RESULT_OK) {
            if (resultData != null) {
                // this is the image selected by the user
                Uri imageUri = resultData.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                    imageView.setImageBitmap(bitmap);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream .toByteArray();
                    base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    Log.d("cek123", "onActivityResult: "+base64);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
