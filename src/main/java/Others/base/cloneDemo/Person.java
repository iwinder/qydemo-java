package Others.base.cloneDemo;

/**
 * 实现对象拷贝的类，必须实现Cloneable接口，并覆写clone()方法
 */
public class Person implements Cloneable{
    //姓名
    private String name;
    // 年龄
    private int age;
    // 编号
    private Integer code;

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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", code=" + code +
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
