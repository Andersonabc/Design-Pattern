package org.ntutssl.shop;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.Iterator;
public class CollectionTest {
    @Test
    public void get_id_test(){
        Collection collection = new Collection(0, "collection", "test");
        assertEquals(0, collection.id());
    }

    @Test
    public void get_description_test(){
        Collection collection = new Collection(1, "collection", "test");
        assertEquals("test", collection.description());
    }

    @Test
    public void get_name_test(){
        Collection collection = new Collection(1, "collection", "test");
        assertEquals("collection", collection.name());
    }
    @Test
    public void add_merch_test(){
        Collection collection = new Collection(1, "collection", "test");
        collection.add(new Merchandise(0, "merch1", "test", 100));
        assertTrue(collection.iterator().hasNext());
        assertEquals("merch1", collection.iterator().next().name());
    }
    @Test
    public void add_another_collection_test(){
        Collection collection = new Collection(1, "collection", "test");
        Collection collection2 = new Collection(2, "collection2", "test");
        collection.add(collection2);
        assertTrue(collection.iterator().hasNext());
        assertEquals("collection2", collection.iterator().next().name());
    }
    @Test
    public void add_another_collection_with_merch_test(){
        Collection collection = new Collection(1, "collection", "test");
        Collection collection2 = new Collection(2, "collection2", "test");
        collection2.add(new Merchandise(0, "merch1", "test", 100));
        collection.add(collection2);
        assertTrue(collection.iterator().hasNext());
        Collection temp = (Collection)collection.iterator().next();
        assertEquals("collection2", temp.name());
        assertTrue(temp.iterator().hasNext());
        assertEquals("merch1", temp.iterator().next().name());
    }
    @Test
    public void add_multiple_items_test(){
        Collection collection = new Collection(1, "collection", "test");
        collection.add(new Merchandise(0, "merch1", "test", 100));
        collection.add(new Merchandise(1, "merch2", "test", 100));
        Iterator<Goods> itr = collection.iterator();
        assertTrue(itr.hasNext());
        assertEquals("merch1", itr.next().name());
        assertTrue(itr.hasNext());
        assertEquals("merch2", itr.next().name());
    }
    @Test
    public void get_price_of_one_merch_test(){
        Collection collection = new Collection(1, "collection", "test");
        collection.add(new Merchandise(0, "merch1", "test", 100));
        assertEquals(100, collection.price(), 0);
    }
    @Test
    public void get_price_of_collection_with_merch_test(){
        Collection collection = new Collection(1, "collection", "test");
        Collection collection2 = new Collection(2, "collection2", "test");
        collection2.add(new Merchandise(0, "merch1", "test", 100));
        collection.add(collection2);
        assertEquals(100, collection.price(), 0);
    }
    @Test
    public void get_price_of_multiple_items_test(){
        Collection collection = new Collection(1, "collection", "test");
        Collection collection2 = new Collection(2, "collection2", "test");
        collection2.add(new Merchandise(0, "merch1", "test", 100));
        collection.add(collection2);
        collection.add(new Merchandise(0, "merch1", "test", 200));
        assertEquals(300, collection.price(), 0);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test
    public void negativeId_exception_test(){
        exceptionRule.expect(ShopException.class);
        exceptionRule.expectMessage("Goods id should not be negative.");
        Collection collection = new Collection(-1, "collection", "test");
    }
}