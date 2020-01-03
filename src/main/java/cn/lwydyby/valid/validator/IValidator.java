package cn.lwydyby.valid.validator;

import cn.lwydyby.valid.result.IConstraintResult;
import cn.lwydyby.valid.validator.ValidEntry;

import java.util.List;

public interface IValidator {
    /**
     * 执行校验
     * @param value 校验的实体对象
     * @return 单个约束条件结果列表
     */
    List<IConstraintResult> valid(final Object value, Double version, Class[] group);

    /**
     * 构建要校验信息
     * @param instance 校验的实体对象
     * @return 要校验的信息
     */
    List<ValidEntry> buildValidatorEntryList(final Object instance);
}
