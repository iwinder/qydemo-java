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
        Person p2 = (Person)p.clone();
        p2.setName("小明克隆人");
        p2.setAge(12);
        p2.setCode(12345);
        System.out.println(p);
        System.out.println(p2);
        System.out.println("------------------");
    }
}
