package cn.lwydyby.valid.annotion;

import cn.lwydyby.valid.annotion.condition.NotNullCondition;
import cn.lwydyby.valid.condition.Condition;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Condition(NotNullCondition.class)
public @interface NotNull {
    /**
     * 校验的起始版本号
     * @return 版本号
     */
    double version();

    /**
     * 校验的分组
     * @return 校验的分组
     */
    Class[] group() default {};

    String message() default "%s Must Not NUll";
}
