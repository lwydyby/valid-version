package cn.lwydyby.valid.condition;

import cn.lwydyby.valid.context.IConditionContext;

import java.lang.annotation.Annotation;

/**
 * @author lwydyby
 * @title: AbstractAnnotationCondition
 * @projectName valid
 * @description: TODO
 * @date 2020-01-02 17:32
 */
public abstract class AbstractAnnotationCondition<A extends Annotation> implements ICondition{
    /**
     * 注解信息
     */
    private A annotation;

    protected abstract ICondition buildCondition(final A annotation);

    public void initialize(A annotation) {
        this.annotation = annotation;
    }

    protected String annotationName() {
        return "annotation";
    }

    @Override
    public boolean condition(IConditionContext conditionContext) {
        ICondition condition = buildCondition(annotation);
        return condition.condition(conditionContext);
    }
}
