package cn.lwydyby.valid.result.handler.Impl;

import cn.lwydyby.valid.result.IConstraintResult;
import cn.lwydyby.valid.result.IResult;
import cn.lwydyby.valid.result.handler.IResultHandler;
import cn.lwydyby.valid.result.impl.DefaultResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liwei
 * @title: BaseResultHandler
 * @projectName sdkproxy
 * @description: TODO
 * @date 2020-01-03 09:04
 */
public class BaseResultHandler implements IResultHandler<IResult> {
    @Override
    public IResult handle(List<IConstraintResult> constraintResultList) {
        DefaultResult defaultResult=new DefaultResult();
        defaultResult.setAllList(constraintResultList);
        defaultResult.setNotPassList(notPassList(constraintResultList));
        defaultResult.setPass(pass(defaultResult));
        return defaultResult;
    }
    /**
     * 返回所有未通过信息列表
     * @param constraintResultList 验证结果列表
     * @return 所有未通过信息列表
     */
    protected List<IConstraintResult> notPassList(final List<IConstraintResult> constraintResultList) {
        if(constraintResultList==null||constraintResultList.isEmpty()) {
            return new ArrayList<>();
        }
        return constraintResultList.stream()
                .filter(iConstraintResult -> !iConstraintResult.pass())
                .collect(Collectors.toList());
    }
    /**
     * 验证是否通过
     * @param result 验证结果列表
     * @return 是否全部通过
     */
    protected boolean pass(final IResult result) {
        return result.notPassList().isEmpty();
    }
}
