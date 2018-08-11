## 队列
除并发应用，Queue在Java SE5中仅有两个实现 LinkedList和PriorityQueue,差异在于排序行为，而不是性能。

除了优先级队列，Queue将准确地按照元素被置于Queue中的顺序产生它们。



## Map

映射表（也称为关联数组）的基本思想：它维护的是键-值（对）关联，因此可以用键来查找值。

标准的java库包含了Map的几种基本实现：HashMap,TreeMap,LinkedHashMap,WeakHashMap,ConcurrentHashMap,IdentityHashMap。

它们都有相同的基本接口Map，但是行为特性各不相同，这表现在效率、键值对的保存及呈现次序、对象的保存周期、映射表如何在多线程程序中工作和判定“键”等价的策略等方面。

### 性能
性能是映射表中的一个重要问题。当get()中使用线性搜索时，执行速度会相当慢，这正是HashMap提高速度的地方。

HashMap使用了特殊的值，称作散列码，来取代对键的缓慢搜索。

散列码是“相对唯一”的、用以代表对象的int值，它通过将该对象的某些信息进行转换而生成。

hashCode()是根类Objcet中的方法，因此所有Java对象都能 产生散列码，

HashMap就是使用对象的hashCode()进行快速查询的，此方法能够显著提高性能。

使用数组代替溢出捅，有两个好处：
- 可以针对磁盘存储方式做优化。
- 在创建和回收单独的记录时，能节约很多时间。

下面是基本Map实现的对照表，如果没有其他的限制，应该默认选择HashMap,因为它对速度做了优化，其他实现强调了其他的特性，因此都不如HashMap快。

| Map实现类型 | 具体特性 |
| --- |  --- |
| HashMap | Map基于散列表的实现（它取代了Hashtable）。插入和查询“键值对”的开销是固定的。可以通过构造器设置容量和负载因子，以调整容器的特性。|
| LinkedHashMap | 类似HashMap,但迭代遍历它时，取得“键值对”的顺序是其插入次序，或者是最近最少使用（LRU）的次序。|
| TreeMap | 基于红黑树的实现。查看“键”或者“键值对”时，它们会被排序（次序由Comparable或者Comparator决定）。TreeMap的特点在于：所得到的结果是经过排序的。TreeMap是唯一的带有subMap()方法的Map，它可以返回一个子树。|
| WeakHashMap | 弱键（weak key）映射，允许释放映射所指向的对象；这是为解决某类特殊问题而设计的。如果映射之外没有引用指向某个“键”，则此键可以被垃圾收集器回收。 |
| ConcurrentHashMap | 一种线程安全的Map, 它不涉及同步加锁。|
| IdentityHashMap | 使用== 代替equals()对“键”进行比较的散列映射。专为解决特殊问题而设计。 |

散列是映射中存储元素时最常用的方式。

对Map中使用的键的要求与对Set中的元素要求一样：

- 任何键必须具有一个equals()方法。
- 如果键被用于散列Map,那么它必须还具有恰当的hashCode()方法。
- 如果键被用于TreeMap,那么它必须实现Comparable。

### SortedMap
TreeMap 是其现阶段的唯一实现。

## 散列与散列码

Object的hashCode()方法生成散列码，默认是使用对象的地址计算散列码。

默认的Objcet.equals()只是比较对象的地址。

若要使用自己的类作为HashMap的键，必须同时重载hashCode()和equals()。

### 正确的equals()方法必须满足的5个条件
- 1.自反性。对任意x，x.equals(x)一定返回true.
- 2.对称性。对任意x和y，如果y.equals(x)返回true，则x.equals(y)也返回true.
- 3.传递性。对任意x、y、z，如果有x.equlas(y)返回true，y.equals(z)返回true，则x.equals(x)一定返回true。
- 4.一致性。对任意x和y，如果对象中用于等价比较的信息没有改变，那么无论调用多少次x.equals(y)，返回的结果应该保持一致，一直是true或false。
- 5.对任何不是null的x，x.equals(null)一定返回null。