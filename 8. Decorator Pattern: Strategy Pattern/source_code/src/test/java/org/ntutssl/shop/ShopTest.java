package org.ntutssl.shop;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class ShopTest implements EventListener{
    private int isADD_TO_CART = 0;

    public void onEvent(Event event){
        if(event.type().equals(EventType.ADD_TO_CART)){
            this.isADD_TO_CART += 1;
        }
    }

    public void reset(){
        this.isADD_TO_CART = 0;
    }
    @Test
    public void list_nothing_test(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new Shop();
        EventManager.getInstance().publish(new StringEvent(EventType.LIST_SHOP,""));
        assertEquals("This shop does not sell anything.\n",outputStream.toString());
        System.setOut(originalOutput);
        outputStream.reset();
        EventManager.getInstance().reset(); 
    }
    @Test
    public void list_collection_test(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new Shop();
        EventManager.getInstance().publish(new GoodsEvent(EventType.REPLENISH,new Collection(0, "collection", "test"),10));
        EventManager.getInstance().publish(new StringEvent(EventType.LIST_SHOP,""));
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
    public void list_collectino_with_merch_test(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new Shop();
        Collection col = new Collection(0, "collection", "test");
        col.add(new Merchandise(0, "merch", "test", 100));
        EventManager.getInstance().publish(new GoodsEvent(EventType.REPLENISH,col,10));
        EventManager.getInstance().publish(new StringEvent(EventType.LIST_SHOP,""));
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
    public void list_multiple_merchs_test(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new Shop();
        EventManager.getInstance().publish(new GoodsEvent(EventType.REPLENISH,new Merchandise(0, "merch1", "test", 100),10));
        EventManager.getInstance().publish(new GoodsEvent(EventType.REPLENISH,new Merchandise(1, "merch2", "test", 200),10));
        EventManager.getInstance().publish(new GoodsEvent(EventType.REPLENISH,new Merchandise(2, "merch3", "test", 300),10));
        EventManager.getInstance().publish(new StringEvent(EventType.LIST_SHOP,""));
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
    public void check_stock_out_of_stock_test(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new Shop();
        EventManager.getInstance().publish(new GoodsEvent(EventType.REPLENISH,new Merchandise(0, "merch1", "test", 100),10));
        EventManager.getInstance().publish(new GoodsEvent(EventType.CHECK_STOCK,new Merchandise(0, "merch1", "test", 100),20));
        assertEquals("out of stock. goods ID: 0\n",outputStream.toString());
        System.setOut(originalOutput);
        outputStream.reset();
        EventManager.getInstance().reset();  
    }
    @Test
    public void check_stock_doesnt_have_test(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new Shop();
        EventManager.getInstance().publish(new GoodsEvent(EventType.CHECK_STOCK,new Merchandise(0, "merch1", "test", 100),20));
        assertEquals("The store doesn't have this goods.\n",outputStream.toString());
        System.setOut(originalOutput);
        outputStream.reset();
        EventManager.getInstance().reset();  
    }
    @Test
    public void check_stock_pass_then_publish_test(){
        EventManager.getInstance().subscribe(EventType.ADD_TO_CART, this);
        new Shop();
        EventManager.getInstance().publish(new GoodsEvent(EventType.REPLENISH,new Merchandise(0, "merch1", "test", 100),10));
        EventManager.getInstance().publish(new GoodsEvent(EventType.CHECK_STOCK,new Merchandise(0, "merch1", "test", 100),1));
        assertEquals(1, this.isADD_TO_CART);
        this.reset();
        EventManager.getInstance().reset();  
    }
    @Test
    public void purchase_out_of_stock_test(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new Shop();
        EventManager.getInstance().publish(new GoodsEvent(EventType.REPLENISH,new Merchandise(0, "merch1", "test", 100),10));
        EventManager.getInstance().publish(new GoodsEvent(EventType.PURCHASE,new Merchandise(0, "merch1", "test", 100),20));
        assertEquals("out of stock. goods ID: 0\n",outputStream.toString());
        System.setOut(originalOutput);
        outputStream.reset();
        EventManager.getInstance().reset();  
    }
    @Test
    public void purchase_doesnt_have_test(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new Shop();
        EventManager.getInstance().publish(new GoodsEvent(EventType.PURCHASE,new Merchandise(0, "merch1", "test", 100),20));
        assertEquals("The store doesn't have this goods.\n",outputStream.toString());
        System.setOut(originalOutput);
        outputStream.reset();
        EventManager.getInstance().reset();   
    }
    @Test
    public void after_puchase_list_test(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOutput = System.out;
        System.setOut(printStream);
        new Shop();
        EventManager.getInstance().publish(new GoodsEvent(EventType.REPLENISH,new Merchandise(0, "merch1", "test", 100),10));
        EventManager.getInstance().publish(new GoodsEvent(EventType.PURCHASE,new Merchandise(0, "merch1", "test", 100),5));
        EventManager.getInstance().publish(new StringEvent(EventType.LIST_SHOP,""));
        String output1 = "================================================================================\n";
        String output2 = String.format("%-4s%-22s%-40s%-8s%-6s\n", "ID","name", "description", "price", "count");
        String output3 = "--------------------------------------------------------------------------------\n";
        String output4 = String.format("%-4s%-22s%-40s%-8s%-6s\n", 0, "merch1", "test", 100.0, 5);
        String output5 = "================================================================================\n";
        assertEquals(output1+output2+output3+output4+output5,outputStream.toString());
        System.setOut(originalOutput);
        outputStream.reset();
        EventManager.getInstance().reset();  
    }

}