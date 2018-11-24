/**
 * 知识点：StringBuffer、StringBuilder
 *
 * StringBuffer是字符串缓冲区，
 *   1.是一个容器
 *   2.其长度是可变化的
 *   3.可以直接操作多个数据类型
 *   4.最终会通过toString方法变成字符串
 *
 * 容器具备的功能(部分)：
 *     1.存储：StringBuffer append()：将指定数据作为参数添加到已有数据的结尾处
 *     2.删除：StringBuffer delete(start,end):删除缓冲区中的数据，包含start，不包含end
 *             StringBuffer deleteCharAt(index)：删除指定位置的字符
 *     3.获取：char charAt(int index);
 *             int indexOf(String str);
 *             int length()
 *             String substring(int start,int end);
 *     4.修改：StringBuffer replace(start,end,string);替换字符串
 *             void setCharAt(int index,char ch);替换一个字符
 *     5.反转：StringBuffer reverse();
 *     6.将缓冲区中指定数据存储到指定字符数组中：
 *             void getChar(int srcBegin,int srcEnd,char[] dst,int dstBegin);包含头不包含尾
 *
 * StringBuilder：
 * StringBuffer是线程同步（多线程使用）；StringBuilder是线程不同步（单线程使用）
 * StringBuilder不是线程安全的，而StringBuffer是线程安全的。但StringBuilder在单线程中的性能比StringBuffer高。
 * 以后开发，建议使用StringBuilder
 *
 * Java升级的三个目的：提高效率；简化书写；提高安全性
 */
public class Begin44 {
    public static void main(String[] args){

//        method_add();

//        method_delete();

//        method_update();
        method_getChar();
    }

    public static void method_getChar(){
        StringBuffer sb = new StringBuffer("abcde");
        char[] chs = new char[6];

        sb.getChars(1,4,chs,1);

        for (int x = 0;x <chs.length;x ++){
            sop("chs["+x+"]="+chs[x]+";");
        }
    }

    public static void method_update(){
        StringBuffer sb = new StringBuffer("abcde");

//        sb.replace(1,4,"java");
        sb.setCharAt(2,'k');
        sop(sb.toString());
    }

    public static void method_delete(){
        StringBuffer sb = new StringBuffer("abcde");

        sb.delete(1,3);

        //删除一个字符
        sb.deleteCharAt(2);

        //清空缓冲区
        sb.delete(0,sb.length());

        sop(sb.toString());

    }

    public static void method_add(){
        StringBuffer sb = new StringBuffer();
        sb.append(34).append("abc").append(3.78);  //方法调用链

        sb.insert(1,"qq");

        sop(sb.toString());
    }
    public static void sop(String str){
        System.out.println(str);
    }
}
