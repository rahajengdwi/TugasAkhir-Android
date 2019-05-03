package com.example.cobata.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cobata.R;
import com.example.cobata.Return.ReturnRead;
import com.example.cobata.model.Customer;
import com.example.cobata.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ExampleHolder> {

    List<ReturnRead> list;
    Context context;
    public ProductAdapter(List<ReturnRead> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_product, parent, false);
        return new ExampleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductAdapter.ExampleHolder holder, int position) {
        final ReturnRead currentModel = list.get(position);
        holder.tv_product_name.setText(currentModel.getNama());
        byte[] decodedString = Base64.decode(currentModel.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Glide.with(context).load(decodedByte).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Customer: " +currentModel.getNama(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ExampleHolder extends RecyclerView.ViewHolder {
        TextView tv_product_name;
        ImageView imageView;
        public ExampleHolder(@NonNull View itemView) {
            super(itemView);
            tv_product_name = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.gambar);
        }
    }
}
