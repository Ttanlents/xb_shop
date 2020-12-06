用户表:user
	字段：id,username,realName,gender,info,address_id,address_name,phone,注册时间，登陆时间，微信id,qqId
商品表:product
	字段：id,price,title,content,browse_count,publish_Date,store_id,store_name,class_id,is_hot,pic
类别表：category
	字段：id,class_name
店铺表:store
	字段：id,store_name
商品收藏表：user_favorite
	字段：id,用户id,商品id
订单表：
	字段：id,收货地址，收货电话，收货人，提交订单时间，总价钱，userId,订单状态
中间表：id,订单id,商品id,购买数量，该商品价钱

地址表：address
    字段：
        id,用户user_id，user_name,区域，详细地址，邮编,电话
        
        
用户_购物车表：id,userid,商品id,count  ,price
用户_购物车表：id,userid,商品id,count  ,price
用户_购物车表：id,userid,商品id,count  ,price
用户_购物车表：id,userid,商品id,count  ,price
