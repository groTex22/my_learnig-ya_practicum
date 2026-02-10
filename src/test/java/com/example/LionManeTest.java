package com.example;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class LionManeTest {
    String male;
    boolean hasMane;

    public LionManeTest(String male, boolean hasMane){
        this.male=male;
        this.hasMane=hasMane;
    }

    @Mock
    Feline feline;

    @Before
    public void beforeTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Parameterized.Parameters
    public static Object[] objects (){
        return new Object[][] {
                { "Самка", false},
                { "Самец", true},
                { "Пацан", false},// передали тестовые данные
        };
    }

    @Test
    public void createLionWrongParemetr() {
        String err = "Используйте допустимые значения пола животного - самей или самка";
        try {
           Lion lion = new Lion(male, feline);
            //А вдруг создасться, поэтому пишем ниже fail, чтобы исключить ложную отработку
            if (male.equals("Пацан")) {
                Assert.fail("Лев не должен был быть создан. Если создали, то ай яй яй");
            }
            Assert.assertEquals( hasMane, lion.doesHaveMane());
        } catch (Exception exception) {
            //Сверим текст исключения с ожидаемым
            MatcherAssert.assertThat(exception.getMessage(), containsString(err));
        }
    }

}
