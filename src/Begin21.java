/**
 * 知识点：Object类
 * Object：所有对象的直接或间接父类；
 * 该类中定义的是所有对象都具备的功能；
 * 当一个类没有使用extends关键字为他显示指定父类时，该类默认继承Object父类。
 *
 * Java认为，所有对象都具有比较性，都能比较
 */
class Demo1{
    private int num;
    //构造函数
    Demo1(int num){

        this.num = num;
    }
    //Object类中的方法
    public boolean equals(Object obj){
        if (!(obj instanceof Demo1)) {
            return false;
        }
        Demo1 d = (Demo1)obj;
        return this.num == d.num;
    }

    //Object类中的方法
    @Override
    public String toString() {

        return "demo"+num;
    }
}
class Person2{

}
public class Begin21 {
    public static void main(String[] args){
        Demo1 d1 = new Demo1(4);
        Demo1 d2 = new Demo1(9);
        Demo1 d3 =d1;
        System.out.println(d1.getClass());//getClass()方法返回该对象的运行时类
        System.out.println(d2.toString());//toString()方法返回该对象的字符串表示（Demo1中重写的该方法，所以以重写的该方法为准）
        System.out.println(d2.hashCode());//hashCode()返回该对象的hashCode值
        System.out.println(d1.equals(d3));//等价于 System.out.println(d1 == d3);

        Person2 p1 = new Person2();
        Person2 p2 = new Person2();
        System.out.println(p1.equals(p2)); //equals比较的是地址，等于就是true，不等于就是false
    }
}
