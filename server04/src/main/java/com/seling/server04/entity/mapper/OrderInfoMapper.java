package com.seling.server04.entity.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.github.pagehelper.Page;
import com.seling.server04.entity.OrderInfoEntity;
import com.seling.server04.form.OrderInfoForm;

@Mapper
public interface OrderInfoMapper {

	@Select("<script>"
			+ "select * from  order_info where 1=1 "
			+"   order by id desc"
			+ "</script>")
	Page<OrderInfoEntity> getOrderInfoByPage(OrderInfoForm orderInfoForm);
}
