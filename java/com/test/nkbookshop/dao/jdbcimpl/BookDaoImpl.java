package com.test.nkbookshop.dao.jdbcimpl;

import com.test.nkbookshop.dao.BookDao;
import com.test.nkbookshop.domain.po.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl extends GenericBaseDao implements BookDao {
    @Override
    public Book findById(Integer id) {
        Book book=null;
        try {
            this.getConnection();
            this.execteQuery("select * from mydb.books where book_id=?",id);
            if(rs!=null&&rs.next()){
                book=new Book(rs.getInt("book_id"),rs.getString("book_name"),
                        rs.getDouble("price"),rs.getString("author"));

            } this.closeALL(rs,pstmt,conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books=null;
        try {
            this.getConnection();
            String sql="select *from mydb.books";
            this.execteQuery(sql);
            if(rs!=null){
                books=new ArrayList<>();
                while(rs.next()){
                    Book book= book=new Book(rs.getInt("book_id"),rs.getString("book_name"),
                            rs.getDouble("price"),rs.getString("author"));

                    books.add(book);
                }
            }
            this.closeALL(rs,pstmt,conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;

    }

    @Override
    public List<Book> findBySQL(String sql, Object... params) {
        List<Book> books=null;
        try {
            this.getConnection();

            this.execteUpdate(sql,params);
            if(rs!=null){
                books=new ArrayList<>();
                while(rs.next()){
                    Book book=new Book(rs.getInt("book_id"),rs.getString("book_name"),
                            rs.getDouble("price"),rs.getString("author"));
                    books.add(book);
                }
            }
            this.closeALL(rs,pstmt,conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    @Override//若书在书库里，则返回1，若不在，则返回1
    public int insert(Book book) {
        Book book1=null;
        book1=findById(book.getId());
        List<Book> list=new ArrayList<>();

        list=this.findByName(book.getName());

        if(!list.isEmpty()){//代表书库已经有该书了
            return 1;
        }
       else if(book1!=null){//代表主键重复

            return -1;
        }
        else{

            int res=-1;
            // Book.count++;
            try {
                this.getConnection();
                String sql="insert into mydb.books values(?,?,?,?)";
                this.execteUpdate(sql,book.getId(),book.getName(),book.getPrice(),book.getAuthor());
                res=result;
                this.closeALL(rs,pstmt,conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return res;

        }

    }

    @Override
    public int update(Book book) {
        int res=-1;
        try {
            this.getConnection();
            String sql="update mydb.books set book_name=?,price=?,author=? where id=?";
            this.execteUpdate(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getId());
            res=result;
            this.closeALL(rs,pstmt,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public int delete(Integer id) {
        Book book=null;
        book=findById(id);
        if(book==null){
            return -1;
        }

//        int res=-1;
//        try {
//            this.getConnection();
//            String sql="delete from mydb.books where book_id=?";
//            this.execteUpdate(sql,id);
//            res=result;
//            this.closeALL(rs,pstmt,conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

       else  return 1;
    }

    @Override
    public int delete(Book book) {
        return this.delete(book.getId());
    }

    @Override
    public List<Book> findByName(String name) {
            List<Book> books=null;
            try {
                this.getConnection();
                String sql="select *from mydb.books where book_name=?";
                this.execteQuery(sql,name);

                if(rs!=null){
                    books=new ArrayList<>();
                    while(rs.next()){
                        Book book= book=new Book(rs.getInt("book_id"),rs.getString("book_name"),
                                rs.getDouble("price"),rs.getString("author"));

                        books.add(book);
                    }
                }
                this.closeALL(rs,pstmt,conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return books;


    }

    @Override
    public int delete(String name) {
//        int res = -1;
//        int temp = 0;
        List<Book> list=new ArrayList<>();
        list=this.findByName(name);
        if(list.isEmpty()){

    return -1;
         }
      else{
//    for(Book book:list){
//        temp=book.getId();
            return 1;
    }
  //  return  this.delete(temp);
//}
    }

}
