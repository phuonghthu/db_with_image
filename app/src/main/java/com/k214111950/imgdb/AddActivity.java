package com.k214111950.imgdb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.k214111950.Database;
import com.k214111950.imgdb.databinding.ActivityAddBinding;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity {

    ActivityAddBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher;

    boolean openCam;

    EditText edtName, edtPrice;
    ImageView imvThumb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.toolbarTitle.setText("Add Product");
        
        addEvents();
        findViewById();

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                , result -> {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){

                        if (openCam){
                            Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
                            binding.imvThumb.setImageBitmap(photo);
                        }else{
                            Uri selectedPhotoUri = result.getData().getData();
                            try {
                                InputStream inputStream = getContentResolver().openInputStream(selectedPhotoUri);
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                binding.imvThumb.setImageBitmap(bitmap);
                            } catch (FileNotFoundException e){
                                e.printStackTrace();
                            }

                        }


                    }
                });
    }

    private void findViewById() {
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        imvThumb = findViewById(R.id.imvThumb);
    }


    private void addEvents() {
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                finish();
            }
        });

        binding.btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCam = true;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher.launch(intent);
            }
        });

        binding.btnLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCam = false;
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);
            }
        });



        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển data imageview -> byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imvThumb.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray); // định dạng lại kiểu dữ liệu
                byte[] image = byteArray.toByteArray(); // dữ liệu chứa hình ảnh
                MainActivity.db.INSERT_Product(
                        edtName.getText().toString().trim(),
                        Double.valueOf(edtPrice.getText().toString().trim()),
                        image

                );
                Toast.makeText(AddActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}