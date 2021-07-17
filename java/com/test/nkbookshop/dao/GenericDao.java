package com.test.nkbookshop.dao;


import com.test.nkbookshop.domain.po.Book;


import java.io.Serializable;
import java.util.List;

/*通用泛型接口
*  完成接口的共性方法    增/删/改/查
*
* */
public interface GenericDao<Entity extends Serializable,ID extends Object> {

    Entity findById(ID id);
    List<Entity> findAll();
    List<Book> findBySQL(String sql, Object...params);
    int insert(Entity entity);
    int update(Entity entity);
    int delete(ID id);
    int delete(Entity entity);
}
