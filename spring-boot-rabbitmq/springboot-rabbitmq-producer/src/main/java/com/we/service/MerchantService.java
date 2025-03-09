package com.we.service;

import com.we.entity.Merchant;

import java.util.List;

/**
 * @author we
 * @date 2021-07-15 16:53
 **/
public interface MerchantService {
    public List<Merchant> getMerchantList(String name, int page, int limit);

    Merchant getMerchantById(Integer id);

    public int add(Merchant merchant);

    public int update(Merchant merchant);

    public int updateState(Merchant merchant);

    public int delete(Integer id);

    int getMerchantCount();
}
