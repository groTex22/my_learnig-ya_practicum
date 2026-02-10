package com.example;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {
    @Mock
    Feline feline;

    Lion lion ;


    //Создаем объект класса Lion и инициируем мок перед тестом
    @Before
    public void beforeTest() throws Exception {
        lion = new Lion("Самец",feline);
    }


    //Проверяем создание объекта с несуществуещем полом животного
    //перехватываем ошибку не с помощью аннотации, а с помощью try catch
    //чтобы сравнить текст ошибки
    @Test
    public void createLionWrongParemetr() {
        String err = "Используйте допустимые значения пола животного - самей или самка";
        try {
        lion = new Lion("Пацан",feline);
        //А вдруг создасться, поэтому пишем ниже fail, чтобы исключить ложную отработку
        Assert.fail("Лев не должен был быть создан. Если создали, то ай яй яй");
        } catch (Exception exception) {
            //Сверим текст исключения с ожидаемым
            MatcherAssert.assertThat(exception.getMessage(), containsString(err));
        }
    }

    //В этом тесте проверим сколько возвращается 1 экземпляр льва
    @Test
    public void getKittensReturnOneCount() {
        Mockito.when(feline.getKittens()).thenReturn(1);
        Assert.assertEquals(1, lion.getKittens());
    }

    //Проверим что кушает лев
    @Test
    public void getFoodPredatorEat() throws Exception {
        List<String> expectedResult = List.of("Животные", "Птицы", "Рыба");

        Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        Assert.assertEquals(expectedResult, lion.getFood());
    }

}
