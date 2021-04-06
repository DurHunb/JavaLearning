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



两种方式的对比

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
    
    //通过类的自变量的方式建立字符串
    Person p1 = new Person("Tom");
    Person p2 = new Person("Tom");
    
    System.out.println(p1.name.equals(p2.name));//true
    System.out.println(p1.name == p2.name);//true，因为通过自变量定义的数据都在常量池中
    
}
```

> 接 System.out.println(p1.name == p2.name)
>
> <img src="D:\ForLife\Learning\JavaLearning\CommonClass.assets\image-20210326160822257.png" alt="image-20210326160822257" style="zoom: 40%;" />





### String的拼接方式

- 常量与常量的拼接结果在常量池中，且常量池中不会存在相同内容的常量
- 拼接中，只要有一方为变量，结果在堆中
- 如果拼接的结果调用`intern()`，返回值就在常量池中

```java
@Test
public void test(){
    String s1 = "JavaEE";
    String s2 = "hadoop";
    
    String s3 = "JavaEEhadoop";  		//在常量池中
    String s4 = "JavaEE" + "hadoop";	//在常量池中
    String s5 = s1 + "hadoop";   		//有变量参与，在堆空间中new了一个新对象
    String s6 = "JavaEE" + s2;	 		//有变量参与，在堆空间中new了一个新对象
    String s7 = s1 + s2;		 		//有变量参与，在堆空间中new了一个新对象
    
    System.out.println(s3 == s4);//true
    System.out.println(s3 == s5);//false
    System.out.println(s3 == s6);//false
    System.out.println(s3 == s7);//false
    
    System.out.println(s5 == s6);//false
    System.out.println(s5 == s7);//false
    
    System.out.println(s6 == s7);//false
    
    String s8 = s5.intern();//intern方法要求返回的结果一定要在常量池中
    System.out.println(s3 == s8);//true
    
}
```





### String的常用方法

```java
int length() //返回字符串的长度
char charAt(int index) //返回某索引处的字符
boolean isEmpty() //判断是否为空字符串
String toLowerCase() //将String中所有字符转换为小写
String toUpperCase() //将String中所有字符转换为大写
String trim() //返回字符串副本，忽略前面和尾部空白
boolean equals(Obj object) //比较字符串的内容是否相同
boolean equalsIgnoreCase(String anotherString) //与equals类似，忽略大小写
String concat(String str) //将指定字符串连接到此字符串的结尾。等价于“+”
int compareTo(String anotherString) //比较两个字符串大小
String substring(int beginIndex) //返回了一个新的字符串，它是从beginIndex开始截取到最后一个的字符串
String substring(int beginIndex,int endIndex) //返回了一个新的字符串，它是从beginIndex开始截取到endIndex(不包含)的一个字符串
```



```java
boolean endsWith(String suffix)//测试此字符串是否以指定的后缀结束
boolean startsWith(String prefix)//测试此字符串是否以指定的前缀开始
boolean startsWith(String prefix,int toffset)//测试此字符串从指定索引处开始的子字符串，是否以指定的前缀开始
    
boolean contains(CharSequence s)//当且仅当此字符串包含指定的char值序列，返回true
int IndexOf(String str) //返回指定字符串在此字符串中第一次出现处的索引
int IndexOf(String str,int fromIndex) //返回指定字符串在此字符串中第一次出现处的索引,从指定索引开始
int lastIndexOf(String str)//返回指定子字符串在此字符串中，最右边时出现的索引//即从后往前找，返回的索引还是从左往右的
int lastIndexOf(String str,int fromIndex)//返回指定子字符串在此字符串中，最后一次出现时的索引，从指定索引处反向搜索

//IndexOf和lastIndexOf方法如果未找到都是返回-1
```









































































# 面试题



**String**



1、`String s = new String("abc")`的方式创建对象，在内存中创建了几个对象？

- 两个：一个是堆空间中的new结构，另一个是`char[]`对应的常量池中的数据：“abc”



2、

> 这里涉及到值传递
>
> str1相当于重新创建的对象，指向了str的地址"good"
>
> 由于字符串不可变，重新建立了一个常量"test ok"，str1重新指向常量"test ok"。而str没有变

```java
public class StringTest {
    
    String str = new String("good");
    char[] char = {'t','e','s','t'};
    
    public void change(String str1, char[] ch){
        str1 = "test ok";
        ch[0] = 'b';
    }
    
    public static void main(String[] args){
        StringTest ex = new StringTest();
        ex.change(ex.str,ex.ch);
        
        System.out.println(ex.str);//good
        System.out.println(ex.ch);//best
    }
    
}
```

