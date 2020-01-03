package cn.lwydyby.valid.annotion.condition;

import cn.lwydyby.valid.annotion.NotNull;
import cn.lwydyby.valid.condition.AbstractAnnotationCondition;
import cn.lwydyby.valid.condition.ICondition;
import cn.lwydyby.valid.context.IConditionContext;
import cn.lwydyby.valid.utils.StringUtils;


import java.util.Arrays;
import java.util.List;

/**
 * @author liwei
 * @title: NotNullCondition
 * @projectName sdkproxy
 * @description: TODO
 * @date 2020-01-03 09:19
 */
public class NotNullCondition extends AbstractAnnotationCondition<NotNull> {
    @Override
    protected ICondition buildCondition(NotNull annotation) {
        return new NotNullConditionImpl(annotation);
    }

    static class NotNullConditionImpl implements ICondition{

        NotNull notNull;

        private NotNullConditionImpl(NotNull notNull) {
            this.notNull = notNull;
        }

        @Override
        public boolean condition(IConditionContext conditionContext) {
            double version=notNull.version();
            double validVersion=conditionContext.version();
            if(validVersion<version){
                return true;
            }
            Class[] group=notNull.group();
            if(group.length!=0){
                //校验分组
                Class[] validGroup=conditionContext.group();
                List<Class> groupList= Arrays.asList(group);
                groupList.retainAll(Arrays.asList(validGroup));
                if(groupList.size()==0){
                    //不在校验范围内，跳出
                    return true;
                }
            }
            return !StringUtils.isEmpty(conditionContext.value());
        }
    }
}
