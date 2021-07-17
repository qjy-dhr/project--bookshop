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

    @Override
    public int insert(Book book) {
        int res=-1;
       // Book.count++;
        try {
            this.getConnection();
            String sql="insert into mydb.books values(null,?,?,?)";
            this.execteUpdate(sql,book.getName(),book.getPrice(),book.getAuthor());
            res=result;
            this.closeALL(rs,pstmt,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public int update(Book book) {
        int res=-1;
        try {
            this.getConnection();
            String sql="update mydb.books set book_name=?,price=?,author=? where id=?";
            this.execteUpdate(sql,book.getName(),book.getPrice(),book.getAuthor());
            res=result;
            this.closeALL(rs,pstmt,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public int delete(Integer id) {
        int res=-1;
//        if(Book.count<=0){
//
//            return res;
//        }
//       else
        try {
            this.getConnection();
            String sql="delete from mydb.books where book_id=?";
            this.execteUpdate(sql,id);
            res=result;
            this.closeALL(rs,pstmt,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
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
        int res = -1;
        int temp = 0;
        List<Book> list=new ArrayList<>();
        list=this.findByName(name);
if(list.isEmpty()){

    return -1;
}
else{
    for(Book book:list){
        temp=book.getId();
    }
    return  this.delete(temp);
}
    }
}
