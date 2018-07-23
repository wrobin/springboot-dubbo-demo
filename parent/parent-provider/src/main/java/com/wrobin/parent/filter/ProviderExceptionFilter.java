package com.wrobin.parent.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.wrobin.common.dto.resp.RespDTO;
import com.wrobin.common.enums.RespStatus;
import com.wrobin.common.exceptions.CommonException;

/**
 * Created by Administrator on 2018/3/17 0017.
 * 此类是配置中服务提供端的异常过滤器，统一包装服务端所有的异常，返回自定义的DubboResult
 * 注意：需配置META-INF/dubbo/com.alibaba.dubbo.rpc.Filter文件
 */
@Activate(group = Constants.PROVIDER, value = {"providerExceptionFilter"})
public class ProviderExceptionFilter implements Filter{
    private static Logger logger = LoggerFactory.getLogger(ProviderExceptionFilter.class);

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = invoker.invoke(invocation);
        if (result.hasException() && GenericService.class != invoker.getInterface()) {
            try {
                Throwable exception = result.getException();
                logger.error("Got unchecked and undeclared exception which called by " + RpcContext.getContext().getRemoteHost()
                        + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName()
                        + ", exception: " + exception.getClass().getName() + ": " + exception.getMessage(), exception);


                RespDTO<String> respDTO;
                // 自定义的异常
                if (exception instanceof CommonException) {
                    CommonException commonException = (CommonException)exception;
                    respDTO = RespDTO.fail(commonException.getErrorCode(),commonException.getErrorMsg());
                }else {
                    respDTO = RespDTO.fail(RespStatus.FAIL.getCode(),exception.getMessage());
                }
                return new RpcResult(respDTO);
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
                return result;
            }
        }
        return result;
    }
}
