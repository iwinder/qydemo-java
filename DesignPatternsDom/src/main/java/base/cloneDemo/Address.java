package base.cloneDemo;

import java.io.Serializable;

public class Address implements Serializable, Cloneable{
    private String name;

    public Address( ) {

    }

    public Address(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * 覆写clone()方法
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
        protected Object clone()   {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
