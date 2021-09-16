package com.we.es.service;

import com.google.common.collect.Lists;
import com.we.es.entity.User;
import com.we.es.repository.EsUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author we
 * @date 2021-09-16 15:41
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EsUserRepository repository;

    @Override
    public boolean insert(User user) {
        boolean flag = false;
        try {
            repository.save(user);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<User> search(String searchContent) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        log.info("查询的语句:"+builder);
        Iterable<User> searchResult = repository.search(builder);
        List<User> list = Lists.newArrayList(searchResult);
        return list;
    }

    @Override
    public List<User> searchByName(Integer pageNumber, Integer pageSize, String name) {
        Page<User> searchPageResults = repository.findUserByName(name, PageRequest.of(pageNumber, pageSize));
        return searchPageResults.getContent();
    }

    @Override
    public List<User> searchUser(Integer pageNumber, Integer pageSize, String searchContent) {
        // 分页参数
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
        log.info("查询的语句:"+searchQuery.getQuery().toString());
        Page<User> searchPageResults = repository.search(searchQuery);
        return searchPageResults.getContent();
    }

    @Override
    public List<User> searchUserByWeight(String searchContent) {
        // 根据权重进行查询
        FunctionScoreQueryBuilder functionScoreQuery = QueryBuilders.functionScoreQuery(QueryBuilders.matchQuery("name", searchContent));
        log.info("查询的语句:"+functionScoreQuery.toString());
        Iterable<User> searchResult = repository.search(functionScoreQuery);
        List<User> list = Lists.newArrayList(searchResult);
        return list;
    }
}
