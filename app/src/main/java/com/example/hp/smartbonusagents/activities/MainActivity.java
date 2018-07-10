package com.example.hp.smartbonusagents.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp.smartbonusagents.R;
import com.example.hp.smartbonusagents.adapters.RecyclerViewAdapter;
import com.example.hp.smartbonusagents.model.DB;
import com.example.hp.smartbonusagents.model.Products;
import com.example.hp.smartbonusagents.model.UserCart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {


    private final String JSON_URL = "https://agents.sbonus.ru/json/";
    //private final String JSON_URL = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Products> listProducts;
    private RecyclerView recyclerView;

    // корзина пользователя
    private ArrayList<UserCart> userCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listProducts = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view_id);
        jsonrequest();


        // наполняем корзину
        /*
        userCart = new DB().getList();

        for (int i = 0; i < userCart.size(); i++) {
            Log.i("TAG", userCart.get(i).toString());
        }
        */

        TreeMap<Integer, TreeMap<String, String>> treeMap = new DB().getTreeMap();

        /*
        for(Map.Entry<Integer, TreeMap <String, String>> entry : treeMap.entrySet()) {
            Integer key = entry.getKey();
            TreeMap <String, String> value = entry.getValue();

            Log.i("TAG", key + ":");

            for(Map.Entry<String,String> product : value.entrySet()) {
                String keyProduct = product.getKey();
                String valueProduct = product.getValue();

                Log.i("TAG", keyProduct + " " + valueProduct);
            }
        }
        */

        Log.i("TAG", "" + treeMap.size());

    }

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {

                    try{
                        jsonObject = response.getJSONObject(i);
                        Products products = new Products();

                        products.setName(jsonObject.getString("name"));
                        products.setPrice(jsonObject.getString("price"));
                        products.setPhoto(jsonObject.getString("photo"));


                        listProducts.add(products);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                setuprecycleview(listProducts);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

    }

    private void setuprecycleview(List<Products> listProducts) {

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,listProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myAdapter);

    }
}
