package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

//Простые проверки геттеров
//в параметризации нет необходимости, хотя желание было
public class IngredientTest {

    IngredientType ingredientType = IngredientType.SAUCE;
    private final String name = "Супер_sauce-1";
    private final float price = 100.1F;
    Ingredient ingredient;


    @Before
    public void beforeTest(){
        //Создадим экземпляр ингредиента
        ingredient = new Ingredient(ingredientType, name, price);
    }

    @Test
    public void getNameIngredientTest() {
        Assert.assertEquals("Имя соответсвует создаваемому"
                , name
                , ingredient.getName());
    }

    @Test
    public void getPriceIngredientTest() {
        Assert.assertEquals("Цена соответсвует создаваемой, с дельтой 0"
                , price
                , ingredient.getPrice()
                , 0);
    }

    @Test
    public void getTypeIngredientTest() {
        Assert.assertEquals("Тип соответсвует создаваемому"
                , ingredientType
                , ingredient.getType());
    }


}
