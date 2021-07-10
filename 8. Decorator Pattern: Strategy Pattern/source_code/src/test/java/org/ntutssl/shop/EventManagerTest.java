package org.ntutssl.shop;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;


import org.junit.Test;

public class EventManagerTest implements EventListener{ 
    private boolean isIMPORT_SHOPPING_LIST = false;
    private boolean isIMPORT_REPLENISH_LIST = false;
    private boolean isREPLENISH = false;
    private boolean isCHECK_STOCK = false;
    private boolean isADD_TO_CART = false;
    private boolean isPAY = false;
    private boolean isCALCULATE = false;
    private boolean isPRINT_RECEIPT = false;
    private boolean isPURCHASE = false;
    private boolean isLIST_CART = false;
    private boolean isLIST_SHOP = false;

    public void onEvent(Event event){
        this.isIMPORT_SHOPPING_LIST = event.type() == EventType.IMPORT_SHOPPING_LIST;
        this.isIMPORT_REPLENISH_LIST = event.type() == EventType.IMPORT_REPLENISH_LIST;
        this.isREPLENISH = event.type() == EventType.REPLENISH;
        this.isCHECK_STOCK = event.type() == EventType.CHECK_STOCK;
        this.isADD_TO_CART = event.type() == EventType.ADD_TO_CART;
        this.isPAY = event.type() == EventType.PAY;
        this.isCALCULATE = event.type() == EventType.CALCULATE;
        this.isPRINT_RECEIPT = event.type() == EventType.PRINT_RECEIPT;
        this.isPURCHASE = event.type() == EventType.PURCHASE;
        this.isLIST_CART = event.type() == EventType.LIST_CART;
        this.isLIST_SHOP = event.type() == EventType.LIST_SHOP;
    }
    @Test
    public void Unique_test(){
        EventManager em = EventManager.getInstance();
        EventManager em2 = EventManager.getInstance();
        assertEquals(em.hashCode(),em2.hashCode());
    }
    @Test
    public void reset_test(){
        EventManager em = EventManager.getInstance();
        EventManager.getInstance().reset();
        EventManager em2 = EventManager.getInstance();
        assertFalse(em.hashCode()==em2.hashCode());
    }
    @Test
    public void IMPORT_SHOPPING_LIST_test(){
        EventManager em = EventManager.getInstance();
        em.subscribe(EventType.IMPORT_SHOPPING_LIST, this);
        em.publish(new StringEvent(EventType.IMPORT_SHOPPING_LIST, "test"));
        assertTrue(this.isIMPORT_SHOPPING_LIST);
        em.reset();
    }
    @Test
    public void IMPORT_REPLENISH_LIST_test(){
        EventManager em = EventManager.getInstance();
        em.subscribe(EventType.IMPORT_REPLENISH_LIST, this);
        em.publish(new StringEvent(EventType.IMPORT_REPLENISH_LIST, "test"));
        assertTrue(this.isIMPORT_REPLENISH_LIST);
        em.reset();
    }
    @Test
    public void REPLENISH_test(){
        EventManager em = EventManager.getInstance();
        em.subscribe(EventType.REPLENISH, this);
        em.publish(new StringEvent(EventType.REPLENISH, "test"));
        assertTrue(this.isREPLENISH);
        em.reset();
    }
    @Test
    public void CHECK_STOCK_test(){
        EventManager em = EventManager.getInstance();
        em.subscribe(EventType.CHECK_STOCK, this);
        em.publish(new StringEvent(EventType.CHECK_STOCK, "test"));
        assertTrue(this.isCHECK_STOCK);
        em.reset();
    }
    @Test
    public void ADD_TO_CART_test(){
        EventManager em = EventManager.getInstance();
        em.subscribe(EventType.ADD_TO_CART, this);
        em.publish(new StringEvent(EventType.ADD_TO_CART, "test"));
        assertTrue(this.isADD_TO_CART);
        em.reset();
    }
    @Test
    public void PAY_test(){
        EventManager em = EventManager.getInstance();
        em.subscribe(EventType.PAY, this);
        em.publish(new StringEvent(EventType.PAY, "test"));
        assertTrue(this.isPAY);
        em.reset();
    }
    @Test
    public void CALCULATE_test(){
        EventManager em = EventManager.getInstance();
        em.subscribe(EventType.CALCULATE, this);
        em.publish(new StringEvent(EventType.CALCULATE, "test"));
        assertTrue(this.isCALCULATE);
        em.reset();
    }
    @Test
    public void PRINT_RECEIPT_test(){
        EventManager em = EventManager.getInstance();
        em.subscribe(EventType.PRINT_RECEIPT, this);
        em.publish(new StringEvent(EventType.PRINT_RECEIPT, "test"));
        assertTrue(this.isPRINT_RECEIPT);
        em.reset();
    }
    @Test
    public void PURCHASE_test(){
        EventManager em = EventManager.getInstance();
        em.subscribe(EventType.PURCHASE, this);
        em.publish(new StringEvent(EventType.PURCHASE, "test"));
        assertTrue(this.isPURCHASE);
        em.reset();
    }
    @Test
    public void LIST_CART_test(){
        EventManager em = EventManager.getInstance();
        em.subscribe(EventType.LIST_CART, this);
        em.publish(new StringEvent(EventType.LIST_CART, "test"));
        assertTrue(this.isLIST_CART);
        em.reset();
    }
    @Test
    public void LIST_SHOP_test(){
        EventManager em = EventManager.getInstance();
        em.subscribe(EventType.LIST_SHOP, this);
        em.publish(new StringEvent(EventType.LIST_SHOP, "test"));
        assertTrue(this.isLIST_SHOP);
        em.reset();
    }
}