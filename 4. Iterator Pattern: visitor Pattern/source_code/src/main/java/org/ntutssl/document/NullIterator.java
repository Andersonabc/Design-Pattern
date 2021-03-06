package org.ntutssl.document;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NullIterator implements Iterator<Document>{
  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public Document next() {
    throw new NoSuchElementException("Iterator don't have next()");
  }
}
