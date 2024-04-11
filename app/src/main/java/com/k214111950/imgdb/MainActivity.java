package com.k214111950.imgdb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.k214111950.Database;
import com.k214111950.adapter.ProductAdapter;
import com.k214111950.imgdb.databinding.ActivityMainBinding;
import com.k214111950.model.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public static Database db; //public để gọi
    ListView lvProduct;
    ArrayList<Product> products;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();

        createDb();


        products = new ArrayList<>();
        adapter = new ProductAdapter(this, R.layout.product_item, products);
        binding.lvProduct.setAdapter(adapter);

        getData();
    }

    private void createDb() {
        db = new Database(MainActivity.this);
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    private void getData() {
        Cursor cursor = db.GetData("SELECT * FROM product ");
        while (cursor.moveToNext()){
            products.add(new Product(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getBlob(3)
            ));
        }
        adapter.notifyDataSetChanged();
    }

    private void addEvents() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });


    }
}