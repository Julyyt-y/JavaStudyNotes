/**
 * 知识点：RuntimeException异常子类
 * RuntimeException及其子类如果在函数内抛出了，函数内不用声明；
 * RuntimeException及其子类如果在函数上声明了，调用者可以不用进行处理。
 *
 * 之所以不用在函数内进行声明，是因为不需要让调用者处理，当异常发生，希望程序停止，
 * 因为在运行时，出现了无法继续运算的情况，希望停止程序后，对代码进行修正。
 *
 * 自定义异常时，如果该异常发生，无法再继续进行运算，就让自定义异常继承RuntimeException
 *
 * 对于异常，分两种：
 *  1.编译时被检测的异常
 *  2.编译时不被检测的异常（运行时异常，RuntimeException及其子类）
 */
class Demo4{
    //ArithmeticException是RuntimeException的子类
    int div(int a,int b)throws ArithmeticException{
//        if (b == 0){
//            throw new ArithmeticException("被0除啦");
        return a/b;
    }
}

public class Begin28 {
    public static void main(String[] args) {
        Demo4 d = new Demo4();
        int x = d.div(4,0);
        System.out.println("x="+x);
        System.out.println("over");
    }
}
