package com.k214111950.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.k214111950.imgdb.R;
import com.k214111950.model.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private int item_layout;
    private List<Product> products;

    public ProductAdapter(Context context, int item_layout, List<Product> products) {
        this.context = context;
        this.item_layout = item_layout;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtPrice = view.findViewById(R.id.txtPrice);
            holder.imvThumb = view.findViewById(R.id.imvThumb);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        //binding
        Product p = products.get(position);
        holder.txtName.setText(p.getProductName());
        holder.txtPrice.setText(String.valueOf(Math.round(p.getProductPrice())) + " VND");
        // Chuyển byte về bitmap
        byte[] imvThumb = p.getProductImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imvThumb, 0, imvThumb.length);
        holder.imvThumb.setImageBitmap(bitmap);

        return view;
    }
    public static class ViewHolder{
        TextView txtName, txtPrice;
        ImageView imvThumb;
    }
}
