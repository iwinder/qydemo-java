package Others.base.Generics.generator;


/**
 * 生成Fibonacci数列
 *
 * 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584
 *
 * Fibonacci类中使用的都是int类型,但其类型参数是Integer。
 * ES5具备自动打包和自动拆包的功能，可以很方便地在基本类型和其相应的包装器类型之间进行转换。
 *
 */
public class Fibonacci implements Generator<Integer> {
    private int count = 0;
    public Integer next() {
        return fib(count++);
    }

    private int fib(int n){
        if ( n<2 ){
            return 1;
        }
        return fib(n-2) + fib(n-1);
    }

    public static void main(String[] args) {
        Fibonacci gen = new Fibonacci();
        for (int i = 0; i<18;i++){
            System.out.print(gen.next() + " ");
        }
    }
}
