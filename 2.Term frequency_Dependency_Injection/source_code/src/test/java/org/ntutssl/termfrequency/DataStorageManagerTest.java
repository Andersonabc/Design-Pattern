package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.FileNotFoundException;

import org.junit.Test;

public class DataStorageManagerTest{ 
    @Test
    public void test_is_data_length(){
        IOHandler ioHandler = new IOHandler();
        DataStorageManager dsm = new DataStorageManager("input/pride-and-prejudice.txt",ioHandler);
        assertEquals(Integer.valueOf(126070),Integer.valueOf(dsm.getWords().size()));
    }
    @Test
    public void test_is_contain_word(){
        IOHandler ioHandler = new IOHandler();
        DataStorageManager dsm = new DataStorageManager("input/pride-and-prejudice.txt",ioHandler);
        assertTrue(dsm.getWords().contains("bennet"));
        assertTrue(dsm.getWords().contains("elizabeth"));
        assertTrue(dsm.getWords().contains("darcy"));
    }
    @Test
    public void test_not_contain_word(){
        IOHandler ioHandler = new IOHandler();
        DataStorageManager dsm = new DataStorageManager("input/pride-and-prejudice.txt",ioHandler);
        assertFalse(dsm.getWords().contains("moshimoshi"));
        assertFalse(dsm.getWords().contains("chengcheng"));
    }
}