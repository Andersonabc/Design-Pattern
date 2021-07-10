package org.ntutssl.shop;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Iterator;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class EventTest {
    private GoodsEvent gEvent = new GoodsEvent(EventType.ADD_TO_CART, new Merchandise(0, "Merch", "test", 100), 10);
    private StringEvent sEvent = new StringEvent(EventType.ADD_TO_CART, "test StringEvent");
    @Test
    public void GoodsEvent_type_test(){
        assertEquals(EventType.ADD_TO_CART, gEvent.type());
    }
    @Test
    public void GoodsEvent_data_test(){
        assertEquals(0, gEvent.data().id());
        assertEquals("Merch", gEvent.data().name());
        assertEquals("test", gEvent.data().description());
        assertEquals(100, gEvent.data().price(), 0);
    }
    @Test
    public void GoodsEvent_count_test(){
        assertEquals(10, gEvent.count());
    }
    @Test
    public void StringEvent_type_test(){
        assertEquals(EventType.ADD_TO_CART, sEvent.type());
    }
    @Test
    public void StringEvent_data_test(){
        assertEquals("test StringEvent", sEvent.data());
    }
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void StringEvent_count_exception_test(){
        exceptionRule.expect(ShopException.class);
        exceptionRule.expectMessage("invalid operation");
        sEvent.count();
    }
}
