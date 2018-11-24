/**
 * 知识点：this关键字、构造函数
 *
 * this的用途：区分同名变量，构造函数间调用；
 * this是本类对象的引用；
 * this代表它所在函数所属对象的引用；
 * 变量出现同名的情况时一定要加this，未出现同名情况时this可省略；
 * this的用法：当定义类中功能（函数）时，该函数内部要用到调用该类中的对象时，用this来表示这个对象；
 * 但凡本类功能内部使用到了本类对象时，都用this表示。
 *
 * this语句只能定义在构造函数的第一行，因为初始化要先执行
 *
 * 一般函数不能直接调用构造函数
 */
public class Begin9 {  //此语句为定义类
    public static void main(String[] args){
        PersonDemo3 p1 = new PersonDemo3("Lily",20);
        p1.speak();
        PersonDemo3 p2 = new PersonDemo3("Lihua",20);
        p2.speak();
        boolean b = p1.compare(p2);  //在PersonDemo3中定义了一个比较年龄的方法compare
        System.out.println(b);
    }
}
class PersonDemo3{

    private String name;
    private int age;
    //构造函数，是一种特殊的函数。其主要功能是用来在创建对象时初始化对象，即为对象成员变量赋初始值
    PersonDemo3(String name){

        this.name = name;
    }
    PersonDemo3(String name,int age){
        this(name); /** 如果没有上面的Begin9（String name）这个构造函数的话此处只能用this.name */
        //this.name = name;//this.name 中的name为成员变量而非局部，等号后的name为局部（参数中）
        /* this.name == p.name == this(name) */
        this.age = age;
    }

    public void speak(){
        System.out.println("name = "+this.name +"...age = " + age);
        show();  // == this.show();
    }
    public void show(){

        System.out.println(this.name);
    }
    public boolean compare(PersonDemo3 p){

        return this.age == p.age;
    }

}
