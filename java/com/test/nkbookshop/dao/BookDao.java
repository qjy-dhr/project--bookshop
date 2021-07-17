package com.test.nkbookshop.dao;

import com.test.nkbookshop.domain.po.Book;

import java.util.List;

public interface BookDao extends GenericDao<Book,Integer> {
    List<Book> findByName(String name);
    int delete(String name);
}
