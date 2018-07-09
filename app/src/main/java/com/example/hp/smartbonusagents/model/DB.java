package com.example.hp.smartbonusagents.model;

import java.util.ArrayList;

public class DB {

    ArrayList<UserCart> list;

    public DB(){
        list.add(new UserCart(1, "Мёд «Дальневосточный» липовый", 115F, "https://sbonus.ru/images/6-jpg-small-4395.jpg", 10));
        list.add(new UserCart(2, "Мёд «Дальневосточный» липовый с ядрами кешью", 138F, "https://sbonus.ru/images/4-jpg-small-4393.jpg", 8));
        list.add(new UserCart(3, "Мёд «Дальневосточный» липовый с ядрами миндаля", 138F, "https://sbonus.ru/images/5-jpg-small-4394.jpg", 6));
    }

    public ArrayList<UserCart> getList() {
        return list;
    }
}