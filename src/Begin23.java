/**
 * 知识点：内部类
 *
 * 内部类定义在局部时：
 *  1.不可以被成员修饰符修饰；
 *  2.可以直接访问外部类中的成员，因为还持有外部类中的引用，
 *    但是不可以访问它所在的局部变量，只能访问被final修饰的局部变量
 */

class Outer1{
    int x = 3;
    void method( final int a){
        final int y = 4;
        class Inner1{
            //内部类定义在局部时不可以被成员修饰符修饰
            void function(){  //局部内部类不能定义静态成员
                System.out.println(Outer1.this.x); //3
                System.out.println(y); //4
                System.out.println(a);//主函数中给
            }
        }
        new Inner1().function();
    }
}
public class Begin23 {
    public static void main(String[] args){
        Outer1 out = new Outer1();
        out.method(7);
        out.method(8);
        new Outer1().method(0);
    }
}
