package com.example.hp.smartbonusagents.model;

import com.example.hp.smartbonusagents.R;

import java.util.ArrayList;
import java.util.TreeMap;

public class DB {

    // структура базы данных
    /*
        id (int)
        name (varchar 255)
        img (varchar 255)
        price (decimal)
        quantity (int)
    */

    /*
        ArrayList<UserCart> list = new ArrayList<UserCart>();

        public DB(){
            list.add(new UserCart(1,10, new Products("Фото", "Заголовок","115.00")));
            list.add(new UserCart(2,5, new Products("Фото2", "Заголовок2","120.00")));
          }

        public ArrayList<UserCart> getList() {
            return list;
        }
    */

    // наполняем корзину
//
//        userCart = new DB().getList();
//
//        for (int i = 0; i < userCart.size(); i++) {
//            Log.i("TAG", userCart.get(i).toString());
//        }
//
//
//        TreeMap<Integer, TreeMap<String, String>> treeMap = new DB().getTreeMap();
//
//
//        for(Map.Entry<Integer, TreeMap <String, String>> entry : treeMap.entrySet()) {
//            Integer key = entry.getKey();
//            TreeMap <String, String> value = entry.getValue();
//
//            Log.i("TAG", key + ":");
//
//            for(Map.Entry<String,String> product : value.entrySet()) {
//                String keyProduct = product.getKey();
//                String valueProduct = product.getValue();
//
//                Log.i("TAG", keyProduct + " " + valueProduct);
//            }
//        }
//
//
//        Log.i("TAG", "" + treeMap.size());

    TreeMap<Integer, TreeMap<String, String>> treeMap = new TreeMap<Integer, TreeMap <String, String>>();

    public DB(){

        if(!treeMap.containsKey(1)){
            treeMap.put(1, new TreeMap<String, String>());
            TreeMap <String, String> temp = treeMap.get(1);

            if(!temp.containsKey("name")) temp.put("name", "Название");
            if(!temp.containsKey("img")) temp.put("img", "image");
            if(!temp.containsKey("price")) temp.put("price", "100.99");
            if(!temp.containsKey("quantity")) temp.put("quantity", "10");
        }

        if(!treeMap.containsKey(2)){
            treeMap.put(2, new TreeMap<String, String>());
            TreeMap <String, String> temp = treeMap.get(2);

            if(!temp.containsKey("name")) temp.put("name", "Название2");
            if(!temp.containsKey("img")) temp.put("img", "image2");
            if(!temp.containsKey("price")) temp.put("price", "200.99");
            if(!temp.containsKey("quantity")) temp.put("quantity", "20");
        }

    }

    public TreeMap<Integer, TreeMap<String, String>> getTreeMap() {
        return treeMap;
    }


}