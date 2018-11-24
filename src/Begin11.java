/**
 * 静态代码块
 * 格式如下：
 * static{
 *     静态代码块中的执行语句
 * }
 * 特点：随着类的加载而执行，且只执行一次,并优先于主函数执行
 * 作用：用于给类进行初始化
 *
 * 先运行父类中的静态代码块，再运行子类中的静态代码块，再运行main方法（main函数定义在子类中）
 *
 */

public class Begin11 {

    static {
        System.out.println("a");
    }
    {
        System.out.println("f");
    }
}

class StaticCodeDemo extends Begin11{
    static{
        System.out.println("b");
    }

    {
        System.out.println("e");
    }

    public static void main(String[] args){
        new Begin11(); //执行Begin11类中的静态代码块
        new Begin11(); //执行Begin11类中的静态代码块
        System.out.println("Hello Java");
    }

    static {
        System.out.println("c");
    }
}

//输出的顺序：b，c，a，Hello Java。（先执行主函数所在的类中的静态代码块）