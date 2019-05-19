package com.seling.server04.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
//import com.seling.common.utils.PageInfo;
import com.seling.server04.entity.OrderInfoEntity;
import com.seling.server04.entity.mapper.OrderInfoMapper;
import com.seling.server04.form.OrderInfoForm;
import com.seling.server04.service.OrderService;
import com.seling.server04.exception.SeverException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderInfoMapper orderInfoMapper;
	
	@Override
	public OrderInfoEntity getOrderInfoById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<OrderInfoEntity> getOrderInfo(OrderInfoForm orderInfoForm) throws Exception {
		// TODO Auto-generated method stub
		PageHelper.startPage(orderInfoForm.getPageNum(),orderInfoForm.getPageSize());
    	Page<OrderInfoEntity> page = orderInfoMapper.getOrderInfoByPage(orderInfoForm);
    	if ( null != page ) {
    		return new PageInfo(page);
    	} else {
    		throw new SeverException("1", "查询异常"); 
    	}
	}

}
