package cn.myfreecloud.store.constants;

public interface Constant {
	/*  用户相关的常量     */
	/**
	 * 用户已激活
	 */
	int USER_IS_ACTIVE=1;
	/**
	 * 用户未激活
	 */
	int USER_IS_NOT_ACTIVE=0;	
	
	
	/*  管理员关的常量     */
	/**
	 * 用户已激活
	 */
	int USER_IS_ADMIN=1;
	/**
	 * 用户未激活
	 */
	int USER_IS_NOT_ADMIN=0;	
	
	
	
	
	/*  商品相关的常量     */
	/**
	 *热门商品
	 */
	int PRODUCT_IS_HOT=1;
	/**
	 *不是热门商品
	 */
	int PRODUCT_IS_NOT_HOT=0;
	
	/*  订单相关的常量     */
	/**
	 * 订单未付款
	 */
	int ORDER_WEIFUKUAN=0;
	/**
	 * 订单已付款
	 */
	int ORDER_YIFUKUAN=1;
	/**
	 * 订单已发货
	 */
	int ORDER_YIFAHUO=2;
	/**
	 * 订单已完成
	 */
	int ORDER_YIWANCHENG=3;
	
	/**
	 * 上架
	 */
	int PRODUCT_ON=0;
	/**
	 * 下架
	 */
	int PRODUCT_OFF=1;
	
	
}
