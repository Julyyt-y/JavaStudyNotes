/**
 * 知识点：多态
 * 可以理解为事物存在的多种体现形态
 * 动物 x = new 猫
 * 多态的实现方式：继承、接口（重写）
 *
 * 1.多态的体现：
 *   父类的引用指向了自己的子类
 *   父类的引用也可以接收自己的子对象
 * 2.多态的前提：
 *   类与类之间有关系，要么继承，要么实现（接口）
 *   通常还有一个前提：存在覆盖
 * 3.多态的好处：
 *   大大的提高了程序的扩展性
 * 4.多态的弊端：
 *   虽然多态提高了扩展性，但只能使用父类的引用访问父类中的成员
 * 5.多态的应用：
 *   类型提升，把猫提升为了动物，称为向上转型；
 *   强制转换，强制将父类的引用转换为子类的类型，称为向下转型；
 *   不能将父类的类型转为子类类型
 *   当父类的引用指向了自己的子类对象时，该引用可以被提升，也可以被强制转换
 *   多态自始至终都是子类对象在做着变化
 * 6.多态使用的注意事项：
 *
 * 在多态中成员函数的特点：
 *   在编译时期：参阅引用型变量所属的类中是否有调用的方法，如果有，编译通过，如果没有，编译失败
 *   在运行时期：参阅对象所属的类中是否有调用的方法
 *   简单总结就是：成员函数在多态调用时，编译看左边，运行看右边
 *
 * 在多态中成员变量的特点：
 *   无论编译和运行，都参考左边（引用型变量所属的类）
 *
 * 在多态中，静态成员函数的特点：无论编译和运行，都参考左边
 */

abstract class Animal{
    abstract void eat();
}

class Cat extends Animal{
    public void eat(){

        System.out.println("eat fish");
    }
    public void catchMouse(){

        System.out.println("catch mouse");
    }
}

class Dog extends Animal{
    public void eat(){

        System.out.println("eat bone");
    }
    public void kanJia(){

        System.out.println("kan jia");
    }
}

class Pig extends Animal{
    public void eat(){

        System.out.println("eat siliao");
    }
    public void sleep(){

        System.out.println("pig sleep");
    }
}
public class Begin18 {
    public static void main(String[] args){
        Cat c1 = new Cat();
        function(c1);
        c1.catchMouse();
        Cat c2 = new Cat();
        function(c2);
        Dog d = new Dog();
        d.eat();
        d.kanJia();

        Animal c3 = new Cat();
        function(c3);  //类型提升，把猫提升为了动物，称为向上转型
        Cat c = (Cat)c3;
        c.catchMouse();  //这两句为强制转换，强制将父类的引用转换为子类的类型，称为向下转型

        /**编译时类型和运行时类型不一致，多态发生*/
        Animal d2 = new Dog();
        Animal p1 = new Pig();
        //不能将父类的类型转为子类类型
        //当父类的引用指向了自己的子类对象时，该引用可以被提升，也可以被强制转换
        //多态自始至终都是子类对象在做着变化

        function(new Dog());
    }

    public static void function(Animal a){
        a.eat();
        if (a instanceof Cat){  //a是不是Cat做的事
            Cat c = (Cat)a;  //向下转型
            c.catchMouse();
        }
        if (a instanceof Dog){
            Dog d = (Dog)a;
            d.kanJia();
        }
        if (a instanceof Pig){
            Pig p = (Pig)a;
            p.sleep();
        }
    }
}
