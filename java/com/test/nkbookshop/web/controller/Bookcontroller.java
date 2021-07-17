package com.test.nkbookshop.web.controller;

import com.test.nkbookshop.domain.po.Book;
import com.test.nkbookshop.service.BookService;
import com.test.nkbookshop.service.impl.BookServiceImpl;

import java.util.List;

public class Bookcontroller {


    private BookService bookservice=new BookServiceImpl();

    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    //1.还书
    public String giveback(){
        //获取视图层前端传过来的请求参数 ====前端完成
        //调用下一层（业务逻辑层）
        boolean res=bookservice.back(book);
        if(res)
            return "book "+book.getName()+" back success";
        else
            return "book "+book.getName()+" back fail";

    }
    //2.借书
    public String borrowbook(){
        //获取视图层前端传过来的请求参数 ====前端完成
        //调用下一层（业务逻辑层）
        boolean res=bookservice.borrow(book.getName());
        if(res)
            return "book "+book.getName()+" borrow success";
        else
            return "book "+book.getName()+" borrow fail";

    }
    public List<Book> showAllBooks(){

        return bookservice.findALL();

    }

}
