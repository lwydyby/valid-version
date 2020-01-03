package cn.lwydyby.valid.result.handler;

import cn.lwydyby.valid.result.IConstraintResult;
import cn.lwydyby.valid.result.IResult;
import cn.lwydyby.valid.result.handler.Impl.BaseResultHandler;

import java.util.List;

/**
 * @author liwei
 * @title: IResultHandler
 * @projectName sdkproxy
 * @description: 结果处理
 * @date 2020-01-03 09:03
 */
public interface IResultHandler<T> {
    /**
     * 对约束结果进行统一处理
     * @param constraintResultList 约束结果列表
     * @return 结果
     */
    T handle(final List<IConstraintResult> constraintResultList);

    static IResultHandler<IResult> simple(){
        return new BaseResultHandler();
    }
}
