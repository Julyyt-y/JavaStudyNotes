/**
 *  知识点：继承（关键字extends）
 *  （对象与对象之间，类与类之间，事物与事物之间）
 *  其他关系：组合关系（手和身体），聚集关系（A里面有B），聚合关系（球队和球员）
 *
 *  继承：
 *  1.提高了代码的复用性
 *  2.让类与类之间产生了关系，有了这种关系，才有了多态的特性
 *
 *  注意：
 *  千万不要为了获取其他类的功能，简化代码而继承；
 *  必须是类与类之间有所属关系才可以继承
 *  判断所属关系：子类具备父类中所有的功能
 *
 * Java语言只支持单继承，不支持多继承（extends A，B）
 * 因为多继承容易带来安全隐患：当多个父类中定义了相同功能，但功能内容不同时，子类对象不确定要运行哪一个,
 * 但是Java保留了这种机制，并用另一种体现形式来完成表现——多实现
 *
 * Java支持多层基础，即：C 继承 B ，B 继承 A ，也就是一个继承体系
 * 想要使用体系，要先查阅体系父类的描述，因为父类中定义的是该体系中的共性功能
 * 通过了解共性功能，就可以知道该体系的基本功能
 * 在具体调用时，要创建最子类的对象，因为：
 *  1.有可能父类不能创建对象（抽象类）
 *  2.创建子类对象可以使用更多的功能，包括基本的和特有的
 *
 *  查阅父类功能，创建子类对象使用功能
 *
 * 父类中有一个对象是私有（private）的时，此对象不能被子类继承，但编译可以通过
 * 但父类中有对象用public修饰，而子类此对象中没有用public修饰时，编译不通过
 */
class Person1 {  //父类
    String name;
    int age;  //name和age都是student和worker的共性

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
class Student extends Person1{  //子类1
//    String name;
//    int age;
    //父类中没有study方法，study方法是子类Student中特有的
    void study(){

        System.out.println("good study");
    }
}
class worker extends Person1{  //子类2
//    String name;
//    int age;
    void work(){

        System.out.println("good work");
    }
}
public class Begin13 {
    public static void main(String[] args){
        Student a = new Student();
        a.name = "lily";
        a.age = 10;
        System.out.println(a.name);
        System.out.println(a.age);
        a.study();
        worker b = new worker();
        b.name = "Tom";
        b.age = 12;
        System.out.println(b.name);
        System.out.println(b.age);
        b.work();
    }
}
