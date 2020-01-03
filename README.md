### 简介

java bean基于注解的校验框架，由于要使用openstack java sdk，而openstack本身是支持多个版本的，
所以需要同一个实体类按版本号进行不同的校验，所以java/spring自带的校验工具并不能满足需求,所以
手撸了一个简易的可以分版本校验的通用框架。（目前只支持了notNull注解，后续根据工作需求会同步更新
，或者有人使用后可以提PR）

### 使用说明

####1. 添加注解

````java
@Data
public class Project {
    @NotNull(version = 2.8)
    private String projectId;

}
````

####2.调用校验

````java
class test{
    public static void main(String[] args){
      IResult result=ValidFactory.on(value,version,new Class[0]).valid().result();
      //控制台打印结果
      result.print();
      if(!result.pass()){
          result.throwsEx();
      }
    }
}
````

### 增加其他校验流程

#### 1. 重写AbstractAnnotationCondition
 
 ````java
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
````   

#### 2. 编写自定义注解，来指定校验所用的类

````java
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
````
