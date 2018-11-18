/**
 * 知识点：异常--finally
 * finally中存放的是一定要被执行的代码，通常用于关闭资源
 *
 * 关闭资源：System.exit(0);系统退出，jvm结束（此时finally不会执行）
 */
//自定义异常
class FuShuException1 extends Exception{
    FuShuException1(String msg1){

        super(msg1);
    }
}
class Demo5{
    int div(int a,int b)throws FuShuException1 {
        if (b < 0) {
            throw new FuShuException1("被0除啦");
        }
        return a/b;
    }
}
public class Begin29 {
    public static void main(String[] args){
        Demo5 d = new Demo5();

        try{
            int x = d.div(4,-1);
            System.out.println("x="+x);
        }
        catch (FuShuException1 e){
            System.out.println(e.toString());
        }
        finally {
            System.out.println("finally");
        }
    }
}
