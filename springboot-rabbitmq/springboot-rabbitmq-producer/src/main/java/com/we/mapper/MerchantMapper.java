package com.we.mapper;

import com.we.entity.Merchant;

import java.util.List;

/**
 * @author we
 * @date 2021-07-15 16:40
 **/
public interface MerchantMapper {
    Merchant getMerchantById(Integer sid);

    public List<Merchant> getMerchantList(String name, int page, int limit);

    public int add(Merchant merchant);

    public int update(Merchant merchant);

    public int updateState(Merchant merchant);

    public int delete(Integer sid);

    int getMerchantCount();
}
