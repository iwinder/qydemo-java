package DesignPatterns.Creational.Factory.geekbang.DIContainer;

import DesignPatterns.Creational.Factory.geekbang.DIContainer.entity.BeanDefinition;
import DesignPatterns.Creational.Factory.geekbang.DIContainer.entity.ConstructorArg;
import DesignPatterns.Creational.Factory.geekbang.DIContainer.exceptions.BeanCreationFailureException;
import DesignPatterns.Creational.Factory.geekbang.DIContainer.exceptions.NoSuchBeanDefinitionException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class BeansFactory {

    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>();

    public void addBeanDefinitions(List<BeanDefinition> beanDefinitionList) throws BeanCreationFailureException, NoSuchBeanDefinitionException {
        for (BeanDefinition beanDefinition: beanDefinitionList) {
            this.beanDefinitions.putIfAbsent(beanDefinition.getId(), beanDefinition);

            if (beanDefinition.isLazyInit() == false && beanDefinition.isSingleton()) {
                createBean(beanDefinition);
            }
        }
    }

    private Object  createBean(BeanDefinition beanDefinition) throws NoSuchBeanDefinitionException, BeanCreationFailureException {
        if (beanDefinition.isSingleton() && singletonObjects.contains(beanDefinition)) {
            return singletonObjects.get(beanDefinition);
        }
        Object bean = null;
        try {
            Class beanClass = Class.forName(beanDefinition.getClassName());
            List<ConstructorArg> args = beanDefinition.getConstructorArgs();
            if (args.isEmpty()) {
                bean = beanClass.newInstance();
            } else {
                Class[] argClasses =  new Class[args.size()];
                Object[] argObjects = new Object[args.size()];
                for (int i = 0; i < args.size(); ++i) {
                    ConstructorArg arg = args.get(i);
                    if (!arg.getIsRef()) {
                        argClasses[i] = arg.getType();
                        argObjects[i] = arg.getArg();
                    }else {
                        BeanDefinition refBeanDefinition = beanDefinitions.get(arg.getArg());
                        if (refBeanDefinition == null) {
                            throw new NoSuchBeanDefinitionException("Bean is not defined: " + arg.getArg());
                        }            argClasses[i] = Class.forName(refBeanDefinition.getClassName());
                        argObjects[i] = createBean(refBeanDefinition);
                    }
                }
                bean = beanClass.getConstructor(argClasses).newInstance(argObjects);
            }
        } catch (ClassNotFoundException | IllegalAccessException |
                InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new BeanCreationFailureException("", e);
        }


        if (bean != null && beanDefinition.isSingleton()) {
            singletonObjects.putIfAbsent(beanDefinition.getId(), bean);
            return singletonObjects.get(beanDefinition.getId());
        }
        return bean;
    }

    public Object getBean(String beanId) throws NoSuchBeanDefinitionException, BeanCreationFailureException {
        BeanDefinition beanDefinition = beanDefinitions.get(beanId);
        if (null == beanDefinition) {
            throw new NoSuchBeanDefinitionException("Bean is not defined: " + beanId);
        }
        return createBean(beanDefinition);
    }
}
