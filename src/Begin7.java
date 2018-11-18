/**
 * 知识点：封装、类与对象
 * private:私有、权限修饰符，用于修饰类中的成员（成员变量、成员函数），
 * 私有只在本类中有效
 * 私有仅仅是封装的一种表现形式
 *
 * 将对象私有化以后，类以外即使建立了对象也不能直接访问，就需要在私有对象所在的类中提供对应访问该对象的方式（接口）
 * (防止该类的代码和数据被外部类定义的代码随机访问)
 * 封装最主要的功能在于我们能修改自己的实现代码，而不用修改那些调用我们代码的程序片段。
 *
 * 之所以对外提供访问方式，是因为可以在访问方式中加入逻辑判断等语句，
 *   对访问的数据进行操作提高代码的健壮性
 *
 * 类与对象：
 * Person p = new Person();这句话都做了什么：
 * 1.因为new中用到了Person.class，所以会先找到Person.class文件并加载到内存
 * 2.执行该类中的static代码块，如果有的话，给Person.class类进行初始化
 * 3.在堆内存中开辟空间，分配内存地址
 * 4.在堆内存中建立对象得特有属性，并进行默认初始化
 * 5.对属性进行显性初始化
 * 6.对对象进行构造代码块初始化
 * 7.对对象进行对应的构造函数初始化
 * 8.将内存地址赋值给栈内存中的p变量
 */
public class Begin7 {
    static class Person{
        private int age; //将age私有化以后，类以外即使建立了对象也不能直接访问，就需要在Person类中提供对应访问age的方式
        private String name;

        /*
        在Person类中提供的对应访问age的方式：
        一个成员遍历通常会对应两个访问方式：设置和获取
         */
        public void setAge(int a){  //此处必须是void
            if (a > 0 && a < 130)
            age = a;
        }

        public int getAge(){  //此处必须不是void

            return age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        void speak(){

            System.out.println("age="+age);
        }

    }

    public static void main(String[] args){
        Person p = new Person();
        p.setAge(20);
        p.setName("Yyt");
        p.speak();
        System.out.println("Name：" + p.getName() + "\n" + "Age：" + p.getAge());
    }
}

