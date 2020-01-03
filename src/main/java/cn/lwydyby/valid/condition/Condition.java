package cn.lwydyby.valid.condition;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Condition {
    /**
     * 校验条件实现类
     * @return 对应的 class 实现
     * @since 0.1.3
     */
    Class<? extends AbstractAnnotationCondition> value();
}
