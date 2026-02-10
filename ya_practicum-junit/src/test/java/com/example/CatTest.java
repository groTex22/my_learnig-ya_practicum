package com.example;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {
    //Мок класса от которого зависит класс Cat
    @Mock
    Feline feline;

    //Будем проверять как говорит котик
    @Test
    public void getSoundReturnCatSound(){
        Cat cat = new Cat(feline);
        Assert.assertEquals("Мяу", cat.getSound());
    }


    //Так же проверим, что он кушает
    @Test
    public void getFoodEatMeat() throws Exception{
        Cat cat = new Cat(feline);
        List<String> expectedResult = Arrays.asList("Животные", "Птицы", "Рыба");

        Mockito.when(feline.eatMeat()).thenReturn(Arrays.asList("Животные", "Птицы", "Рыба"));
        Assert.assertEquals(expectedResult, cat.getFood());
    }

}
