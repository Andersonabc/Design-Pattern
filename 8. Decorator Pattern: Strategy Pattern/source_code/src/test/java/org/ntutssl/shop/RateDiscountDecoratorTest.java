package org.ntutssl.shop;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class RateDiscountDecoratorTest { 
    private Merchandise merch = new Merchandise(3, "Merch", "test", 30);

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test
    public void merch_discount_error_test(){
        exceptionRule.expect(ShopException.class);
        exceptionRule.expectMessage("Discount rate should be [0, 1).");
        new RateDiscountDecorator(merch, 1.1);
    }
    @Test
    public void collection_discount_error_test(){
        Collection coll = new Collection(0, "Collection", "test");
        coll.add(new Merchandise(1, "Merch1", "test", 10));
        coll.add(new Merchandise(2, "Merch2", "test", 20));
        exceptionRule.expect(ShopException.class);
        exceptionRule.expectMessage("Discount rate should be [0, 1).");
        new RateDiscountDecorator(coll, 1.1);
    }
    @Test
    public void decorate_merch_id_test(){
        RateDiscountDecorator rdd = new RateDiscountDecorator(this.merch, 0.5);
        assertEquals(3, rdd.id());
    }
    @Test
    public void decorate_merch_price_test(){
        RateDiscountDecorator rdd = new RateDiscountDecorator(this.merch, 0.5);
        assertEquals(15, rdd.price(), 0);
    }
    @Test
    public void decorate_merch_description_test(){
        RateDiscountDecorator rdd = new RateDiscountDecorator(this.merch, 0.5);
        assertEquals("test", rdd.description());
    }
    @Test
    public void decorate_merch_name_test(){
        RateDiscountDecorator rdd = new RateDiscountDecorator(this.merch, 0.5);
        assertEquals("Merch", rdd.name());
    }
    @Test
    public void decorate_collection_id_test(){
        Collection coll = new Collection(0, "Collection", "test");
        coll.add(new Merchandise(1, "Merch1", "test", 10));
        coll.add(new Merchandise(2, "Merch2", "test", 20));
        RateDiscountDecorator rdd = new RateDiscountDecorator(coll, 0.5);
        assertEquals(0, rdd.id());
    }
    @Test
    public void decorate_collection_price_test(){
        Collection coll = new Collection(0, "Collection", "test");
        coll.add(new Merchandise(1, "Merch1", "test", 10));
        coll.add(new Merchandise(2, "Merch2", "test", 20));
        RateDiscountDecorator rdd = new RateDiscountDecorator(coll, 0.5);
        assertEquals(15, rdd.price(), 0);
    }
    @Test
    public void decorate_collection_description_test(){
        Collection coll = new Collection(0, "Collection", "test");
        coll.add(new Merchandise(1, "Merch1", "test", 10));
        coll.add(new Merchandise(2, "Merch2", "test", 20));
        RateDiscountDecorator rdd = new RateDiscountDecorator(coll, 0.5);
        assertEquals("test", rdd.description());
    }
    @Test
    public void decorate_collection_name_test(){
        Collection coll = new Collection(0, "Collection", "test");
        coll.add(new Merchandise(1, "Merch1", "test", 10));
        coll.add(new Merchandise(2, "Merch2", "test", 20));
        RateDiscountDecorator rdd = new RateDiscountDecorator(coll, 0.5);
        assertEquals("Collection", rdd.name());
    }
}
