package org.ntutssl.shop;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class ShoppingCartTest implements EventListener { 
    private int isCALCULATE = 0;
    private int isPURCHASE = 0;

    public void onEvent(Event event){
        if(event.type().equals(EventType.CALCULATE)){
            this.isCALCULATE += 1;
        }
        if(event.type().equals(EventType.PURCHASE)){
            this.isPURCHASE += 1;
        }
    }

    public void reset(){
        this.isCALCULATE = 0;
        this.isPURCHASE = 0;
    }
    @Test
    public void cart_is_empty_test(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new ShoppingCart();
        EventManager.getInstance().publish(new StringEvent(EventType.LIST_CART,""));
        assertEquals("Your shopping cart is empty\n",outputStream.toString());
        System.setOut(originalOutput);
        outputStream.reset();
        EventManager.getInstance().reset();   
    }
    @Test
    public void add_and_list_merch_test(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new ShoppingCart();
        EventManager.getInstance().publish(new GoodsEvent(EventType.ADD_TO_CART,new Merchandise(0, "merch", "test", 100),10));
        EventManager.getInstance().publish(new StringEvent(EventType.LIST_CART,""));
        String output1 = "================================================================================\n";
        String output2 = String.format("%-4s%-22s%-40s%-8s%-6s\n", "ID","name", "description", "price", "count");
        String output3 = "--------------------------------------------------------------------------------\n";
        String output4 = String.format("%-4s%-22s%-40s%-8s%-6s\n", 0, "merch", "test", 100.0, 10);
        String output5 = "================================================================================\n";
        assertEquals(output1+output2+output3+output4+output5,outputStream.toString());
        System.setOut(originalOutput);
        outputStream.reset();
        EventManager.getInstance().reset();   
    }
    @Test
    public void add_and_list_collection_test(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new ShoppingCart();
        EventManager.getInstance().publish(new GoodsEvent(EventType.ADD_TO_CART,new Collection(0, "collection", "test"),10));
        EventManager.getInstance().publish(new StringEvent(EventType.LIST_CART,""));
        String output1 = "================================================================================\n";
        String output2 = String.format("%-4s%-22s%-40s%-8s%-6s\n", "ID","name", "description", "price", "count");
        String output3 = "--------------------------------------------------------------------------------\n";
        String output4 = String.format("%-4s%-22s%-40s%-8s%-6s\n", 0, "collection", "test", 0.0, 10);
        String output5 = "================================================================================\n";
        assertEquals(output1+output2+output3+output4+output5,outputStream.toString());
        System.setOut(originalOutput);
        outputStream.reset();
        EventManager.getInstance().reset(); 
    }
    @Test
    public void add_and_list_collection_with_merch_test(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new ShoppingCart();
        Collection col = new Collection(0, "collection", "test");
        col.add(new Merchandise(0, "merch", "test", 100));
        EventManager.getInstance().publish(new GoodsEvent(EventType.ADD_TO_CART,col,10));
        EventManager.getInstance().publish(new StringEvent(EventType.LIST_CART,""));
        String output1 = "================================================================================\n";
        String output2 = String.format("%-4s%-22s%-40s%-8s%-6s\n", "ID","name", "description", "price", "count");
        String output3 = "--------------------------------------------------------------------------------\n";
        String output4 = String.format("%-4s%-22s%-40s%-8s%-6s\n", 0, "collection", "test", 100.0, 10);
        String output5 = "================================================================================\n";
        assertEquals(output1+output2+output3+output4+output5,outputStream.toString());
        System.setOut(originalOutput);
        outputStream.reset();
        EventManager.getInstance().reset();
    }
    @Test
    public void add_and_list_nestedCollection_with_merch_test(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new ShoppingCart();
        Collection col = new Collection(0, "collection", "test");
        Collection col2 = new Collection(1, "collection2", "test");
        col2.add(new Merchandise(0, "merch", "test", 100));
        col.add(col2);
        EventManager.getInstance().publish(new GoodsEvent(EventType.ADD_TO_CART,col,10));
        EventManager.getInstance().publish(new StringEvent(EventType.LIST_CART,""));
        String output1 = "================================================================================\n";
        String output2 = String.format("%-4s%-22s%-40s%-8s%-6s\n", "ID","name", "description", "price", "count");
        String output3 = "--------------------------------------------------------------------------------\n";
        String output4 = String.format("%-4s%-22s%-40s%-8s%-6s\n", 0, "collection", "test", 100.0, 10);
        String output5 = "================================================================================\n";
        assertEquals(output1+output2+output3+output4+output5,outputStream.toString());
        System.setOut(originalOutput);
        outputStream.reset();
        EventManager.getInstance().reset();
    }
    @Test
    public void add_and_list_multiple_merchs_test(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new ShoppingCart();
        EventManager.getInstance().publish(new GoodsEvent(EventType.ADD_TO_CART,new Merchandise(0, "merch1", "test", 100),10));
        EventManager.getInstance().publish(new GoodsEvent(EventType.ADD_TO_CART,new Merchandise(1, "merch2", "test", 200),10));
        EventManager.getInstance().publish(new GoodsEvent(EventType.ADD_TO_CART,new Merchandise(2, "merch3", "test", 300),10));
        EventManager.getInstance().publish(new StringEvent(EventType.LIST_CART,""));
        String output1 = "================================================================================\n";
        String output2 = String.format("%-4s%-22s%-40s%-8s%-6s\n", "ID","name", "description", "price", "count");
        String output3 = "--------------------------------------------------------------------------------\n";
        String output4 = String.format("%-4s%-22s%-40s%-8s%-6s\n", 0, "merch1", "test", 100.0, 10);
        String output5 = String.format("%-4s%-22s%-40s%-8s%-6s\n", 1, "merch2", "test", 200.0, 10);
        String output6 = String.format("%-4s%-22s%-40s%-8s%-6s\n", 2, "merch3", "test", 300.0, 10);
        String output7 = "================================================================================\n";
        assertEquals(output1+output2+output3+output4+output5+output6+output7,outputStream.toString());
        System.setOut(originalOutput);
        outputStream.reset();
        EventManager.getInstance().reset();   
    }
    @Test
    public void list_receipt_and_publish_purchase_test(){
        EventManager.getInstance().subscribe(EventType.PURCHASE, this);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new ShoppingCart();
        EventManager.getInstance().publish(new GoodsEvent(EventType.ADD_TO_CART,new Merchandise(0, "merch1", "test", 100),10));
        EventManager.getInstance().publish(new StringEvent(EventType.PRINT_RECEIPT,"$100"));
        String output1 = "================================================================================\n";
        String output2 = "Receipt:\n";
        String output3 = String.format("%-40s%-10s%-10s\n", "name","price", "count");
        String output4 = String.format("%-40s%-10s%-10s\n", "merch1", "$100.0", 10);
        String output5 = "--------------------------------------------------------------------------------\n";
        String output6 = "Total Price: $100\n";
        String output7 = "================================================================================\n";
        assertEquals(output1+output2+output3+output4+output5+output6+output7,outputStream.toString());
        assertEquals(1, this.isPURCHASE);
        this.reset();
        System.setOut(originalOutput);
        outputStream.reset();
        EventManager.getInstance().reset();   
    }
    @Test
    public void list_receipt_with_two_items_publish_purchase_test(){
        EventManager.getInstance().subscribe(EventType.PURCHASE, this);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new ShoppingCart();
        EventManager.getInstance().publish(new GoodsEvent(EventType.ADD_TO_CART,new Merchandise(0, "merch1", "test", 50),10));
        EventManager.getInstance().publish(new GoodsEvent(EventType.ADD_TO_CART,new Merchandise(1, "merch2", "test", 50),10));
        EventManager.getInstance().publish(new StringEvent(EventType.PRINT_RECEIPT,"$100"));
        String output1 = "================================================================================\n";
        String output2 = "Receipt:\n";
        String output3 = String.format("%-40s%-10s%-10s\n", "name","price", "count");
        String output4 = String.format("%-40s%-10s%-10s\n", "merch1", "$50.0", 10);
        String output5 = String.format("%-40s%-10s%-10s\n", "merch2", "$50.0", 10);
        String output6 = "--------------------------------------------------------------------------------\n";
        String output7 = "Total Price: $100\n";
        String output8 = "================================================================================\n";
        assertEquals(output1+output2+output3+output4+output5+output6+output7+output8,outputStream.toString());
        assertEquals(2, this.isPURCHASE);
        this.reset();
        System.setOut(originalOutput);
        outputStream.reset();
        EventManager.getInstance().reset();   
    }
    @Test
    public void calculate_publish_test(){
        EventManager.getInstance().subscribe(EventType.CALCULATE, this);
        new ShoppingCart();
        EventManager.getInstance().publish(new StringEvent(EventType.PAY, "100"));
        assertEquals(1,this.isCALCULATE);
        EventManager.getInstance().reset();
        this.reset();
    }
}
