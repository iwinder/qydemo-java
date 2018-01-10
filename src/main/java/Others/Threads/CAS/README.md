## 什么是CAS？
全称：Compare And Swap，翻译为比较并替换。

CAS机制当中使用了3个基本操作数：
- 内存地址V
- 旧的预期值A
- 要修改的新值B

**当且仅当变量的预期值A和内存地址V当中的实际值相同时，才会将内存地址V对应的值修改为B** 。

### 例：

1.在内存地址V中，存着值为10的变量。

2.此时线程1想要把变量的值加1。对线程1来说，旧的预期值A=10，要修改的新值B=11。

3.在线程1提交更新之前，另一个线程2先行一步，吧内存地址V中的变量先更成了11。

4.线程1提交更新，首先**A和地址V中的实际值比较**，发现A不等于V的实际值，提交失败。

5.线程1重新获取内存地址的当前值，并重新计算想要修改的新值B。此时对线程1来说，A=11,B=12。这个重新尝试的过程被称为**自旋**。

6.这次比较幸运，没有其他线程改变V中的值，线程1进行**Compare**，发现A与V中的值相等。

7.线程1进行**SWAP**,把地址V中的值替换为B，即12。

### 从思想上来说：
- Synchronized属于**悲观锁**，悲观地认为程序中的并发情况严重，所以严防死守。
- CAS属于**乐观锁**，乐观地认为程序中的并发情况不那么严重，所以让线程不断去尝试更新。

### 使用场景
- Synchronized同步锁更适合在**并发量非常高**的情况下。
- CAS机制用于如Atomic系列类，Lock系列类的底层实现；Java1.6以上版本，Synchronized转变为重量级锁之前也会采用。

### CAS缺点

1.CPU开销较大

在并发量比较高的情况下，如果许多线程反复尝试更新某个变量，却一直不成功，循环往复，会给CPU带来很大的压力。

2.不能保证代码块的原子性

CAS机制所保证的只是一个变量的原子性操作，而不能保证整个代码块的原子性。

比如需要保证3个变量共同进行原子性的更新，就不得不使用Synchronized了。

3.ABA问题

当一个值从A更新成B，又更新会A，普通CAS机制会误判通过检测。
#### 例：
假设内存中有个值为100的变量，存储在地址V中。

此时有3个线程想使用CAS的方式更新这个值，每个线程执行的时间有略微的偏差。线程1和线程2已经获得当前值，线程3还未获得当前值。即：

- 线程1：获取当前值100，期望更新为50；
- 线程2：获取当前值100，期望更新为50；
- 线程3：期望更新为100

之后，线程1先执行成功，把100更新为50，线程2因为某些原因被阻塞，未做更新；线程3在1更新后，获得当前值50.

- 线程1：获取当前值100，成功更新为50；
- 线程2：获取当前值100，期望更新为50，BLOCK
- 线程3：获取当前值50，期望更新为100

此时，线程2仍处于阻塞状态，线程3继续执行，把50更新为100。

- 线程1：获取当前值100，成功更新为50，已返回；
- 线程2：获取当前值100，期望更新为50，BLOCK
- 线程3：获取当前值50，成功更新为100

最后，线程2终于恢复运行状态，由于阻塞之前已经获得“当前值”100，并经过compare检测，内存地址V的实际值也是100，所以成功把变量100更新为50.

- 线程1：获取当前值100，成功更新为50，已返回；
- 线程2：获取当前值100，成功更新为50
- 线程3：获取当前值50，成功更新为100，已返回；

这个过程中，线程2获取到的变量100是一个旧值，尽管和当前的实际值相同，但内存地址V中的变量已经经历了100->50->100（即A->B->A)的改变。

#### 解决方案

利用版本号比较可以有效解决ABA问题。即，在Compare阶段不仅要比较期望值A和地址V中的实际值，还要比较变量的版本号是否一致。

在Java当中，Java并发包中提供了一个带有标记的原子引用类AtomicStampedReference，其可以通过控制变量值的版本来保证CAS的正确性。

### Java当中CAS的底层实现
下面以AtomicInteger的实现为例分析。

```
public class AtomicInteger extends Number implements java.io.Serializable {
    private static final long serialVersionUID = 6214790243416807050L;

    // setup to use Unsafe.compareAndSwapInt for updates
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    private volatile int value;
    
    .....
    
    /**
     * Gets the current value.
     *
     * @return the current value
     */
    public final int get() {
        return value;
    }
    //省略部分代码
    ......
    
   
    
}
```

- 1.Unsafe，是CAS的核心类，由于Java方法无法直接访问底层系统，需要通过本地（native）方法来访问，Unsafe相当于一个后门，基于该类可以直接操作特定内存的数据。unsafe为我们提供了硬件级别的原子操作。
- 2.变量valueOffset，表示该变量值在内存中的偏移地址，因为Unsafe就是根据内存偏移地址获取数据的。我们可以简单地把valueOffset理解为value变量的内存地址。
- 3.变量value用volatile修饰，保证了多线程之间的内存可见性。即可当做V中的值

看看AtomicInteger如何实现并发下的累加操作：

```
    public final int incrementAndGet() {
        return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
    }
    // unsafe.getAndAddInt
    public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

        return var5;
    }
    public native int getIntVolatile(Object var1, long var2);
```

线程通过getIntVolatile(var1, var2)拿到value值，compareAndSwapInt比较并替换，若不相等继续执行getIntVolatile，然后再compareAndSwapInt,直到成功。

整个过程中，利用CAS保证了对于value的修改的并发安全，继续深入看看Unsafe类中的compareAndSwapInt方法实现。

```
public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
```
Unsafe类中的compareAndSwapInt，是一个本地方法，该方法的实现位于unsafe.cpp中（此部分及以下大部分来源自占小狼博客）

```
UNSAFE_ENTRY(jboolean, Unsafe_CompareAndSwapInt(JNIEnv *env, jobject unsafe, jobject obj, jlong offset, jint e, jint x))
  UnsafeWrapper("Unsafe_CompareAndSwapInt");
  oop p = JNIHandles::resolve(obj);
  jint* addr = (jint *) index_oop_from_field_offset_long(p, offset);
  return (jint)(Atomic::cmpxchg(x, addr, e)) == e;
UNSAFE_END
```
先想办法拿到变量value在内存中的地址。

通过Atomic::cmpxchg实现比较替换，其中参数x是即将更新的值，参数e是原内存的值。

如果是Linux的x86，Atomic::cmpxchg方法的实现如下：

```
inline jint Atomic::cmpxchg (jint exchange_value, volatile jint* dest, jint compare_value) {
  int mp = os::is_MP();
  __asm__ volatile (LOCK_IF_MP(%4) "cmpxchgl %1,(%3)"
                    : "=a" (exchange_value)
                    : "r" (exchange_value), "a" (compare_value), "r" (dest), "r" (mp)
                    : "cc", "memory");
  return exchange_value;
}
```
- ```__asm__``` 表示汇编的开始
- ```volatile```  表示禁止编译器优化
- ```LOCK_IF_MP``` 是个内联函数
```
#define LOCK_IF_MP(mp) "cmp $0, " #mp "; je 1f; lock; 1: "
```
Window的x86实现如下：

```
inline jint Atomic::cmpxchg (jint exchange_value, volatile jint* dest, jint compare_value) {
    int mp = os::isMP(); //判断是否是多处理器
    _asm {
        mov edx, dest
        mov ecx, exchange_value
        mov eax, compare_value
        LOCK_IF_MP(mp)
        cmpxchg dword ptr [edx], ecx
    }
}

// Adding a lock prefix to an instruction on MP machine
// VC++ doesn't like the lock prefix to be on a single line
// so we can't insert a label after the lock prefix.
// By emitting a lock prefix, we can define a label after it.
#define LOCK_IF_MP(mp) __asm cmp mp, 0  \
                       __asm je L0      \
                       __asm _emit 0xF0 \
                       __asm L0:
```

LOCK_IF_MP根据当前系统是否为多核处理器决定是否为cmpxchg指令添加lock前缀。

- 1.如果是多处理器，为cmpxchg指令添加lock前缀。
- 2.反之，就省略lock前缀。（单处理器会不需要lock前缀提供的内存屏障效果）

#### intel手册对lock前缀的说明如下：

- 1.确保后续指令执行的原子性。

在Pentium及之前的处理器中，带有lock前缀的指令在执行期间会锁住总线，使得其它处理器暂时无法通过总线访问内存，很显然，这个开销很大。在新的处理器中，Intel使用缓存锁定来保证指令执行的原子性，缓存锁定将大大降低lock前缀指令的执行开销。

- 2.禁止该指令与前面和后面的读写指令重排序。
- 3.把写缓冲区的所有数据刷新到内存中。

上面的第2点和第3点所具有的内存屏障效果，保证了CAS同时具有volatile读和volatile写的内存语义。

### 扩展知识-关于CPU的锁

关于CPU的锁有如下3种：
#### 1.处理器自动保证基本内存操作的原子性
首先处理器会自动保证基本的内存操作的原子性。

处理器保证从系统内存当中读取或者写入一个字节是原子的，意思是当一个处理器读取一个字节时，其他处理器不能访问这个字节的内存地址。

奔腾6和最新的处理器能自动保证单处理器对同一个缓存行里进行16/32/64位的操作是原子的，但是复杂的内存操作处理器不能自动保证其原子性，比如跨总线宽度，跨多个缓存行，跨页表的访问。

但是处理器提供**总线锁定**和**缓存锁定**两个机制来保证复杂内存操作的原子性。
#### 2.使用总线锁保证原子性
第一个机制是通过总线锁保证原子性。

如果多个处理器同时对共享变量进行读改写（i++就是经典的读改写操作）操作，那么共享变量就会被多个处理器同时进行操作，这样读改写操作就不是原子的，操作完之后共享变量的值会和期望的不一致，举个例子：如果i=1,我们进行两次i++操作，我们期望的结果是3，但是有可能结果是2。

原因是有可能多个处理器同时从各自的缓存中读取变量i，分别进行加一操作，然后分别写入系统内存当中。那么想要保证读改写共享变量的操作是原子的，就必须保证CPU1读改写共享变量的时候，CPU2不能操作缓存了该共享变量内存地址的缓存。

处理器使用总线锁就是来解决这个问题的。所谓总线锁就是使用处理器提供的一个LOCK＃信号，当一个处理器在总线上输出此信号时，其他处理器的请求将被阻塞住,那么该处理器可以独占使用共享内存。
#### 3.使用缓存锁保证原子性
第二个机制是通过缓存锁定保证原子性。

在同一时刻我们只需保证对某个内存地址的操作是原子性即可，但总线锁定把CPU和内存之间通信锁住了，这使得锁定期间，其他处理器不能操作其他内存地址的数据，所以总线锁定的开销比较大，最近的处理器在某些场合下使用缓存锁定代替总线锁定来进行优化。   

频繁使用的内存会缓存在处理器的L1，L2和L3高速缓存里，那么原子操作就可以直接在处理器内部缓存中进行，并不需要声明总线锁，在奔腾6和最近的处理器中可以使用“缓存锁定”的方式来实现复杂的原子性。所谓“缓存锁定”就是如果缓存在处理器缓存行中内存区域在LOCK操作期间被锁定，当它执行锁操作回写内存时，处理器不在总线上声言LOCK＃信号，而是修改内部的内存地址，并允许它的缓存一致性机制来保证操作的原子性，因为缓存一致性机制会阻止同时修改被两个以上处理器缓存的内存区域数据，当其他处理器回写已被锁定的缓存行的数据时会起缓存行无效，在例1中，当CPU1修改缓存行中的i时使用缓存锁定，那么CPU2就不能同时缓存了i的缓存行。

但是有两种情况下处理器不会使用缓存锁定。第一种情况是：当操作的数据不能被缓存在处理器内部，或操作的数据跨多个缓存行（cache line），则处理器会调用总线锁定。第二种情况是：有些处理器不支持缓存锁定。对于Inter486和奔腾处理器,就算锁定的内存区域在处理器的缓存行中也会调用总线锁定。

以上两个机制我们可以通过Inter处理器提供了很多LOCK前缀的指令来实现。比如位测试和修改指令BTS，BTR，BTC，交换指令XADD，CMPXCHG和其他一些操作数和逻辑指令，比如ADD（加），OR（或）等，被这些指令操作的内存区域就会加锁，导致其他处理器不能同时访问它。

## 参考资料
[漫画：什么是CAS机制？（进阶篇）](https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzIxMjE5MTE1Nw==#wechat_redirect)

最开始了解CAS机制来源于看小灰的公众号（chengxuyuanxiaohui），里面一共是两篇文章，这里仅放出一篇地址，有兴趣可以去公众号查看。

[深入浅出CAS](https://www.jianshu.com/p/fb6e91b013cc)
小灰里面对AtomicInteger的分析部分比较简单而且看代码可能略微旧些，于是找到了占小狼的和下面一篇。这主要参考了对AtomicInteger的CAS底层实现分析以及深到汇编的部分。

[JAVA CAS原理深度分析](http://blog.csdn.net/hsuxu/article/details/9467651)
这里也存在一些汇编，由于和占的部分重叠就没再多看，里面有个C++文件地址所在目录，虽然是openjdk7的，但可以参考。主要留存了下里面的备注知识-关于CPU的锁。