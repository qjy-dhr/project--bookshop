package com.test.nkbookshop.dao.jdbcimpl;

import com.test.nkbookshop.dao.BookDao;
import com.test.nkbookshop.domain.po.Book;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {


        BookDao bookdao=new BookDaoImpl();
        List<Book> list=new ArrayList<>();
        list = bookdao.findByName("lucky");
        System.out.println(list.isEmpty());
        for(Book book:list){
            System.out.println(book);
        }

    }

}
