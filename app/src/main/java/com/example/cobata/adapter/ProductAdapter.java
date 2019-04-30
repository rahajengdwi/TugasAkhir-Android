package com.example.cobata.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobata.R;
import com.example.cobata.model.ProductModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ExampleHolder> {

    ArrayList<ProductModel> list;

    public ProductAdapter(ArrayList<ProductModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ProductAdapter.ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_product, parent, false);
        return new ExampleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductAdapter.ExampleHolder holder, int position) {
        final ProductModel currentModel = list.get(position);
        holder.tv_product_name.setText(currentModel.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Customer: " +currentModel.getName(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ExampleHolder extends RecyclerView.ViewHolder {
        TextView tv_product_name;

        public ExampleHolder(@NonNull View itemView) {
            super(itemView);
            tv_product_name = itemView.findViewById(R.id.name);
        }
    }
}
