package base.cloneDemo;

import java.io.*;

/**
 * 实现对象拷贝的类，必须实现Cloneable接口，并覆写clone()方法
 */
public class Person implements Serializable, Cloneable{
    //姓名
    private String name;
    // 年龄
    private int age;
    // 编号
    private Integer code;

    private Address address;

    private String a;

    public Person(){

    }

    public Person(String name, int age, Integer code) {
        this.name = name;
        this.age = age;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", code=" + code +
                ", address=" + address +
                ", a=" + a +
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
            // super.clone();
            // return super.clone();

            // 改为深复制：
            Person newPerson = (Person)super.clone();
            // 拷贝一份新的address
            newPerson.address = (Address) address.clone();
            return newPerson;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 利用串行化来做深复制
     * by: windCoder.com
     * @return
     */
    public Object deepClone()  {

        try {
            //写入对象
            ByteArrayOutputStream bo= new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bo);
            os.writeObject(this);
            //读取对象
            ByteArrayInputStream bi=new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi=new ObjectInputStream(bi);
            return(oi.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
