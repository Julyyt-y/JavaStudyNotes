/**
 * 知识点：包package
 *  包的作用：1.对类文件进行分类管理；2.给类提供多层命名空间；
 *  注意：包写在程序文件的第一行；类名的全称是  包名.类名；包也是一种封装形式
 *
 * 总结：1.包与包之间进行访问，被访问的包中的类以及类中的成员，需要public修饰
 *       2.不同包中的子类还可以直接访问父类中被protected权限修饰的成员
 *       （包与包之间可以使用的权限只有两种：public 和 protected ）
 *
 * import:导入包中的类或子包中的类
 *
 * Jar包（Java中的压缩包）
 *
 */

package pack;  //包名的所有字母均小写
//package pack.A.B.C
public class Begin32 {
    public static void main(String[] args){

        System.out.println("main method run");
    }
}
