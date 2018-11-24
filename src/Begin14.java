/**
 * 知识点：抽象类abstract
 * 当多个类中出现相同功能，但功能主体不同时，可一进行向上抽取，这时，只抽取功能定义，而不抽取功能主体
 *
 * 抽象类的特点：
 *  1.抽象方法一定定义在抽象类中
 *  2.抽象方法和抽象类都必须被abstract关键字修饰
 *  3.抽象类不可以用new关键字来创建对象
 *  4.抽象类中的抽象方法方法要被使用，必须由子类复写了所有抽象方法后，建立子类对象调用
 *    如果子类只覆盖了部分抽象方法，那么该子类还是一个抽象类
 *  5.抽象类中也可以定义一般类，也可以不定义抽象方法
 */
//定义抽象类
abstract class Students{
    //抽象类中的抽象方法
    abstract void study();
    void sleep(){

        System.out.println("sleep");
    }
}
class BaseStudents extends Students{
    //BaseStudents子类要使用Students中的study()抽象方法，就要复写该方法
    void study(){

        System.out.println("base study");
    }

    //父类中的sleep不是抽象方法，故此处sleep方法其实是覆盖了父类中的sleep方法
    void sleep() {
        System.out.println("Sleep");
    }
}
class AdvStudents extends Students{
    void study(){

        System.out.println("adv study");
    }
}

public class Begin14 {
    public static void main(String[] args){
        //new Students(); 抽象类不可以用new关键字来创建对象
        new BaseStudents().study();
        //如果子类中没有覆盖父类中sleep方法，则此处打印sleep（继承）
        new BaseStudents().sleep();
        new AdvStudents().study();
    }
}
