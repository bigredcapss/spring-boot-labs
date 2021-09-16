package com.we.es.repository;

import com.we.es.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * EsBookRepository接口
 * @author we
 * @date 2021-09-16 14:53
 **/
public interface EsBookRepository extends ElasticsearchRepository<Book,String> {

    List<Book> findByTitleOrAuthor(String title,String author);

    List<Book> findByNameOrPrice(String name,Integer price);

    Page<Book> findByName(String name, Pageable page);

    Page<Book> findByNameNot(String name,Pageable page);

    Page<Book> findByPriceBetween(int price,Pageable page);

    Page<Book> findByNameLike(String name,Pageable page);

    @Query("{\"bool\" : {\"must\" : {\"term\" : {\"message\" : \"?0\"}}}}")
    Page<Book> findByMessage(String message, Pageable pageable);



}
