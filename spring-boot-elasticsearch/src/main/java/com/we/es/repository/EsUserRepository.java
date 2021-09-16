package com.we.es.repository;

import com.we.es.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * EsUserRepository接口
 * @author we
 * @date 2021-09-16 15:34
 **/
public interface EsUserRepository extends ElasticsearchRepository<User,Long> {
    Page<User> findUserByName(String name, Pageable pageable);
}
