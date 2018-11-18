/**
 * 抽象类练习
 * 需求：在开发系统时需要对员工进行建模，员工包含三个属性：
 *      姓名、工号、工资。经理也算是员工。除了含有员工的属性外，
 *      还有一个奖金属性。
 *      请使用继承的思想设计出员工类和经理类，要求类中提供必要的方法进行属性访问
 *
 */
abstract class Employee{
    private String name;
    private String id;
    private double pay;

    //构造函数，用于初始化对象
    Employee (String name,String id,double pay){
        this.name = name;
        this.id = id;
        this.pay = pay;
    }
    public abstract void work();
}

class Manager extends Employee{
    private int bonus;
    Manager(String name,String id,double pay,int bonus){
        super(name,id,pay);
        this.bonus = bonus;
    }
    public void work(){

        System.out.println("manager work");
    }
}

class Pro extends Employee{
    Pro(String name,String id,double pay){

        super(name,id,pay);

        System.out.println(name);
        System.out.println(id);
        System.out.println(pay);
    }
    public void work(){

        System.out.println("pro work");
    }

}
public class Begin15 {
    public static void main(String[] args){
        Pro pro = new Pro("Tom","111",1000); //给por设置name，id，pay
        pro.work();
        System.out.println("work");
    }
}
