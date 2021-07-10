package org.ntutssl.shop;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;

public class GoodsBuilderTest { 
    @Test
    public void build_Merch_test(){
        GoodsBuilder gb = new GoodsBuilder();
        gb.buildMerchandise(0, "Merch", "builtMerch", 99);
        Merchandise merch = (Merchandise)gb.getResult();
        assertEquals(0, merch.id());
        assertEquals("Merch", merch.name());
        assertEquals("builtMerch", merch.description());
        assertEquals(99, merch.price(), 0);
    }
    @Test
    public void build_Collection_test(){
        GoodsBuilder gb = new GoodsBuilder();
        gb.startBuildCollection(0, "Collection", "builtCollection");
        gb.endBuildCollection();
        Collection coll = (Collection)gb.getResult();
        assertEquals(0, coll.id());
        assertEquals("Collection", coll.name());
        assertEquals("builtCollection", coll.description());
    }
    @Test
    public void build_Collection_with_Merch_test(){
        GoodsBuilder gb = new GoodsBuilder();
        gb.startBuildCollection(0, "Collection", "builtCollection");
        gb.buildMerchandise(0, "Merch", "builtMerch", 99);
        gb.endBuildCollection();
        Collection coll = (Collection)gb.getResult();
        assertEquals(0, coll.id());
        assertEquals("Collection", coll.name());
        assertEquals("builtCollection", coll.description());
        assertTrue(coll.iterator().hasNext());
        Merchandise merch = (Merchandise)coll.iterator().next();
        assertEquals(0, merch.id());
        assertEquals("Merch", merch.name());
        assertEquals("builtMerch", merch.description());
        assertEquals(99, merch.price(), 0);
    }
    @Test
    public void build_nested_Collections_with_Merch_test(){
        GoodsBuilder gb = new GoodsBuilder();
        gb.startBuildCollection(1, "Collection1", "builtCollection1");
        gb.buildMerchandise(1, "Merch1", "builtMerch1", 100);
        gb.startBuildCollection(2, "Collection2", "builtCollection2");
        gb.buildMerchandise(2, "Merch2", "builtMerch2", 200);
        gb.endBuildCollection();
        gb.endBuildCollection();
        Collection coll = (Collection)gb.getResult();
        assertEquals(1, coll.id());
        assertEquals("Collection1", coll.name());
        assertEquals("builtCollection1", coll.description());
        Iterator<Goods> itr = coll.iterator();
        assertTrue(itr.hasNext());
        Merchandise merch = (Merchandise)itr.next();
        assertEquals(1, merch.id());
        assertEquals("Merch1", merch.name());
        assertEquals("builtMerch1", merch.description());
        assertEquals(100, merch.price(), 0);
        assertTrue(itr.hasNext());
        Collection coll2 = (Collection)itr.next();
        assertEquals(2, coll2.id());
        assertEquals("Collection2", coll2.name());
        assertEquals("builtCollection2", coll2.description());
        assertTrue(coll2.iterator().hasNext());
        Merchandise merch2 = (Merchandise)coll2.iterator().next();
        assertEquals(2, merch2.id());
        assertEquals("Merch2", merch2.name());
        assertEquals("builtMerch2", merch2.description());
        assertEquals(200, merch2.price(), 0);

    }

}