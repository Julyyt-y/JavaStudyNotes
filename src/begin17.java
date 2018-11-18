/**
 * 接口举例体现
 * 接口：
 *      Java中一个抽象类型，抽象方法的合集，通常以interface来声明，
 *      一个接口可以有多个方法，这些方法都必须是抽象方法，
 *      一个类通过继承接口的方式，从而来继承接口的抽象方法；
 *      接口跟类不一样：类描述对象的属性和方法，接口则包含类要实现的方法，
 *      除非实现接口的类是抽象类，否则该类要定义接口中的所有方法；
 *      接口无法被实例化，但是可以被实现，一个实现接口的类，必须实现接口内所描述的所有方法，否则就必须声明为抽象类，
 *      接口支持多继承,
 *      一个接口能继承另一个接口
 *      接口时不可以创建对象的，因为有抽象方法，
 *      需要被子类实现，子类对接口中的抽象方法全部覆盖后，子类才可以实例化，
 *      否则子类时是一个抽象类
 *
 * 重写接口中声明的方法时，需要注意：
 * 类在实现接口的方法时，不能抛出强制性异常，只能在接口中，或者继承接口的抽象类中抛出该强制性异常；
 * 类在重写方法时要保持一致的方法名，并且应该保持相同或者相兼容的返回值类型，
 * 如果实现接口的类是抽象类，那么就没必要实现该接口的方法
 *
 * 接口中的成员修饰符是固定的：
 *   成员常量：public static final
 *   成员函数：public abstract
 *
 *
 */

//声明抽象类
abstract class Studentss{
    abstract void study();
    void sleep(){

        System.out.println("sleep");
    }
}

//声明接口（接口是隐式抽象的，当声明一个接口的时候，不必使用abstract关键字）
interface Smoking{
    //接口中每一个方法也是隐式抽象的，声明时同样不需要abstract关键字
    void smoke();
    void learn();
}

//一个接口能继承另一个接口
interface Sleep extends Smoking{
    void sleep();
}

//一个类实现了某一接口则会实现接口中的所有方法
class ZhangSan extends Studentss implements Sleep{
    void study(){}
    public void smoke(){
        System.out.println("Not Smoking");
    }

    @Override
    public void learn() {

        System.out.println("Learning");
    }

    @Override
    public void sleep() {
        System.out.println("SSSSleep");
    }
}

public class begin17 {
    public static void main(String[] args){
        ZhangSan zhangSan = new ZhangSan();
        zhangSan.learn();
        zhangSan.smoke();
        zhangSan.study();
        zhangSan.sleep();
    }
}
