package com.example.hp.smartbonusagents.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hp.smartbonusagents.R;
import com.example.hp.smartbonusagents.activities.MainActivity;
import com.example.hp.smartbonusagents.activities.ProductActivity;
import com.example.hp.smartbonusagents.dao.UserCartDao;
import com.example.hp.smartbonusagents.database.App;
import com.example.hp.smartbonusagents.database.AppDatabase;
import com.example.hp.smartbonusagents.entities.UserCartEntity;
import com.example.hp.smartbonusagents.model.Products;
import com.example.hp.smartbonusagents.model.UserCart;

import java.util.List;
import java.util.TreeMap;

import static com.example.hp.smartbonusagents.R.drawable.button_bg;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private static final String TAG = "TAG";
    private Context mContext;
    private List<Products> mData;
    private TreeMap<Integer, Integer> userCartMap;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<Products> mData, TreeMap<Integer, Integer> userCartMap) {
        this.mContext = mContext;
        this.mData = mData;
        this.userCartMap = userCartMap;

        // Options for Glide
        this.option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.products_row_item, parent,false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);

        // click on add cart
        myViewHolder.addCartButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {

                AppDatabase db = App.getInstance().getDatabase();
                UserCartDao userCartDao = db.userCartDao();

                // определяем id товара
                int p_id = mData.get(myViewHolder.getAdapterPosition()).getId();

                // формируем данные
                UserCartEntity userCartEntity = new UserCartEntity();
                userCartEntity.id = p_id;
                userCartEntity.quantity = 1;
                userCartEntity.products = mData.get(myViewHolder.getAdapterPosition());

                // проверяем наличие товара в корзине
                UserCartEntity userCartId = userCartDao.getById(p_id);

                //Button button = v.findViewById(v.getId());

                if(userCartId == null){
                    Log.i(TAG, "onClick: добавляем товар в корзину: id = " + p_id);
                    v.setBackgroundResource(R.color.colorAccent);



                    // закидываем в корзину
                    userCartDao.insert(userCartEntity);

                }else{
                    Log.i(TAG, "onClick: товар уже в корзине. Убираем");

                    v.setBackgroundResource(R.color.colorPrimaryDark);

                    // удаляем из корзины
                    userCartDao.delete(userCartEntity);
                }


                List<UserCartEntity> userCartsEntities = userCartDao.getAll();

                for (UserCartEntity element : userCartsEntities) {
                    Log.i(TAG, "id: " + element.id
                            +", quantity: "+ element.quantity
                            +", p_id: "+ element.products.getId()
                            + ", p_name: " + element.products.getName()
                            + ", p_price: " + element.products.getPrice()
                            + ", p_img: " + element.products.getPhoto());
                }

                // вызов метода обработки клика
                /*
                *   1. меняем фон кнопки (toggle)
                *   2. пишем id и quantity в базу (содержимое корзины)
                *   3. если повторный клик, убераем товар из базы, кнопку в первоначальный вид
                *
                * */
            }
        });




        // enter card
        myViewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ProductActivity.class);
                i.putExtra("product_name", mData.get(myViewHolder.getAdapterPosition()).getName());
                i.putExtra("product_price", mData.get(myViewHolder.getAdapterPosition()).getPrice());
                i.putExtra("product_photo", mData.get(myViewHolder.getAdapterPosition()).getPhoto());
                Log.i(TAG, "onClick: " + v.toString());

                //v.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.colorAccent));

                mContext.startActivity(i);
            }
        });

        return myViewHolder;
    }






    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        // создаем мап с id товаров в корзине
        // сравниваем id товара с мапом
        //  если в мапе есть id меняем стиль кнопки или отмечаем ее как ВКЛ

        if(userCartMap.containsKey(mData.get(position).getId())){
            holder.addCartButton.setBackgroundResource(R.color.colorAccent);
        }


        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_price.setText(mData.get(position).getPrice());

        // загрузка изображения
        Glide.with(mContext).load(mData.get(position).getPhoto()).apply(option).into(holder.img_photo);
    }

    @Override
    public int getItemCount() {
        return this.mData.size();
    }


    // MyViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_price;
        ImageView img_photo;
        LinearLayout view_container;
        
        
        // кнопка
        Button addCartButton;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.name);
            tv_price = itemView.findViewById(R.id.price);
            img_photo = itemView.findViewById(R.id.photo);
            view_container = itemView.findViewById(R.id.container);
            
            // кнопка
            addCartButton = itemView.findViewById(R.id.add_cart_button);
        }



    }
}
