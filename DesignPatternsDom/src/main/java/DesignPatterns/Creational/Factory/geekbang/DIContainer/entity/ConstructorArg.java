package DesignPatterns.Creational.Factory.geekbang.DIContainer.entity;

import lombok.Data;

@Data
public class ConstructorArg {
    private boolean isRef;
    private Class type;
    private Object arg;

    public boolean getIsRef() {
        return isRef;
    }
}
