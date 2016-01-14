package com.mlnx.chronic.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

public class ResultIterator<T> implements Iterator<T> {

    private final ResultSet resultSet;

    private final ResultTransformer<T> resultTransformer;

    public ResultIterator(ResultSet resultSet, ResultTransformer<T> resultTransformer) {

        this.resultSet = resultSet;
        this.resultTransformer = resultTransformer;
    }

    @Override
    public boolean hasNext() {

        return resultSet != null && !resultSet.isExhausted();
    }

    @Override
    public T next() {

        if (resultSet == null) {
            throw new NoSuchElementException();
        }
        Row result = resultSet.one();
        return result == null ? null : resultTransformer.transform(result);
    }

    @Override
    public void remove() {

        throw new UnsupportedOperationException();
    }
}
