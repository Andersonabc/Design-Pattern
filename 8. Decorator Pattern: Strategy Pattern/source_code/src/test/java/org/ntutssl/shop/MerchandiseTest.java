package org.ntutssl.shop;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
public class MerchandiseTest {
    @Test
    public void get_id_test(){
        Merchandise merchandise = new Merchandise(0, "merchandise", "test", 99.9);
        assertEquals(0, merchandise.id());
    }
    
    @Test
    public void get_name_test(){
        Merchandise merchandise = new Merchandise(0, "merchandise", "test", 99.9);
        assertEquals("merchandise", merchandise.name());
    }

    @Test
    public void get_description_test(){
        Merchandise merchandise = new Merchandise(0, "merchandise", "test", 99.9);
        assertEquals("test", merchandise.description());
    }

    @Test
    public void get_price_test(){
        Merchandise merchandise = new Merchandise(0, "merchandise", "test", 99.9);
        assertEquals(99.9, merchandise.price(), 0);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test
    public void negativeId_exception_test(){
        exceptionRule.expect(ShopException.class);
        exceptionRule.expectMessage("Goods id should not be negative.");
        Merchandise merchandise = new Merchandise(-1, "merchandise", "m", 99.9);
    }

    @Test
    public void negativePrice_test(){
        exceptionRule.expect(ShopException.class);
        exceptionRule.expectMessage("Goods price should not be negative.");
        Merchandise merchandise = new Merchandise(0, "merchandise", "test", -1.0);
    }

    @Test
    public void add_exception_test(){
        exceptionRule.expect(ShopException.class);
        exceptionRule.expectMessage("Invalid action: add.");
        Merchandise merchandise = new Merchandise(0, "merchandise", "test", 99.9);
        merchandise.add(merchandise);
    }

    @Test
    public void nullIterator_test(){
        Merchandise merchandise = new Merchandise(0, "merchandise", "test", 99.9);
        assertFalse(merchandise.iterator().hasNext());
        assertEquals(merchandise.iterator().getClass(),NullIterator.class);
    }
}