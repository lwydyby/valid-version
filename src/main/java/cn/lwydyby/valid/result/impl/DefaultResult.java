package cn.lwydyby.valid.result.impl;

import cn.lwydyby.valid.exception.ValidRuntimeException;
import cn.lwydyby.valid.result.IConstraintResult;
import cn.lwydyby.valid.result.IResult;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author lwydyby
 * @title: DefaultResult
 * @projectName valid
 * @description: TODO
 * @date 2020-01-03 09:06
 */
@Data
@ToString
public class DefaultResult implements IResult {

    /**
     * 是否全部通过验证
     */
    private boolean pass;

    /**
     * 未通过的列表信息
     */
    private List<IConstraintResult> notPassList;

    /**
     * 所有的验证结果列表
     */
    private List<IConstraintResult> allList;

    @Override
    public boolean pass() {
        return pass;
    }

    @Override
    public List<IConstraintResult> notPassList() {
        return notPassList;
    }

    @Override
    public List<IConstraintResult> allList() {
        return allList;
    }

    @Override
    public IResult print() {
        System.out.println(this);
        return this;
    }

    @Override
    public IResult throwsEx() {
        if(!pass) {
            final String message = this.notPassList.get(0).message();
            throw new ValidRuntimeException(message);
        }
        return this;
    }
}
