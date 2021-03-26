# 一、字符串相关的类



## 1、String

- String类代表字符串，Java中所有的字符串如“ABC”，都作为此类的实例实现

- String类是一个final类，表示<font color='red'>不可变</font>的字符序列

  字符串是一个常量，用双引号引起来表示。它的值在创建后不能更改

  ——字符串相加、替换都是重新建立一个新的字符串

- String对象的字符内容是存储在一个字符数组Value[]中的

![image-20210322112431003](D:\ForLife\Learning\JavaLearning\CommonClass.assets\image-20210322112431003.png)

- String实现了`Serializable`接口，表示字符串是支持序列化的

  String实现了Comparable接口，表示String可以比较大小



### String的实例化方式

- 通过字面量定义的方式
- 通过`new+构造器`的方式



```java
@Test
public void test(){
    //通过字面量的方式:数据“JavaEE”声明在方法区字符串常量池中
    String s1 = "JavaEE";
    String s2 = "JavaEE";
    //通过new+构造器的方式:数据保存在堆空间中开辟空间以后对应的地址值
    String s3 = new String("JavaEE");
    String s4 = new String("JavaEE");
    
    //== 对于引用类型比较的是地址值，对于基本类型比较的是值
    //String的equals重写过，比较的是值
    System.out.println(s1 == s2);//true
    System.out.println(s1 == s3);//false
    System.out.println(s1 == s4);//false
    System.out.println(s1 == s2);//false
    
    System.out.println(“**************************************”);
    Person p1 = new Person("Tom");
    Person p2 = new Person("Tom");
    
    System.out.println(p1.name.equals(p2.name));//true
    System.out.println(p1.name == p2.name);//true，因为通过自变量定义的数据都在常量池中
    
}
```

> 接 System.out.println(p1.name == p2.name)
>
> <img src="D:\ForLife\Learning\JavaLearning\CommonClass.assets\image-20210326160822257.png" alt="image-20210326160822257" style="zoom: 40%;" />

