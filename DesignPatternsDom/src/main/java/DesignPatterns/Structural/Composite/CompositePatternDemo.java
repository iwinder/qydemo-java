package DesignPatterns.Structural.Composite;

/**
 * Description:Java设计模式学习笔记—组合模式
 * User: wind
 * Date: 2017-06-27
 * Time: 13:16 下午
 */
public class CompositePatternDemo {
    public static void main(String[] args) {
        Employee CEO = new Employee("John","CEO",30000);

        Employee headSales = new Employee("Robert","Head Sales",20000);

        Employee headMarketing = new Employee("Michel","Head Marketing",20000);

        Employee clerk1 = new Employee("Laura","Marketing",10000);
        Employee clerk2 = new Employee("Bob","Marketing",10000);

        Employee salesExecutive1 = new Employee("Richard","Sales", 10000);
        Employee salesExecutive2 = new Employee("Rob","Sales", 10000);

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        System.out.println(CEO);
        for (Employee headEmployee:CEO.getSubordinates()){
            System.out.println(headEmployee);
            for (Employee employee:headEmployee.getSubordinates()){
                System.out.println(employee);
            }
        }
    }
}
