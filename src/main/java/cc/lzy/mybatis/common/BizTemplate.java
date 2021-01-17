/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * 业务执行模板
 *
 * @author taigai
 * @version BizTemplate.java, v 0.1 2021年01月10日 11:08 taigai Exp $
 */
public class BizTemplate {
    /**
     * private
     */
    private BizTemplate() {
    }

    /**
     * 业务模版
     *
     * 给rpc注册服务使用
     *
     * @param logger    日志
     * @param result    结果
     * @param action    具体动作
     */
    public static void execute(Logger logger, IBaseResult result, BizAction action) {
        logger.info("[BizTemplate] begin..");

        long begin = System.currentTimeMillis();

        try {
            action.before();
            action.execute();
            buildSuccessResult(result);
        } catch (BizException e) {
            // 业务异常捕获
            IBizResultCodeEnum resultCodeEnum = e.getResultCode();
            if (resultCodeEnum.getErrorLevel() == BizErrorLevelEnum.LEVEL_WARN) {
                logger.warn("[BizTemplate]业务异常", e);
            } else {
                logger.error("[BizTemplate]业务异常", e);
            }
            result.setResultCode(resultCodeEnum.getCode());
            if (StringUtils.isNotBlank(e.getResultView())) {
                result.setResultMsg(e.getResultView());
            } else {
                result.setResultMsg(resultCodeEnum.getDesc());
            }
        } catch (Exception e) {
            // 系统异常捕获
            logger.error("[BizTemplate] 系统异常", e);
            // 系统异常捕获
            result.setResultCode("SYSTEM_ERROR");
            result.setResultMsg("SYSTEM_ERROR");
        } finally {
            long elapse = System.currentTimeMillis() - begin;
            try {
                action.after(elapse);
            } catch (Exception e) {
                logger.error("后置执行异常", e);
            }
            logger.info("[BizTemplate] end，耗时[{}]", elapse);
        }
    }

    /**
     * 构造成功结果
     * @param result 结果基类
     */
    private static void buildSuccessResult(IBaseResult result) {
        if (result != null) {
            //进行默认操作
            result.setSuccess(true);
            if (StringUtils.isBlank(result.getResultCode())) {
                result.setResultCode("SUCCESS");
            }
            result.setResultMsg("SUCCESS");
        }
    }
}