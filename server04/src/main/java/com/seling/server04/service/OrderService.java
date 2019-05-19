package com.seling.server04.service;

import com.github.pagehelper.PageInfo;
import com.seling.server04.entity.OrderInfoEntity;
import com.seling.server04.form.OrderInfoForm;

public interface OrderService {
	
	public OrderInfoEntity getOrderInfoById(String id) throws Exception;
	
	public PageInfo<OrderInfoEntity> getOrderInfo(OrderInfoForm orderInfoForm) throws Exception;
	

}
