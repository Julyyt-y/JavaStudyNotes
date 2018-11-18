/**
 * 知识点：内部类（内置类、嵌套类）
 *
 * 内部类的访问规则：
 *  1.内部类可以直接访问外部类中的成员
 *    之所以可以直接访问外部类中的成员，是因为内部类中持有了一个外部类的引用，格式：外部类名.this
 *  2.外部类要访问内部类，必须建立内部类对象
 *
 * 访问格式：
 *  1.当内部类定义在外部类的成员位置上，而且非私有，可以在其他外部类中可以直接建立内部类对象，
 *    格式：外部类名.内部类名  变量名 = 外部类对象.内部类对象
 *    Outer.Inner inn = new Outer().new Inner();
 *  2.当内部类在成员位置上，就可以被成员修饰符所修饰
 *    比如：private：将内部类在外部类中进行封装
 *    static：内部类就具备static的特性
 *    当内部类被static修饰后，只能直接访问外部类中的static成员，出现了访问局限
 *    在外部其他类中如何直接访问static内部类的非静态成员： new Outer.Inner().function();
 *    在外部其他类中如何直接访问static内部类的静态成员： Outer.Inner.function();
 *
 *    注意：当内部类中定义了静态成员时，该内部类必须是static的
 *          当外部类中的静态方法访问内部类时，内部类也必须是static的
 *
 * 内部类成员可以直接访问外部类的私有数据，但此私有数据必须是静态的，
 * 外部类不能访问内部类的实现细节（如内部类的成员变量），
 * 内部类只有在外部类中才有意义（如Inner只有在Outer中才有意义），
 * 内部类只有外部类能访问（如Inner只能被Outer访问，而且还不能访问Inner的实现细节）
 *
 * 内部类可以被private私有修饰，外部类不能
 */
class Outer{  //外部类
    private static int num = 3;

    static class Inner{  //内部类
        int num = 4;
        static void function(){
            int num = 6;
            System.out.println("Inner:" + Outer.num);
//            System.out.println("this:" + this.num);
//            System.out.println("out.this"+Outer.this.num);
        }
    }

//    void method(){
//        System.out.println(num);
//        Inner in = new Inner();
//        in.function();
//    }

    static class Inner2{  //当外部类中的静态方法访问内部类时，内部类也必须是static的
        void show(){

            System.out.println("inner2 show");
        }
    }

    public static void method(){
        Inner.function();
        new Inner2().show();  //当外部类中的静态方法访问内部类时，内部类也必须是static的
    }
}
public class Begin22 {  //外部其他类
    public static void main(String[] args){
        Outer.Inner.function();  //在外部其他类中如何直接访问static内部类的非静态成员
        new Outer.Inner().function();  // 在外部其他类中如何直接访问static内部类的静态成员
        Outer out = new Outer();
        out.method();

        //直接访问内部类中的成员
//        Outer.Inner inn = new Outer().new Inner();
//        inn.function();

        Outer11.Inner11 inn11 = new Outer11(). new Inner11();
        inn11.function11();
        Outer11.Inner22 inn22 = new Outer11(). new Inner22();
        inn22.show();

    }
}


class Outer11{
    int num = 11;
    class Inner11{
        int num = 22;
        void function11(){
            int num = 33;
            System.out.println("Inner11:" + num);
        }

    }

    class Inner22{
        void show(){
            System.out.println("inner22 show");
        }
    }
}