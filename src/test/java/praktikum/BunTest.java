package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/*Ограничений как таковых здесь нет, а из методов только геттеры,
  Поэтому в целом параметризация здесь может и излишняя, но с заделом
  на развитие кода так сказать*/
@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;
    private final float delta = 0;

    Bun bun;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    //Глядя в код, ограничений на имя и цены кроме типа данных нет, поэтому
    //Много сценариев писать смысла нет
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Столичная", 100F}, //кириллица, положительная цена
                {"Moskovskaya", -20.1f}, //латиница, отрицательная цена
                {"    ", 0}, //пробелы, ноль
        };
    }

    @Before
    public void beforeTest() {
        bun = new Bun(name, price);
    }

    @Test
    public void getNameBunTest() {
        Assert.assertEquals("Имя не соответсвует создаваемому"
                , name
                , bun.getName());
    }

    @Test
    public void getPriceBunTest() {
        Assert.assertEquals("Цена не соответсвует создаваемой"
                , price
                , bun.getPrice()
                , delta);

    }
}
