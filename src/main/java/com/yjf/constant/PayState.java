package com.yjf.constant;

/**
 * @author 余俊锋
 * @date 2020/12/8 19:03
 * @Description
 */
public class PayState {
    public final static Integer NOT_PAY=1;  //未支付
    public final static Integer RECENT_PAY=2; //已支付   待收货
    public final static Integer WAIT_EVALUATION=3;//待评价   去评价
    public final static Integer RECENT_EVALUATION=4;//已经评价  评价完成
}
