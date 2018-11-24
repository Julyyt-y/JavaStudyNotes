/**
 * 知识点：异常--异常类的继承体系
 *  1.子类在覆盖父类时，如果父类的方法抛出异常，那么子类的覆盖方法只能抛出父类的异常或者该异常的子类
 *  2.如果父类方法抛出多个异常，那么子类在覆盖该方法时，只能抛出父类异常的子集
 *  3.如果父类或者接口的方法中没有异常抛出，那么子类在覆盖方法时，也不可以抛出异常，
 *    如果子类方法发生了异常，就必须要进行try处理，绝对不能抛
 */

//异常类的继承1：
class AException extends Exception{

}
class BException extends AException{

}
class CException extends Exception{

}
class Fu2{
    void show() throws AException {

    }
}
class Zi2 extends Fu2{
    //父类已经把异常抛给了A异常（父异常），故子异常只能把异常抛给B异常（A异常的子异常）
    void show() throws BException{

    }
}
class Test2{
    void function(Fu2 f){
        try {
            f.show();
        }
        catch (AException e){

        }
    }
}
public class Begin30 {
    public static void main(String[] args){
        Test2 t = new Test2();
        t.function(new Fu2());

        //异常类的继承2：
        try {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = a / b;
            System.out.println("您输入的两个数相除的结果是：" + c);
        }catch (IndexOutOfBoundsException ie){
            System.out.println("数组越界：运行程序时输入的参数个数不够！");
        }
        catch (NumberFormatException ne){
            System.out.println("数字格式异常：运行时只能接收整数参数！");
        }
        catch (ArithmeticException ae){
            System.out.println("算术异常");
        }
        catch (Exception e){
            System.out.println("异常未知");
        }
    }
}
