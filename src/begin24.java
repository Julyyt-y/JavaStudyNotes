/**
 * 知识点：匿名内部类、重写、super关键字
 *   1.匿名内部类其实就是内部类的简写格式
 *   2.定义匿名内部类的前提：内部类必须是继承一个类或者实现接口
 *   3.匿名内部类的格式： new  父类或者接口() {定义子类的内容}
 *   4.其实匿名内部类就是一个匿名子类对象，而且这个对象有点胖，可以理解为带内容的对象
 *   5.匿名内部类中的定义的方法最好不要超过3个
 *   6.匿名内部类适合于创建那些仅需要使用一次的类
 *
 *
 * super关键字：
 *      当需要在子类中调用父类的被重写方法时，要使用super关键字
 */

//抽象类
class AbsDemo{
    void show() {
        System.out.println("AbsDemo run");
    }
}

class Outer2{
    int x = 3;
    class Inner2 extends AbsDemo{
        void show(){
            System.out.println("method:"+x);
        }
    }


//    public void function(){
//        new AbsDemo(){  //AbsDemo的匿名子类对象
//            void show(){
//                System.out.println("x="+x);
//            }
//    }.show();


//    public void function(){
//        new AbsDemo(){  //AbsDemo的匿名子类对象
//            void show(){
//                System.out.println("x="+x);
//            }
//            void abc(){
//                System.out.println("haha");
//            }
//        }.abc();
//    }


//    public void function(){
//        new AbsDemo(){  //AbsDemo的匿名子类对象
//            void show(){
//                System.out.println("x="+x);
//            }
//            void abc(){
//                System.out.println("haha");
//            }
//        }.show();


    /**
     * JAVA中 @Override 的作用：
     * 如果想重写父类的方法，比如toString()方法的话，在方法前面加上@Override 系统可以帮你检查方法的正确性，
     * @Overridepublic String toString(){...}这是正确的，
     * 一旦写错 写成这样@Overridepublic String tostring(){...}编译器可以检测出这种写法是错误的，这样能保证你的确重写的方确，
     * 而如果不加@Overridepublic String tostring(){...}这样编译器是不会报错的 它只会认为这是你自己新加的一个方法而已
     */
    public void function(){
        AbsDemo d = new AbsDemo() {
            int num = 9;
            @Override
            void show() {   //匿名内部类中的方法
                super.show(); //应用super类的方法
                System.out.println("x=="+x);
                System.out.println("num=="+num);
            }
            void abc(){
                System.out.println("haha");
            }
        };
        d.show();
    }

}

public class begin24 {
    public static void main(String[] args){
        new Outer2().function();
    }
}
