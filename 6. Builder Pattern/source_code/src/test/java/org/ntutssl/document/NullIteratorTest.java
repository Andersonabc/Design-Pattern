package org.ntutssl.document;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.util.NoSuchElementException;
import jdk.jfr.Timestamp;

import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class NullIteratorTest { 
    @Test
    public void hasnext_test(){
        NullIterator itr = new NullIterator();
        assertFalse(itr.hasNext());
    }

    @Rule
    public ExpectedException expected = ExpectedException.none();
    @Test
    public void next_test(){
        NullIterator itr = new NullIterator();
        expected.expect(NoSuchElementException.class);
        expected.expectMessage("Iterator don't have next()");
        itr.next();
    }

}