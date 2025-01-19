package com.we.service.impl;

import com.we.entity.Merchant;
import com.we.mapper.MerchantMapper;
import com.we.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author we
 * @date 2021-07-17 11:21
 **/
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public List<Merchant> getMerchantList(String name, int page, int limit) {
        return merchantMapper.getMerchantList(name,page,limit);
    }

    @Override
    public List<Merchant> getMerchantList() {
        return merchantMapper.listMerchant();
    }

    @Override
    public Merchant getMerchantById(Integer id) {
        return merchantMapper.getMerchantById(id);
    }

    @Override
    public int add(Merchant merchant) {
        return merchantMapper.add(merchant);
    }

    @Override
    public int update(Merchant merchant) {
        return merchantMapper.update(merchant);
    }

    @Override
    public int updateState(Merchant merchant) {
        return merchantMapper.updateState(merchant);
    }

    @Override
    public int delete(Integer id) {
        return merchantMapper.delete(id);
    }

    @Override
    public int getMerchantCount() {
        return merchantMapper.getMerchantCount();
    }
}
