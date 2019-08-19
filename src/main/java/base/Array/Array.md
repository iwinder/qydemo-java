## 数组
在Java语言中，数组是对象（An object is a class instance or an array.），而且是动态创建的。

数组超类是Objcet，可以在数组上调用Object类的所有方法。

每个数组都有一个关联的Class对象，与具有相同组成类型的所有其他数组共享([§10.8](https://docs.oracle.com/javase/specs/jls/se8/html/jls-10.html#jls-10.8))。

虽然数组类型不是一个class，但每个数组的Class对象的行为如下：
- 每个数组类型的直接超类都是Object。
- 每个数组类型都实现了Cloneable和java.io.Serializable接口。

## 数组类型的超类型关系

以下规则定义了数组类型之间的直接超类型关系（[4.10.3](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.10.3)）：

- 如果 S 和 T 都是引用类型，当S ><sub>1</sub> T时，S[] ><sub>1</sub> T[] 
- Object ><sub>1</sub> Object[]
- Cloneable ><sub>1</sub> Object[]
- java.io.Serializable ><sub>1</sub> Object[]
- 如果 P 是一种原始数据类型，则：
    - Object ><sub>1</sub> P[]
    - Cloneable ><sub>1</sub> P[]
    - java.io.Serializable ><sub>1</sub> P[]

**数组类型的超类型关系与超类关系不同**。根据上面（即4.10.3），Integer []的直接超类型是Number []。
但根据Integer[]是一个Class对象([§10.8](https://docs.oracle.com/javase/specs/jls/se8/html/jls-10.html#jls-10.8))，Integer []的直接超类是Object。

### 数组类型成员

以下是数组类型的所有成员：
public final字段length，包含数组的元素个数。长度可以是正数或零。
public 方法 clone，它会覆盖Object类中的同名方法，并且抛出任何未检查的异常。数组类型T []的clone()方法的返回类型是T []。
多维数组的clone很浅，也就是说它只创建一个新数组。子阵列是共享的。

所有成员都继承自Object类;唯一没有继承的Object方法是它的clone方法。

### Java中的length和length()
问：为什么数组有length属性，而字符串没有？或者，为什么字符串有length()方法，而数组没有？

答：

一旦数组被创建，他的长度就是固定的了。数组的长度可以作为final实例变量的长度。因此，长度可以被视为一个数组的属性。

String背后的数据结构是一个char数组,所以没有必要来定义一个不必要的属性（因为该属性在char数值中已经提供了）。

[深入分析Java中的length和length()](http://www.hollischuang.com/archives/1261)