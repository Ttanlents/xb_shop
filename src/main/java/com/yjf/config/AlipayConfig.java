package com.yjf.config;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号  2021001168671509
    public static String app_id = "2016102800774074";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCQU2n4gncXzo/xHyEUG7ZXSVToR+mOl7L5JJwAt6tX2UrYCCS5Vz8n1okWzjMs179qNNnvhwyweYwHRQtbrh5HHVKs/iISh+jhmxwRpU44bkvFzfQsx8Q5+0Bhull1ahs/4QnY+HmDYR142f4uH3pH3vNFMZGGswf9eycZhydmZ0x3TWbpP0/mxGlEZk8mCb+66RMlNNMMPNT8M6ZIpCeIUjvGvMvvI4ottKBfy1zVpWFYV1MC1O9QbBtxUdwFCxWEkJAn52PBUpZ+WEguIYQpRsn+QoE9c+z9L1utQWX3tVzF4iFw54ge4Pi+Rh/LnEFd3Z7Qc6D/j+YhUFt4iBK3AgMBAAECggEAUqlz6IHQ9DG6/790kq/Mn/HAT8ngW2tHdqshLsgDuy0ZUOJZJk5yPb8Cha1UYjqvx4S9zhLrkagMK53AA6KWdOS2lZ54pWRyXcUZLoIfavLBM2nikENagFsHZPH2iula8htSKJMoBbd5yd4RVxlwT/nCG79oOl4QFX6TxmtChcOHPVQm/oBEw55ZB5lPTThzuUdjlRpPns8TLXB7LVgsUinuMuGSn30OKkoiSPVRw1Q3EPYEY6riCXTXNR3Tl3nGwGGrhpS/vm88hpFmwZpMnhDtrgjhvHKpFwBFGY7f8YaYSOq9SskVrW4hIIZhE4M8n30tihSlpuF/SW4OU3f7AQKBgQDTPr+AiesQQFp+AtETQWEj7XEWWvB1UXs0mgdvSjZn0fU2oU2U8P4NK+AUd/NW5xzGOPTT3oDuD1RKzp0JQZdXhHi3fayvya11du2veFU5HS8JQ0NZk2aCa8GbfcfBMHBB4v7a6fqozDCrkvNoB9WiAorGvG5fLgLwyjVKYhyZAwKBgQCu5y5HlPew5F+vMtXlbJJXXxIWyPjSUZJc+EyspGFaKJJE5SnGlwdHDQNiF0KP6XkmG826HF4BVXOw7BxuAf82SuSyMqE8BUrPea5hJYgapjCNc0JEN8WTiDLZ+vXeZFxj5GUWlbjtWvS7EG2NB9apmHliZTE8LLpPnmFlwXrfPQKBgQDO9UfLF9P7HQFLOVk6gzA3cbX8j+XLGqQZWdp01wFMX6/c8NVAWYacwuKLSyVVe6F+NmjudaxdDxmnHJtPaTprnLZkFrUrl+vgDD87TSo5hXUCnyqJxNaGainJO8aLZ/hjJJBJSzivTTVVsufTgPF1AwaRRCTiDQMGgyUIleysnwKBgB/mWEGEAP0JWg5JNkvm1nYLB/CR7Rfm+svsGYfvXzYjajIYndrzlsc1r2VHmsNGsOrrbUVBgT+7ByPAnJrP0G1lkXqS02dwC6n1poFfaAh8QSS7Xd21UlrLH4M0S32pTlSFxo4s9pKL4VDw+Roc0OETxyfUDvvLqJt25oq5lCB9AoGBALv2OMOz6b4+SDP2FbBeVNh5IaEWtk0tbkSyNj/Ft0FAc2565w0ckqCoHGOz/AfOxtbsythLyKInGmHoF27zpSCq+VADDpXMlveohGYqCrLjkZA0O1N8Va3XQFNt7uPGefXda/T78SRXz7XgVAdv9jjz37i0iimTNJTFZAUzokB8";


    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvJCRoIZWI4X556G+E8LG/l8N51J0uPhW/qa2e+Y1GOAurAtl6eJXrVEg479qmIma2dDcXIqPFR1BzZiNW/R2DfIXS3Iz/gCFIAfogeoXy5ri8x3m/Me0hv6psLuvkaVeJRYfI+lk2Llr6hpxc5Qt5hf1XWyw9TDn8mcezaQQBl6SJCym2HRz0FWzqx19uZ3x2TGE1/8kDBjp0i+SQS9SDhF3ZeYVXSBap4oEGg8W0QWDzYmXVkrsIVG2HcsIJ7G45jvL0pH/jFwwpmkrpbQGzJfYWyfQU1BfAspVdyNjlysL8KzXWPxqiN1yxXttxuRKif46ePlArawqrGR7i8mZpwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 作用 处理订单更新
    //public static String notify_url = "http://2b7762x523.qicp.vip/mall/user/success_pay.html";
    public static String notify_url = "http://2b7762x523.qicp.vip/aliPay/paySuccess";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数  内网也可以访问
    public static String return_url = "http://192.168.1.120:80/profile.html";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关   沙箱https://openapi.alipaydev.com/gateway.do
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
}


