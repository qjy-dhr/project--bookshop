package com.test.nkbookshop.view;

import com.test.nkbookshop.domain.po.Book;
import com.test.nkbookshop.web.controller.Bookcontroller;

import java.util.List;
import java.util.Scanner;

public class Bookview {
     //还书界面
    public static void giveback(){
        Bookcontroller bookcontroller=new Bookcontroller();


        String book_name="lucky";

        double price=10.3;
        String author="aurhor4";
        Book tempbook =new Book(4,book_name,price,author);

        bookcontroller.setBook(tempbook);
        String result=bookcontroller.giveback();
        System.out.println(result);
    }
    //借书界面
    public static void borrowbook(){
        Bookcontroller bookcontroller=new Bookcontroller();
        String book_name="little-women";
        Book tempbook =new Book(book_name);
        bookcontroller.setBook(tempbook);
        String result=bookcontroller.borrowbook();
        System.out.println(result);
    }
    //展示
    public static void showAll(){
        Bookcontroller bookcontroller=new Bookcontroller();
        List<Book> list=null;
        list=bookcontroller.showAllBooks();
        for(Book book:list){
            System.out.println(book);
        }

    }

    public static void main(String[] args) {
        showAll();
        //borrowbook();
        giveback();

        System.out.println();
        showAll();

    }


}
