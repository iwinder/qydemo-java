package DesignPatterns.Creational.Factory.geekbang.DIContainer.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BeanDefinition {

    private String id;
    private String className;
    private List<ConstructorArg> constructorArgs = new ArrayList<>();
    private Scope scope = Scope.SINGLETON;
    private boolean lazyInit = false;

    public boolean isSingleton() {
        return   scope.equals(Scope.SINGLETON);
    }


}
