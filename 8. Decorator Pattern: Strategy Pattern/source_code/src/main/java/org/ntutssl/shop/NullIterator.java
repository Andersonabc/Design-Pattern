package org.ntutssl.shop;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NullIterator implements Iterator<Goods>{
  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public Goods next() {
    throw new NoSuchElementException("Iterator don't have next()");
  }
}
