package com.test.nkbookshop.dao.jdbcimpl;

import com.test.nkbookshop.dao.BookDao;
import com.test.nkbookshop.domain.po.Book;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        int id=1;
        String book_name="lucky";
        double price=10.3;
        String author="aurhor4";
        Book tempbook =new Book(id,book_name,price,author);

        BookDao bookdao=new BookDaoImpl();

        List<Book> list=new ArrayList<>();



        System.out.println(bookdao.insert(tempbook)) ;
        list = bookdao.findAll();
        for(Book book:list){
            System.out.println(book);
        }


    }

}
