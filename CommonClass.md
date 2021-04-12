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





### 常用方法

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



```java
String replace(char oldChar, char newChar) //返回一个新字符串，它是通过newChar替换此字符串中出现的所有oldChar得到的
String replace(CharSequence target, CharSequence replacement) //返回一个新字符串，使用replacement替换此字符串中出现的所有target
    
String replaceAll(String regex,String replacement)//使用给定的replacement替换所有正则regex匹配上的子字符串
String replaceFirst(String regex,String replacement)//使用给定的replacement替换正则regex匹配上的第一个子字符串
    
boolean matches(String regex) //返回此字符串是否匹配给定的正则表达式

String[] split(String regex) //根据给定正则表达式的匹配拆分此字符串
String[] split(String regex,int limit) //根据给定正则表达式的匹配拆分此字符串,最多不超过limit个。如果超过了，剩下的全部都放到最后一个元素中
```





### String 、基本类型、包装类之间的转换

见《面向对象》



### String与字符数组char[]之间的转换



```java
//String ---->  char[] : 使用String自带的方法，toCharArray()

    @Test
    public void test1() {
        String s1 = "abc123";
        char[] charArray = s1.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            System.out.println(charArray[i]);
        }
    }


//char[] ---->  String : 使用String的构造器

	@Test
    public void test2(){
        char[] arr = new char[]{'h','e','l','l','o'};
        String s2 = new String(arr);
        System.out.println(s2); //hello
    }
```





### String与字符数组byte[]之间的转换

> 解码时，要求和编码时所用字符集一致，否则会出现乱码
>
> 

```java
//编码：String ---->  byte[] : 使用String自带的方法，getBytes()
    @Test
    public void test1() {
        String s1 = "abc123中国";
        byte[] bytes1 = s1.getBytes();//使用默认字符集
        byte[] bytes2 = s1.getBytes("gbk");//使用gbk字符集
        System.out.println(Arrays.toString(bytes1));//[97, 98, 99, 49, 50, 51, -28, -72, -83, -27, -101, -67]
        System.out.println(Arrays.toString(bytes2));//[97, 98, 99, 49, 50, 51, -42, -48, -71, -6]
    }


//解码：byte[] ----> String  : 使用String的构造器
    @Test
    public void test2(){
        String s = "abc123中国";
        
        byte[] bytes1 = s.getBytes();
        String s1 = new String(bytes1);        
        System.out.println(s1);//abc123中国
        
        byte[] bytes2 = s.getBytes("gbk");
        String s2 = new String(bytes2,"gbk");        
        System.out.println(s2);//abc123中国
    }
```













### 面试题


1、`String s = new String("abc")`的方式创建对象，在内存中创建了几个对象？

- 两个：一个是堆空间中的new结构，另一个是`char[]`对应的常量池中的数据：“abc”



2、如下算法的输出值是什么

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

















## 2、`StringBuffer`&`StringBuilder`





- **`StringBuffer`**

> `java.lang.StringBuffer`代表**可变的字符序列**，JDK1.0中声明，可以对字符串内容进行增删，此时不会产生新对象。
>
> `StringBuffer`的很多方法和String相同。
>
> 作为参数传递时，方法内部可以改变值



- **`StringBuilder`**

> `StringBuilder`与`StringBuffer`类似，均代表可变的字符序列，而且提供相关功能的方法也一样









### 常用方法

```java
//因为本身可变，所以不用像 String一样用新的字符串接受
append(xxx)//字符串拼接
delete(int start,int end)//删除[start,end)的内容
replace(int start,int end,String str)//把[start,end)位置替换为str
insert(int offset, xxx)//在指定位置插入xxx
reverse()//把当前字符序列逆转
toString()//查看值

//与String类似的方法
public int indexof(String str)//返回该字符串出现时的索引
public String substring(int start,int end)//返回[start,end)的子字符串
public int length()//返回字符串的长度
public char charAt(int n)//返回索引n处的字符    
public void setCharAt(int n,char ch)//将指定索引n的字符变为ch

```



###  面试题

对比`String`、`StringBuffer`、`StringBuilder`

- `String`：不可变字符序列；底层使用 final char[ ]存储；
- `StringBuffer`：可变字符序列；线程安全，效率低；底层使用char[ ]存储；
- `StringBuilder`：可变字符序列；线程不安全，效率高；底层使用char[ ]存储；



- 源码分析

```java
String str = new String();	//char[] value = new char[0] 底层创建了一个长度是0的数组
String str = new String("abc");	//char[] value = new char[]{'a','b','c'}

StringBuffer sb1 = new StringBuffer(); //char[] value = new char[16] 底层创建了一个长度是16的数组
StringBuffer sb2 = new StringBuffer("abc"); //char[] value = new char["abc".lenth()+16] 

/*
问题一:扩容问题
如果要添加的数据底层数组盛不下了，那就需要扩容底层的数组。
默认情况下，扩容为原来容量的2倍+2，同时将原有数组中的元素复制到新的数组中
*/


```

故，如果需要对字符串频繁的修改，则需要使用`StringBuffer`、`StringBuilder`，以避免频繁的创建对象。

再根据是否线程安全进行选择。

```java
开发中建议使用	StringBuffer(int capacity) 或 StringBuilder(int capacity)
从而自定义字符串容量，避免之后再扩容，提高效率
```







# 二、日期时间API



## System类获取时间戳

- 从System类里中获得时间戳

```java
@Test
public void test(){
    long time = System.currentTimeMillis();
    //返回当前时间距离1970年1月1日0时0分0秒之后，以毫秒为单位的时间差。
}
```





## Date类

> java.util.Date类，表示特定的时间，精确到毫秒
>
> java.sql.Date类，对应数据库中的日期类型的变量，用数据库时使用。
>
> java.util.Date类是java.sql.Date类的父类。

- 构造器

	```java
	Date();//使用无参的构造器创建的对象可以获取本地时间
	Date(long date);
	```

- 常用方法

	```java
	getTime();//返回当前时间距离1970年1月1日0时0分0秒之后，Date对象表示的毫秒数（时间戳）。
	toString();//把Date对象转换为以下形式的String:  dow mon dd hh:mm:ss zzz yyy
	//其中
	//dow是一周中的某一天(Sun,Mon,Tue,Wed,Thu,Fri,Sat)
	//zzz是时间标准
	```



```java
//java.util.Date
@Test
public Test1(){
    //构造器一:Date(),创建一个对应当前对象的Date对象
    Date date1 = new Date();
    System.out.println(date1.toString());//当前时间,Wed Apr 07 01:57:55 CST 2021
    System.out.println(date1.getTime());//时间戳,1617731875492
    
    //构造器二:Date(long date),	date是毫秒数(时间戳)
    Date date1 = new Date(1617731875492L);
    System.out.println(date1.toString());//Wed Apr 07 01:57:55 CST 2021
}
```



```java
//java.sql.Date
@Test
public Test2(){
    //构造器一:Date(),创建一个对应当前对象的Date对象
    java.sql.Date date3 = new java.sql.Date(1617731875492L);
    System.out.println(date3);//2021-04-07
    
}
```



```java
java.sql.Date对象 <===>  java.util.Date对象

//情况一
//java.sql.Date对象转为java.util.Date对象
Date date4 = new java.sql.Date(1617731875492L);//多态
//java.util.Date对象转为java.sql.Date对象
Date date5 = (java.sql.Date)date4;//强转的前提是，它本来就是由子类转成父类的

//情况二
Date date6 = new Date();
java.sql.Date date7 = new java.sql.Date(date6.getTime());//date6原本非sql.Date类，不能强转
```







## SimpleDateFormat类

> java.text.SimpleDateFormat类



```java
//格式化:日期 ---> 字符串
public SimpleDateFormat()//用默认的模式创建对象
public SimpleDateFormat(String pattern)//用参数pattern指定的格式创建一个对象

public String format(Date date)//方法用来格式化	时间对象date

//解析:字符串 ---> 日期
public Date parse(String source)//从给定的字符串开始，以生成一个日期
```



```java
//public SimpleDateFormat()//用默认的模式创建对象
	@Test
    public void testSimpleDateFormat() throws ParseException {
        //实例化SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat();

        //格式化:日期 ---> 字符串
        Date date1 = new Date();
        String format = sdf.format(date1);
        System.out.println(format);//21-4-8 上午1:16

        ////解析:字符串 ---> 日期
        String str = "21-4-8 上午1:16";//使用默认构造器只能解析这种默认格式的
        Date date2 = sdf.parse(str);
        System.out.println(date2);
    }

//public SimpleDateFormat(String pattern)//用参数pattern指定的格式创建一个对象
    @Test
    public void testSimpleDateFormatPattern() throws ParseException {
        //实例化SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //格式化:日期 ---> 字符串
        Date date1 = new Date();
        String format = sdf.format(date1);
        System.out.println(format);//2021-04-08 01:37:33

        ////解析:字符串 ---> 日期
        String str = "2021-04-08 01:37:33";//使用自定义构造器，解析这种自定义格式的
        Date date2 = sdf.parse(str);
        System.out.println(date2);
    }
```











## Calender类

> java.util.Calendar 类，是一个抽象基类。

获取Calendar实例的方法

- 使用`Calender.getInstance()`方法
	- 其实该方法调用的还是子类`GregorianCalendar`的构造器
- 调用它的子类`GregorianCalendar`的构造器

```java
/*
一个Calender的实例是系统时间的抽象表示，通过`get(int field)`方法来取得想要的时间信息。
比如YEAR、MONTH、DAY_OF_WEEK、HOUR_OF_DAY、MINUTE、SECOND
*/
public void get(int field)
public void set(int field,int value)
public void add(int field,int amount)
public final Date getTime() //日历类	----->	Date
public final void setTime(Date date)
```

注意：

- 获取月份时：一月是0，二月是1，以此类推，十二月是11
- 获取星期时：周日是1，周一是2，以此类推，周六是7

```java
    @Test
    public void testCalendar(){
        //实例化
        //方式一:创建子类`GregorianCalendar`的对象(一般不用)
        //方式二:使用静态方法Calender.getInstance()
        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.getClass());//class java.util.GregorianCalendar

        //常用方法
        //get()
        int days = calendar.get(Calendar.DAY_OF_MONTH);//获取今天是当前月份第几天
        System.out.println(days);//8（今天是四月八号）

        //set()
        calendar.set(Calendar.DAY_OF_MONTH,22);//设置属性为22
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//22

        //add
        calendar.add(Calendar.DAY_OF_MONTH,3);//在当前属性上，再加3天
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//25  (22+3)

        //getTime():日历类----->Date
        Date date1 = calendar.getTime();

        //setTime():Date----->日历类
        Date date2 = new Date(1617731875492L);
        calendar.setTime(date2);//Calendar的时间修改为date2的时间
    }
```









## Java.time

> LocalDate、LocalTime、LocalDateTime类的实例是不可变对象，
>
> 分别表示公历的日期、时间、日期和时间

- `LocalDateTime`使用的频率较高

```java
    @Test
    public void test(){
        //now,获取当前的日期、时间、日期+时间
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);//2021-04-12

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);//17:30:47.467

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);//2021-04-12T17:30:47.468

        //设置指定的年月日
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 10, 1, 12, 00, 00);
        System.out.println(localDateTime1);
    }
```































