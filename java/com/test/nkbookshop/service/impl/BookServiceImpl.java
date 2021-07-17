package com.test.nkbookshop.service.impl;

import com.test.nkbookshop.dao.BookDao;
import com.test.nkbookshop.dao.jdbcimpl.BookDaoImpl;
import com.test.nkbookshop.domain.po.Book;
import com.test.nkbookshop.service.BookService;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookdao=new BookDaoImpl();
    @Override//还书
    public boolean back(Book book) {
        boolean res=false;
        int result=-1;
        if(book!=null)
        result=bookdao.insert(book);
        if(result==1)
            res=true;
        return res;
    }

    @Override//借书
    public boolean borrow(String name) {
        boolean res=false;
        int result=-1;
        result=bookdao.delete(name);
        if(result!=-1&&result!=0)
            res=true;
        return res;
    }

    @Override
    public List<Book> findALL() {
        List <Book> list=null;
        list=new ArrayList<>();
        return  bookdao.findAll();
    }
}
