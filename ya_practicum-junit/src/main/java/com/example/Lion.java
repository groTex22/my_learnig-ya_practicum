package com.example;

import java.util.List;

public class Lion {

    boolean hasMane;
    Feline feline;

    //Конструктор с инъекцией зависимостей
    public Lion(String sex, Feline feline) throws Exception {
        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного - самей или самка");
        }
        //Теперь получаем уже готовый объект, а не создаем сами
        //Таким образом сделали инъекцию зависимости
        this.feline = feline;
    }


    public int getKittens() {
        return feline.getKittens();
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public List<String> getFood() throws Exception {
        return feline.getFood("Хищник");
    }
}
