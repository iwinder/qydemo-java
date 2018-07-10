package Others.base.cloneDemo;

public class cloneDemo {
    public static void main(String[] args) {
        Person p = new Person("小明",11,123);
        Person p1 = p;
        System.out.println(p);
        System.out.println(p1);
        p1.setCode(1234);
        System.out.println("------------------");
        System.out.println(p);
        System.out.println(p1);
        System.out.println("------------------");
        p.setAddress(new Address("我是一个小学生"));
        Person p2 = (Person)p.clone();
        System.out.println(p);
        System.out.println(p2);
        System.out.println("------------------");
        p2.setName("小明克隆人");
        p2.setAge(12);
        p2.setCode(12345);
        p2.getAddress().setName("我是一个小学生克隆人");
        System.out.println(p);
        System.out.println(p2);
        System.out.println("------------------");
        Person p3 = (Person)p.deepClone();
        System.out.println(p);
        System.out.println(p3);
        System.out.println("------------------");
        p3.setName("小明克隆人");
        p3.setAge(12);
        p3.setCode(12345);
        p3.getAddress().setName("我是一个小学生克隆人233333");
        System.out.println(p);
        System.out.println(p3);
        System.out.println("------------------");


    }
}
