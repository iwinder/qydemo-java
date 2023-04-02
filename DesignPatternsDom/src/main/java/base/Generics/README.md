## 泛型
用于类和接口区别不大。

### 局限性
基本类型无法作为类型参数。

### Tips
- 对象想在循环语句中使用，需要实现Iterable接口。
- 是否拥有泛型方法，预期所在的类是否是泛型无关。
- 无论何时，应该尽量用泛型方法，即如果使用泛型方法可以取代将整个类泛型化，那应该只使用泛型方法。
- 对于一个static的方法而言，无法访问泛型类的类型参数，所以，如果static方法需要使用泛型的能力，必须使其成为泛型翻翻。

- 要定义泛型方法，只需将泛型参数列表置于返回值之前：
```
public <T> void f(T x){
    System,out.println(x,getClass().getName());
}
```

- 当使用泛型类是，必须在创建对象时指定类型参数的值，而是用类型方法时，通常不必知名参数类型。
因为编译器会帮我们找出具体类型，这称为类型参数推断（type argument inference）。

- 若调用f()时传入基本类型，自动打包机制会介入其中，将基本类型的值包装为对应的对象。

- 编译器无法从泛型参数列表中的一个参数推断出另一个参数。在泛型方法中，类型参数推断可以为我们简化一部分工作。

```
// 使用泛型参数时，需要重复指定类型。
Map<Person,List<? extends  Pet>> petPeople = new HashMap<Person, List<? extends Pet>>;

// 类型参数推断避免了重复的泛型参数列表。
 Map<String,List<String>> sls = New.map();
  Map<Person,List<? extends  Pet>> petPeople = New.map();
```
- 类型推断只对赋值操作有效。若将一个泛型方法调用的结果作为参数，传递给另一个方法，这时编译器并不会执行类型推断。
此时，编译器认为：调用泛型方法后，其返回值被赋给一个Object类型的变量。
```
public class A {
    static void f(Map<Person,List<? extends  Pet>> petPeople ){}
    public static void main(String[] args){
        //f(New.map()) //Does not compile.
    }
}

```
显示类型说明

- 泛型方法中，可以显示指明类型，但很少用。
- 要现实的指明类型，必须在点操作符与方法名之间插入尖括号，然后把类型至于尖括号内。
- 若是要定义该方法的类的内部，必须在点操作符之前使用this关键字。
- 若是static方法，必须在点操作符之前加上类名。
```
public class B {
    static void f(Map<Person,List<? extends  Pet>> petPeople ){}
    public static void main(String[] args){
        f(New.<Person,List<Pet>>map())
    }
}

```

- 泛型方法与可变参数列表可以很好的共存。

- 在泛型代码内部，无法获得任何有关泛型参数类型的信息。
Java 泛型是使用擦除实现的，意味着使用泛型时，任何具体的类型信息都被擦除了，唯一能知道的是在使用一个对象。
因此List<String>和List<Integer>在运行时实际上是相同的类型，都被擦除成它们的“原生”类型List。

- 不能将一个设计Apple的泛型赋值给一个涉及Fruit的泛型。Apple的List在类型上不等价于Fruit的List，即使Apple是一种Fruit的类型。


- 任何基本类型都不能作为类型参数。解决方案：使用基本类型的包装器类以及Java SE5的自动包装机制，但自动包装机制不能应用于数组。
- 实现参数化接口：一个类不能同时实现一个泛型接口的两种变体。由于擦除的原因，这两个变体会变成相同的接口。
- 转型和警告：使用带有泛型类型参数的转型或instanceof不会有任何效果。可以在容器内部将各个值存储为Object，并在获取这些值时，再将它们转型回为T。
