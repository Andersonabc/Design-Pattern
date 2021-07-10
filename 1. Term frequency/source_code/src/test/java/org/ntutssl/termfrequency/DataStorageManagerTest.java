package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Test;

public class DataStorageManagerTest{ 
    @Test
    public void test_is_data_length(){
        DataStorageManager dsm = new DataStorageManager("input/pride-and-prejudice.txt");
        assertEquals(Integer.valueOf(126070),Integer.valueOf(dsm.getWords().size()));
    }
    @Test
    public void test_is_contain_word(){
        DataStorageManager dsm = new DataStorageManager("input/pride-and-prejudice.txt");
        assertTrue(dsm.getWords().contains("bennet"));
        assertTrue(dsm.getWords().contains("elizabeth"));
        assertTrue(dsm.getWords().contains("darcy"));
    }
}