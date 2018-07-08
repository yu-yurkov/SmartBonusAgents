package com.example.hp.smartbonusagents.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hp.smartbonusagents.R;

public class ProductActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // hide default action bar
        getSupportActionBar().hide();

        //
        String name = getIntent().getExtras().getString("product_name");
        String price = getIntent().getExtras().getString("product_price");
        String photo = getIntent().getExtras().getString("product_photo");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_name = findViewById(R.id.ap_name);
        TextView tv_price = findViewById(R.id.ap_price);
        TextView tv_description = findViewById(R.id.ap_description);
        ImageView img = findViewById(R.id.ap_photo);

        tv_name.setText(name);
        tv_price.setText(price);
        tv_description.setText(name);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        // load img using Glide
        Glide.with(this).load(photo).apply(requestOptions).into(img);

        collapsingToolbarLayout.setTitle(name);
    }
}
