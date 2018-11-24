import java.io.FileInputStream;
import java.io.IOException;

/**
 * 知识点：异常--finally
 * 使用finally回收资源；
 * finally中存放的是一定要被执行的代码，通常用于关闭资源。
 *
 * 在finally块中使用return、throw等，将会导致try块、catch块中的return、throw语句失效。
 *
 * 关闭资源：System.exit(0);系统退出，jvm结束（此时finally不会执行）
 */
public class Begin29 {
    public static void main(String[] args){

        finallyTest();

        boolean a = fianllyFlowTest();
        System.out.println(a);
    }

    public static void finallyTest(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("obj.txt");
        }catch (IOException e){
            System.out.println(e.getMessage());
            return;//使用return语句强制返回
            //使用exit退出虚拟机，则finally块将会失去执行机会
//            System.exit(1);
        }
        finally {
            //关闭磁盘文件，回收在try块中使用（打开）的资源
            if (fis != null){
                try {
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            System.out.println("执行finally块里的资源回收！");
        }
    }
    public static boolean fianllyFlowTest(){
        try {
            //因为finally语句中包含了return语句，所以下面一行一句将会失效
            return true;
        }finally {

            return false;
        }
    }
}
