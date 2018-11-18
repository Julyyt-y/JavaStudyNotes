import java.io.FileReader;
import java.io.IOException;

/**
 * 知识点：装饰设计模式
 * 需求：自定义一个类中包含一个功能和readLine一致的方法，来模拟一下BufferedReader
 *
 * 装饰设计模式：
 * 当想要对已有的对象进行功能增强时，可以定义类，将已有对象传入，基于已有的功能，并提供加强功能，
 * 那么自定义的该类称为装饰类；
 * 装饰类通常会通过构造方法接收被装饰的对象，并基于被装饰的对象的功能，提供更强的功能。
 *
 * 装饰和继承的区别：（毕向东233）
 * 自定义装饰类：（毕向东234）
 *
 */
class MyBufferedReader1{
    private FileReader r;
    MyBufferedReader1(FileReader r){
        this.r = r;
    }
    //可以一次读一行数据的方法
    public String myReadLine()throws IOException{
        //定义一个临时容器，原BufferedReader封装的是字符数组
        //为了演示方便，定义一个StringBuilder容器，因为最终还是要将数据变成字符串
        StringBuilder sb = new StringBuilder();
        int ch = 0;
        while ((ch=r.read())!=-1){
            if (ch=='\r'){
                continue;
            }
            if (ch=='\n'){
                return sb.toString();
            }
            else
                sb.append((char)ch);
        }
        if (sb.length()!=0){
            return sb.toString();
        }
        return null;
    }
    public void myClose()throws IOException{
        r.close();
    }
}
public class MyBufferedReader {
    public static void main(String[] args)throws IOException{
        FileReader fr = new FileReader("E:\\buf.txt");
        MyBufferedReader1 mybuf = new MyBufferedReader1(fr);
        String line = null;
        while ((line=mybuf.myReadLine())!=null){
            System.out.println(line);
        }
        mybuf.myClose();
    }
}
