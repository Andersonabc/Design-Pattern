package org.ntutssl.shop;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class GoodsParserTest implements EventListener{ 
    private int isREPLENISH = 0;
    private int isCHECK_STOCK = 0;

    public void onEvent(Event event){
        if(event.type().equals(EventType.REPLENISH)){
            this.isREPLENISH += 1;
        }
        if(event.type().equals(EventType.CHECK_STOCK)){
            this.isCHECK_STOCK += 1;
        }
    }
    public void resetCount(){
        this.isCHECK_STOCK = 0;
        this.isREPLENISH = 0;
    }
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test
    public void import_file_error_test(){
        exceptionRule.expect(ShopException.class);
        exceptionRule.expectMessage("import error");
        resetCount();
        EventManager.getInstance().subscribe(EventType.REPLENISH, this);
        GoodsParser gp = new GoodsParser();
        EventManager.getInstance().publish(new StringEvent(EventType.IMPORT_REPLENISH_LIST, "input/error.json"));
    }
    @Test
    public void import_replenish_list_publish_event_test(){
        resetCount();
        EventManager.getInstance().reset();
        EventManager.getInstance().subscribe(EventType.REPLENISH, this);
        GoodsParser gp = new GoodsParser();
        EventManager.getInstance().publish(new StringEvent(EventType.IMPORT_REPLENISH_LIST, "input/replenish_list.json"));
        assertEquals(13, this.isREPLENISH);
        
    }
    @Test
    public void import_shopping_list_publish_event_test(){
        resetCount();
        EventManager.getInstance().reset();
        EventManager.getInstance().subscribe(EventType.CHECK_STOCK, this);
        GoodsParser gp = new GoodsParser();
        EventManager.getInstance().publish(new StringEvent(EventType.IMPORT_SHOPPING_LIST, "input/shopping_list.json"));
        assertEquals(6, this.isCHECK_STOCK);
    }

}

