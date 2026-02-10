package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;//= new Bun("Булочка за сотку", 100f );
    @Mock
    Ingredient ingredient;// = new Ingredient(IngredientType.SAUCE, "Подлива", 50f);
    @Mock
    Ingredient ingredient2;

    Burger burger;

    @Before
    public void beforeTest() {
        burger = new Burger();
        //Пропишем, что ожидаем получить при вызове моков
        //Потребуется для getPriceBurgerTest и getReceiptBurgerTest
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(bun.getName()).thenReturn("Вкусная булочка");
        Mockito.when(ingredient.getPrice()).thenReturn(50f);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getName()).thenReturn("томатный");
    }

    @Test
    public void setBunBurgerTest() {
        burger.setBuns(bun);
        //Проверяем, что добавилась именно такая же булка
        //Сравниваются два объекта, а не их отдельные поля
        //хотя можно в случае более сложного кода, можно сравнить отдельно все поля
        Assert.assertEquals(bun, burger.bun);

    }
    //Будем проверять как добавляются ингредиенты
    @Test
    public void addOneIngredientBurgerTest() {
        burger.addIngredient(ingredient);
        Assert.assertEquals(List.of(ingredient),burger.ingredients); //.get(0).getName()
    }

    //Будем проверять как добавляются два ингредиента
    @Test
    public void addTwoIngredientBurgerTest() {
        //Добавим два ингредиента
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        //Убедимся, что оба ингредиента добавлены в список ингредиентов.
        Assert.assertEquals(List.of(ingredient,ingredient),burger.ingredients);
    }

    //Проверяем как удаляется ингредиенты из бургера
    @Test
    public void removeIngredientBurgerTest() {
        //Добавили ингредиент
        burger.addIngredient(ingredient);
        //так ранее ингредиентов не было, то единственный ингредиент
        //по-умолчанию занимает 0 индекс
        burger.removeIngredient(0);
        //Убедимся, что список пустой.
        Assert.assertTrue("Список не пустой",burger.ingredients.isEmpty());
    }

    //Проверяем как ингредиенты меняются местами
    @Test
    public void moveIngredientBurgerTest() {
        //Добавили ингредиент
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        //так ранее ингредиентов не было, то единственный ингредиент
        //по-умолчанию занимает 0 индекс
        burger.moveIngredient(0, 1);
        //второй ингредиент должен оказаться на первом месте
        Assert.assertEquals(0, burger.ingredients.indexOf(ingredient2));
        //Первый наоборот
        Assert.assertEquals(1, burger.ingredients.indexOf(ingredient));
    }

    //Проверка получения цены
    //стоимость равна сумме цен бургера*2 и ингредиентов
    @Test
    public void getPriceBurgerTest() {
        //Используем шпионов, поэтому стоимость должна быть 100*2 + 50 + 50
        //Добавим булку
        burger.setBuns(bun);
        //Добавим несколько ингредиентов
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);

        burger.getPrice();
        //стоимость равна сумме булки*2 и ингредиента
        Assert.assertEquals(300f, burger.getPrice(), 0.0);
    }

    //Проверка печати рецепта
    @Test
    public void getReceiptBurgerTest() {
        //Добавим булку
        burger.setBuns(bun);
        //Добавим несколько ингредиентов, для проверки работы цикла
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);

        String expected = "(==== Вкусная булочка ====)\r\n"
                + "= sauce томатный =\r\n"
                + "= sauce томатный =\r\n"
                + "(==== Вкусная булочка ====)\r\n"
                + "\r\n"
                + "Price: 300,000000\r\n";

        //рецепт совпадает с ожиданием
        Assert.assertEquals(expected, burger.getReceipt());
    }



}
