import java.io.*;

/**
 * 知识点：RandomAccessFile
 * Java输入/输出流体系中功能最丰富的文件内容访问类。
 * 功能：1.读取文件
 *       2.向文件输入数据
 *       3.支持随机访问（准确来说是任意访问）
 *       4.可以向已有文件后面追加内容
 * 缺陷：只能读写文件，不能读写其他IO节点
 * 支持任一访问的原因：
 * RandomAccessFile允许自由定位文件记录指针，而且RandomAccessFile对象也包含了一个记录指针用以标识当前读写的位置
 *
 * File中delete()方法和deleteOnExit()方法的区别：
 * 当调用delete()方法时，直接删除文件，不管该文件是否存在，一经调用立即执行；
 * 当程序运行结束，JVM终止时调用deleteOnExit()方法实现删除操作。即该方法是将删除的命令缓存了一下，到服务停止的时候再进行操作
 *
 */
public class Begin54 {
    public static void main(String[] args)throws IOException{
//        RandomAccessFileTest();

//        AppendContent();

        insert("J:\\out.txt",45,"插入的内容\r\n");
    }

    //使用RandomAccessFile来访问指定的中间部分数据
    public static void RandomAccessFileTest(){
        //创建RandomAccessFile对象，该对象以“只读”方式打开指定的文件
        try(RandomAccessFile raf = new RandomAccessFile("E:\\JAVA\\untitled\\src\\Begin54.java","r")){
            //获取RandomAccessFile对象文件指针的位置，初始位置是0
            System.out.println("RandomAccessFile的文件指针的初始位置：" + raf.getFilePointer());
            raf.seek(300);  //移动raf的文件记录指针的位置，将文件记录指针定位到300处，也就是说程序从300字节开始读
            byte[] bbuf = new byte[1024];
            int hasRead = 0;    //用于保存实际读取的字节数
            //使用循环来重复“取水”过程
            while ((hasRead = raf.read(bbuf)) > 0){
                System.out.println(new String(bbuf,0,hasRead));  //取出“竹筒”中的水滴（字节），将字节数组转成字符数组输入
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //向指定文件中追加内容
    public static void AppendContent(){
        //以读、写方式打开一个RandomAccessFile对象
        try(RandomAccessFile raf = new RandomAccessFile("J:\\out.txt","rw")){
            raf.seek(raf.length());     //将记录指针移动到J:\\out.txt文件的末尾
            raf.write("追加的内容！\r\n".getBytes());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //向指定文件、指定位置插入内容
    public static void insert(String fileName,long pos,String insertContent)throws IOException{
        /**createTempFile()用于创建一个临时文件，该文件将会在JVM退出时被删除 */
        File tmp = File.createTempFile("tmp",null);
        tmp.deleteOnExit(); //当程序运行结束，JVM终止时才真正调用deleteOnExit()方法实现删除操作。
        try(
                RandomAccessFile raf = new RandomAccessFile(fileName,"rw");
                //使用临时文件来保存插入后的数据
                FileOutputStream tmpOut = new FileOutputStream(tmp);
                FileInputStream tmpIn = new FileInputStream(tmp)){
            raf.seek(pos);
            //——————以下代码将插入点后的内容读入临时文件中保存——————//
            byte[] bbuf = new byte[64];
            int hasRead = 0;    //用于保存实际读取的字节数
            //使用循环的方式读取插入点后的数据
            while ((hasRead = raf.read(bbuf)) > 0){
                tmpOut.write(bbuf,0,hasRead);   //将读取的数据写入临时文件
            }
            //——————下面代码用于插入内容——————//
            raf.seek(pos);  //把文件记录指针重新定位到pos位置
            raf.write(insertContent.getBytes());    //追加需要插入的内容
            //追加临时文件中的内容
            while ((hasRead = tmpIn.read(bbuf)) > 0){
                raf.write(bbuf,0,hasRead);
            }
        }
    }
}
