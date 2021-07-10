package org.ntutssl.shop;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class FixedDiscountDecoratorTest { 
    private Merchandise merch = new Merchandise(3, "Merch", "test", 30);

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test
    public void merch_discount_error_test(){
        exceptionRule.expect(ShopException.class);
        exceptionRule.expectMessage("Fixed discount should be lower than the price of goods.");
        new FixedDiscountDecorator(merch, 50);
    }
    @Test
    public void collection_discount_error_test(){
        Collection coll = new Collection(0, "Collection", "test");
        coll.add(new Merchandise(1, "Merch1", "test", 10));
        coll.add(new Merchandise(2, "Merch2", "test", 20));
        exceptionRule.expect(ShopException.class);
        exceptionRule.expectMessage("Fixed discount should be lower than the price of goods.");
        new FixedDiscountDecorator(coll, 40);
    }
    @Test
    public void decorate_merch_id_test(){
        FixedDiscountDecorator fdd = new FixedDiscountDecorator(this.merch, 5);
        assertEquals(3, fdd.id());
    }
    @Test
    public void decorate_merch_price_test(){
        FixedDiscountDecorator fdd = new FixedDiscountDecorator(this.merch, 5);
        assertEquals(25, fdd.price(), 0);
    }
    @Test
    public void decorate_merch_description_test(){
        FixedDiscountDecorator fdd = new FixedDiscountDecorator(this.merch, 5);
        assertEquals("test", fdd.description());
    }
    @Test
    public void decorate_merch_name_test(){
        FixedDiscountDecorator fdd = new FixedDiscountDecorator(this.merch, 5);
        assertEquals("Merch", fdd.name());
    }
    @Test
    public void decorate_collection_id_test(){
        Collection coll = new Collection(0, "Collection", "test");
        coll.add(new Merchandise(1, "Merch1", "test", 10));
        coll.add(new Merchandise(2, "Merch2", "test", 20));
        FixedDiscountDecorator fdd = new FixedDiscountDecorator(coll, 20);
        assertEquals(0, fdd.id());
    }
    @Test
    public void decorate_collection_price_test(){
        Collection coll = new Collection(0, "Collection", "test");
        coll.add(new Merchandise(1, "Merch1", "test", 10));
        coll.add(new Merchandise(2, "Merch2", "test", 20));
        FixedDiscountDecorator fdd = new FixedDiscountDecorator(coll, 20);
        assertEquals(10, fdd.price(), 0);
    }
    @Test
    public void decorate_collection_description_test(){
        Collection coll = new Collection(0, "Collection", "test");
        coll.add(new Merchandise(1, "Merch1", "test", 10));
        coll.add(new Merchandise(2, "Merch2", "test", 20));
        FixedDiscountDecorator fdd = new FixedDiscountDecorator(coll, 20);
        assertEquals("test", fdd.description());
    }
    @Test
    public void decorate_collection_name_test(){
        Collection coll = new Collection(0, "Collection", "test");
        coll.add(new Merchandise(1, "Merch1", "test", 10));
        coll.add(new Merchandise(2, "Merch2", "test", 20));
        FixedDiscountDecorator fdd = new FixedDiscountDecorator(coll, 20);
        assertEquals("Collection", fdd.name());
    }

}
