/**
 * 知识点：异常处理--自定义异常
 * 因为项目中会出现特有的问题，而这些问题并未被Java所描述并封装对象，
 * 所有对这些特有的问题可以按照Java的对问题封装的思想，将特有的问题，进行自定义的异常封装
 *
 * 当在函数内部出现了throw抛出异常对象，那么就必须要给出对应的处理动作
 * 要么在内部try catch处理，要么在函数上
 *
 * 发现打印的结果中只有异常的名称，却并没有异常的信息
 * 因为自定义的异常并未定义信息
 *
 * 如何定义异常信息：
 * 因为父类中已经把异常信息的操作都完成了，
 * 所以子类只要在构造时，将异常信息通过super语句传递给父类，
 * 那么就可以直接通过getMessage方法获取自定义的异常信息
 *
 * 自定义异常：
 * 必须是自定义类继承Exception；
 * 如果希望写一个检查性异常类，则需要继承 Exception 类，如果你想写一个运行时异常类，那么需要继承 RuntimeException 类。
 * 继承Exception的原因：
 * 异常体系有一个特点：异常类和异常体系都需要被抛出，他们都具备可抛性，
 * 这个可抛性是Throwable这个体系中独有的特点，
 * 只有这个体系中的类和对象才可以被throws和throw操作
 *
 * throws和throw的区别：
 * throws使用在函数上；throw使用在函数内
 * throws后面跟的是异常类，可以跟多个（多个类名之间用逗号隔开）；throw后面跟的是异常对象（中间加new）
 */

//需求：在本程序中，对于除数是-1，也视为是错误的、无法进行的运算
//      那么就需要对这个问题进行自定义的描述

//自定义一个异常类
//只有FuShuException继承了Exception或Error，FuShuException才具有可抛性
class FuShuException extends Exception{

    private String msg;
    //构造函数
    FuShuException(String msg){
        this.msg = msg;
    }
    //直接通过getMessage方法获取自定义的异常信息
    @Override
    public String getMessage(){
        return msg;
    }

    private int value;
    FuShuException(){

        super();
    }
    FuShuException(String msg,int value){
        super(msg);
        this.value = value;
    }
    public int getValue(){

        return value;
    }
}

class Demo3{
    int div(int a,int b) throws FuShuException{
        if (b < 0){
            //已经自定义了异常信息，所以"出现了除数是复数的情况"不能打印出来
            throw new FuShuException("出现了除数是复数的情况",b);
            //给FuShuException(String msg){}传入"出现了除数是复数的情况"的异常信息
        }
        return a/b;
    }
}
public class Begin27 {
    public static void main(String[] args){
        Demo3 d = new Demo3();
        try{
            int x = d.div(4,-1);
            System.out.println("x = "+x);
        }
        catch (FuShuException e){
            System.out.println(e.toString());  //打印异常的字符串表示
            System.out.println("除数出现负数了");
            System.out.println("错误的复数是"+e.getValue());
        }
        System.out.println("over");

        CheckingAccount c = new CheckingAccount(101);
        System.out.println("存500元...");
        c.deposit(500);
        try {
            System.out.println("取100元。。。");
            c.withdraw(100);
            System.out.println("取600元。。。");
            c.withdraw(600);
        } catch (InsufficientFundsException e){
            System.out.println("你的账户差" + e.getAmount());
            e.printStackTrace();
        }
    }
}

//自定义异常类，继承Exception类
class InsufficientFundsException extends Exception{

    //此处的amount用来储存当出现异常（取出钱多于余额时）所缺乏的钱
    private double amount;
    InsufficientFundsException(double amount){
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
//此类模拟银行账户
class CheckingAccount{
    private double balance; //账户余额
    private int number; //卡号
    CheckingAccount(int number){
        this.number = number;
    }
    //方法：存钱
    void deposit(double amount){
        balance +=amount;
    }
    //方法：取钱
    void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= balance){
            balance -= amount;
        }
        else {
            double needs = amount - balance;
            throw new InsufficientFundsException(needs);
        }
    }
    //方法：返回余额
    double getBalance(){
        return balance;
    }
    //方法：返回卡号
    int getNumber(){
        return number;
    }
}