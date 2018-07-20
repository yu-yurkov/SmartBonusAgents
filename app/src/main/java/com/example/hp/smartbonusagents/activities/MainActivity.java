package com.example.hp.smartbonusagents.activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp.smartbonusagents.R;
import com.example.hp.smartbonusagents.adapters.RecyclerViewAdapter;
import com.example.hp.smartbonusagents.dao.UserCartDao;
import com.example.hp.smartbonusagents.database.App;
import com.example.hp.smartbonusagents.database.AppDatabase;
import com.example.hp.smartbonusagents.entities.UserCartEntity;
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

    private static final String TAG = "userCarts";

    private final String JSON_URL = "https://agents.sbonus.ru/json/";
    //private final String JSON_URL = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Products> listProducts;
    private RecyclerView recyclerView;
    private TreeMap<Integer, Integer> userCartMap;

    // корзина пользователя
    private ArrayList<UserCart> userCart;

    TextView textCartItemCount;
    int mCartItemCount = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // массив с корзиной
        List<UserCartEntity> userCart;
        userCartMap = new TreeMap<Integer, Integer>();

        AppDatabase db = App.getInstance().getDatabase();
        UserCartDao userCartDao = db.userCartDao();

        userCart = userCartDao.getAll();

        // упаковываем в TreeMap
        for (UserCartEntity element : userCart) {
            userCartMap.put(element.id, element.quantity);
        }

        // устанавливаем количество товаров в карзине
        mCartItemCount = userCartMap.size();

        // проверяем MAP

//        for(Map.Entry<Integer, Integer> entry : userCartMap.entrySet()) {
//            Integer key = entry.getKey();
//            Integer value = entry.getValue();
//
//            Log.i("userCartMap", "id: " + key + "Quantity: " + value);
//        }


        listProducts = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view_id);
        jsonrequest();

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

                        products.setId(jsonObject.getInt("id"));
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

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,listProducts, userCartMap);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myAdapter);

    }
}


