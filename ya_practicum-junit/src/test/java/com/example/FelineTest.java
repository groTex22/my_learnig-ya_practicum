package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {
    //Здесь используем шпиона, чтобы точно определить
    //в каком методе getKittens с параметром или без упали.
    @Spy
    Feline feline; //создали объект-шпион

    @Test
    public void getKittensNoParametrsResultOne(){

        Mockito.when(feline.getKittens(1)).thenReturn(1);
        Assert.assertEquals(1, feline.getKittens());
    }

    @Test
    public void getKittensCountTest() {
        Assert.assertEquals(5, feline.getKittens(5));
    }

    @Test
    public void getFamilyResultCat(){
        Assert.assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void eatMeatResiltEatMeat() throws Exception{
        List<String> expectedResult = List.of("Животные", "Птицы", "Рыба");
        Assert.assertEquals(expectedResult, feline.eatMeat());
    }


}
