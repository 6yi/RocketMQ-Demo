package cn.lzheng.exception;


import cn.lzheng.constant.ShopCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

/**
 * 异常抛出类
 */
@Slf4j
public class CastException {

    public static void cast(ShopCode shopCode) {

        log.error(shopCode.toString());

        throw new CustomerException(shopCode);
    }
}
