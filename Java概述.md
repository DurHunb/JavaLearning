# 一、常用DOS命令

- dir👉列出当前目录下的文件以及文件夹
- md👉创建目录
- rd👉删除目录
- cd👉进入指定目录
- cd..👉退回到上一级目录
- cd\👉退回到根目录
- del👉删除文件
- exit👉退出dos命令行，补充：echo javase>1.doc



# 二、Java版本类别说明

- Java SE ——Java Standard Edition，标准版
  - 支持面向桌面级应用的Java平台，提供完整核心API
- Java EE ——Java Enterprise Edition，企业版
  - 是为开发企业环境下的应用程序提供的一套解决方案
  - 主要针对Web应用程序开发
- Java ME——Java Micro Edition，小型版
  - 支持Java程序运行在移动终端（手机，PDA）上的平台
  - 对Java API有所精简，并加入了针对移动终端的支持
- Java Card
  - 支持一些Java小程序（Applets）运行在小内存设备（如智能卡）上的平台

# 三、Java相关知识

## 1、JDK&JRE		

​		JDK=JRE+开发工具集

​		JRE=JVM+Java SE标准类库

- JDK

> JDK是提供给Java开发人员的，其中包含了Java的开发工具，也包含了JRE
>
> 其中的开发工具：编译工具（javac.exe）,打包工具（jar.exe）

- JRE

> 包括Java虚拟机 JVM（Java Virtual Machine）和Java所需的核心类库等，
>
> 如果想要运行一个开发好的Java程序，计算机只需要安装JRE即可

- JVM

> 因为有了JVM，同一个Java程序在三个不同的操作系统中都可以执行。这样就实现了Java程序的跨平台性
>
> 存在Win版、Linux版、Mac版的JVM，Java程序实际上是运行在JVM上的



## 2、注释

- 单行注释

> //单行注释

- 多行注释

> /*
>
> 多行注释：
>
> 在这里的描述只能供解释说明
>
> */

- 文档注释（Java特有）
  - 注释内容可被JDK提供的工具javadoc解析，生成一套以网页文件形式体现的该程序的说明文档
  - cmd 操作方式  `D:\code>javadoc -d mydoc -author -version helloWorld.java`
    - 生成名字是mydoc的文件夹，

> /**
>
> @author RENBO (指定Java程序的作者)
>
> @version 1.0.01  (指定源文件的版本)
>
> */



## 3、Java API文档

- API（Application Programming Interfaca，应用程序编程接口）是Java提供的基本编程接口
- Java语言提供了大量基础类，因此Oracle也为这些基础类提供了相应的API文档，介绍了如何使用这些类。
- [下载API](https://docs.oracle.com/javase/8/docs/api/index.html)



## 4、第一个程序总结

```java
public class Hello{
    //程序入口main方法，格式固定
    //args→arguments参数的缩写
    //args可变，比如a，比如b
    //[]的位置可以变，比如a[]
	public static void main(String[] args){
		System.out.println("Hello World!");
	}
}
class Person{

}
class Animal{

}
```



- 在一个Java源文件中可以声明多个class。但是，==只能最多有一个类声明为public==。
  - 因为要求声明为public的类的类名必须与源文件名相同

- 程序的入口是main()方法，格式是固定的。==只有一个main()==

- println与print区别
  - System.out.println() 先输出后换行，【相当于Python中后面跟了个\n】
  - System.out.print()    仅输出不换行
- ==每一个执行语句都以分号结尾==，==；==

- 编译以后会生成字节码文件
  - 字节码文件名和java源文件类名相同
  - 字节码文件数和java源文件类的数目相同