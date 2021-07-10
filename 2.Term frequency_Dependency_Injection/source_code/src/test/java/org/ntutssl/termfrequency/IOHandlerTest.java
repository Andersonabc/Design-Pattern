package org.ntutssl.termfrequency;

import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class IOHandlerTest { 
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test
    public void test_input_list(){
        IOHandler ioHandler = new IOHandler();
        List<String> data = ioHandler.handleInputAsList("input/stop_words.txt", ",");
        assertEquals("a", data.get(0));
    }
    @Test
    public void test_input_set(){
        IOHandler ioHandler = new IOHandler();
        Set<String> data = ioHandler.handleInputAsSet("input/stop_words.txt", ",");
        assertEquals(119, data.size());
    }
    @Test
    public void fileNotFound_ExceptionIsWork(){
        IOHandler handler = new IOHandler();
        exceptionRule.expect(WordFrequencyException.class);
        exceptionRule.expectMessage("File not found.");
        handler.handleInputAsList("fake.txt", "");
        handler.handleInputAsSet("fake.txt", "");
    }

}
