import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 知识点：对象序列化
 *
 * 对象序列化：
 * 对象序列化的目标：把对象保存在磁盘中，或允许在网络中直接传输对象；
 * 对象序列化允许把内存中的Java对象转换成平与台无关的二进制流，从而允许把这种二进制流持久的保存在磁盘上，
 * 通过网络将这种二进制流传输到另一个网络节点；
 * 其他程序一旦获得了这种二进制流（无论是从磁盘中获取还是从网络中获取），都可以将这种二进制流恢复成原来的Java对象。
 *
 * 序列化机制允许将实现序列化的Java对象转换成字节序列，这些字节序列可以保存在磁盘上，或通过网络传输；
 * 序列化机制使得对象可以脱离程序而独立存在；
 * 对象的序列化是指将一个Java对象写入IO流中，对象的反序列化是指从IO流中恢复该Java对象；
 * 所有可能在网络上传输的对象的类都应该是可序列化的，否则程序将会出现异常。
 *
 * 序列化对象的步骤：
 * 1、让类实现Serializable接口（这样这个类的对象就是可序列化的）
 * 2、创建一个ObjectOutputStream输出流
 * 3、调用ObjectOutputStream对象的writeObject()方法输出可序列化对象
 *
 * 反序列化的步骤：
 * 1、让类实现Serializable接口（这样这个类的对象就是可序列化的）
 * 2、创建一个ObjectInputStream输入流
 * 3、调用ObjectInputStream对象的readObject()方法读取流中的对象，该方法返回一个Object类型的Java对象（可强制转换成其他类型的对象）
 *
 * 对象引用的序列化：
 * 1.如果某个类的成员变量的类型不是基本类型或String类型，而是另一个引用类型，
 *   则这个引用类必须是可序列化的，否则拥有该类型成员变量的类也是不可序列化的；
 * 2.Java序列化机制采用的序列化算法：
 *     1）.所有保存到磁盘中的对象都有一个序列化编号；
 *     2）.当程序序列化一个对象时，会先检查该对象是否已经被序列化过，只有在该对象没被序列化时，系统才会将该对象转换成字节序列并输出；
 *     3）.若某个对象已经序列化过，则程序只是输出一个序列化编号，而不是重新序列化该对象；
 * 3.这种机制存在的潜在问题：当程序序列化一个可变对象时，只有在第一次使用writeObject()方法输出时才会将该对象转换成字节序列并输出，
 *   当该对象的实例变量已经被改变时，被改变的实例对象不会被输出；
 * 4.改善这种潜在问题的方法：
 *   自定义序列化（1）：实例变量被transient修饰；
 *      被transient修饰的实例变量不可被序列化；
 *      因此也出现了一个问题：反序列化恢复Java对象是无法取得该实例变量值；
 *   自定义序列化（2）：可以控制如何实例化各实例变量；
 *      写入特定类的实例状态，以便相应的readObject()可以恢复它，
 *      怎么自定义：
 *          通过重写该方法，程序员可以完全获得对序列化机制的控制，自主决定需要序列化哪些实例变量，以及如何序列化，
 *          private void writeObject(ObjectOutputStream out) throws IOException{}；
 *          从流中读取并恢复对象的实例化变量，
 *          通过重写该方法，程序员可以完全获得对反序列化机制的控制，自主决定需要反序列化哪些实例变量，以及如何进行反序列化，
 *          private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException{}；
 *   自定义序列化（3）：
 *      重写writeReplace方法，程序在序列化该对象之前，先调用该方法，
 *      private Object writeReplace() throws ObjectStreamException{}，
 *      此序列化机制在序列化对象时，实际上是转化为ArrayList对象；
 *  另一种自定义序列化机制：
 *      此种序列化机制完全由程序员决定存储和恢复对象数据；
 *      必须实现Externalizable接口。
 *      虽然实现Externalizable接口会带来一定的性能提升，但实现该接口导致编程的复杂度增加，
 *      所以大部分时候都采用实现Serializable接口的方式。
 */
public class Begin55 {
    public static void main(String[] args) {

//        writeObject();

//        readObject();

//        WriteTeacher();

//        ReadTeacher0();

//        SerializeMutable();

//        transientTest();

        replaceTest();
    }

    //对象序列化
    public static void writeObject(){
        try(
                //创建一个ObjectOutputStream输出流，此输出流建立在一个文件输出流的基础上
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("J:\\Begin54.txt"))){
            Person0 per = new Person0("孙悟空",500);
            oos.writeObject(per);   //将per对象写入输出流
        }
        catch (IOException e){
                    e.printStackTrace();
        }
    }

    //对象序列化
    public static void readObject(){
        try(
                //创建一个ObjectOutputStream输出流，此输出流建立在一个文件输出流的基础上
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("J:\\Begin54.txt"))){
            //从输入流中读取一个Java对象，并将其强制类型转换为Person0类
            Person0 p = (Person0)ois.readObject();
            System.out.println("名字为：" + p.getName() + "\n年龄为：" + p.getAge());
        }
        catch (Exception e){
                    e.printStackTrace();
        }
    }

    //对象引用的序列化
    public static void WriteTeacher(){
        try(
                //创建一个ObjectOutputStream输出流
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("J:\\teacher.txt"))){
            Person0 per = new Person0("孙悟空",500);
            Teacher0 t1 = new Teacher0("唐僧",per);
            Teacher0 t2 = new Teacher0("菩提祖师",per);
            //依次将4个对象写入输入流
            oos.writeObject(t1);
            oos.writeObject(t2);
            oos.writeObject(per);
            oos.writeObject(t2);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //对象引用的序列化：展示出实例对象只被序列化一次
    public static void ReadTeacher0(){
        try(
                //创建一个ObjectInputStream输出流
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("J:\\teacher.txt"))){
            //依次读取ObjectInputStream输入流中的4个对象
            Teacher0 t1 = (Teacher0)ois.readObject();
            Teacher0 t2 = (Teacher0)ois.readObject();
            Person0 p = (Person0)ois.readObject();
            Teacher0 t3 = (Teacher0)ois.readObject();
            System.out.println("t1的student引用和p是否相同：" + (t1.getStudent() == p)); //输出true
            System.out.println("t2的student引用和p是否相同：" + (t2.getStudent() == p)); //输出true
            System.out.println("t2和t3是否是同一个对象：" + (t2 == t3));  //输出true
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //展示这种序列化机制的潜在问题
    public static void SerializeMutable(){
        try(
                //创建一个ObjectOutputStream输出流
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("J:\\teacher.txt"));
                //创建一个ObjectInputStream输出流
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("J:\\teacher.txt"))){
            Person0 per = new Person0("孙悟空",500);
            oos.writeObject(per);
            per.setName("猪八戒");
            oos.writeObject(per);
            Person0 p1 = (Person0)ois.readObject();
            Person0 p2 = (Person0)ois.readObject();
            System.out.println(p1 == p2);
            System.out.println(p2.getName());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //自定义序列化（1），展示被transient修饰的实例变量不可被序列化
    public static void transientTest(){
        try(
                //创建一个ObjectOutputStream输出流
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("J:\\transient.txt"));
                //创建一个ObjectInputStream输出流
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("J:\\transient.txt"))){
            Person00 per = new Person00("孙悟空",500);
            oos.writeObject(per);   //系统将per对象转换成字节序列并输出
            Person00 p = (Person00)ois.readObject();    //从序列化文件中读取该Person对象
            System.out.println(p.getAge()); //输出该Person对象的age实例变量。输出0，因为age不可以被实例化
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void replaceTest(){
        try(
                //创建一个ObjectOutputStream输出流
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("J:\\replace.txt"));
                //创建一个ObjectInputStream输出流
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("J:\\replace.txt"))){
            Person0000 per = new Person0000("孙悟空",500);
            oos.writeObject(per);   //写入Person0000的对象
            ArrayList list = (ArrayList)ois.readObject();   //反序列化读取得到的是ArrayList
            System.out.println(list);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

//让Person0类实现Serializable接口（这样改类的对象就是可序列化的）
class Person0 implements Serializable{
    private String name;
    private int age;
    //注意此处没有写无参数的构造器
    public Person0(String name, int age){
        System.out.println("有参数的构造器");
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
class Teacher0 implements Serializable{
    private String name;
    private Person0 student;
    public Teacher0(String name,Person0 student){
        this.name = name;
        this.student = student;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStudent(Person0 student) {
        this.student = student;
    }

    public Person0 getStudent() {
        return student;
    }
}
//自定义序列化（1）：使用transient修饰实例变量，从而使该实例变量不可序列化
class Person00 implements Serializable{
    private String name;
    private transient int age;  //使用transient修饰实例变量，使age不可序列化
    //注意此处没有写无参数的构造器
    public Person00(String name, int age){
        System.out.println("有参数的构造器");
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
//自定义序列化（2）
class Person000 implements Serializable{
    private String name;
    private int age;
    public Person000(String name,int age){
        System.out.println("无参数的构造器");
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    /**下面两个方法实现了第二种自定义序列化的第二种方式。
       注意writeObject()存储实例变量的顺序应和readObject()恢复实例变量的顺序一致，否则将不能恢复该java对象*/
    //写入特定类的实例状态，以便相应的readObject()可以恢复它
    //通过重写该方法，程序员可以完全获得对序列化机制的控制，自主决定需要序列化哪些实例变量，以及如何序列化
    private void writeObject(ObjectOutputStream out) throws IOException{
        out.writeObject(new StringBuffer(name).reverse());   //将name实例变量反转进二进制流
        out.writeInt(age);
    }
    //从流中读取并恢复对象的实例化变量
    //通过重写该方法，程序员可以完全获得对反序列化机制的控制，自主决定需要反序列化哪些实例变量，以及如何进行反序列化
    private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException{
        this.name = ((StringBuffer)in.readObject()).reverse().toString();   //将读取的字符串反转后赋给name实例变量
        this.age = in.readInt();
    }
}
//自定义序列化（3）
class Person0000 implements Serializable {
    private String name;
    private int age;

    public Person0000(String name, int age) {
        System.out.println("无参数的构造器");
        this.name = name;
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    //重写writeReplace方法，程序在序列化该对象之前，先调用该方法
    private Object writeReplace() throws ObjectStreamException{
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(name);
        list.add(age);
        return list;
    }
}
/**另一种自定义序列化机制
 * 此种序列化机制完全由程序员决定存储和恢复对象数据；
 * 必须实现Externalizable接口。
 * 虽然实现Externalizable接口会带来一定的性能提升，但实现该接口导致编程的复杂度增加，
 * 所以大部分时候都采用实现Serializable接口的方式。
 */
class Person01 implements Externalizable{

    //版本控制，防止类升级后不能被反序列化
    private static final long serialVersonUID = 512L;

    private String name;
    private int age;
    //注意提供无参数的构造器，否则会反序列化失败
    public Person01(){}
    public Person01(String name,int age){
        System.out.println("有参数的构造器");
        this.name = name;
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    //该方法调用DataOutput（ObjectOutput的父接口）的方法来保存基本类型的实例变量值，
    //调用ObjectOutput的writeObject()方法来保存引用类型的实例变量值。
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(new StringBuffer(name).reverse());  //将name实例变量反转后写入二进制流
        out.writeInt(age);
    }
    //该方法调用DataInPut（readObject的父接口）的方法来恢复基本类型的实例变量值，
    //调用ObjectInput的readObject()方法来恢复引用类型的实例变量值。
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = ((StringBuffer)in.readObject()).reverse().toString();   //将读取的字符串反转后赋给name实例变量
        this.age = in.readInt();
    }
}

