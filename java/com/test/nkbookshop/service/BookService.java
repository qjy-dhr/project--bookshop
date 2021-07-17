package com.test.nkbookshop.service;

import com.test.nkbookshop.domain.po.Book;

import java.util.List;

public interface BookService {


   boolean back(Book book);//还书
     boolean borrow(String name);//借书

    //boolean modify(Book book);//更新

    List<Book> findALL();//查询全部


}


