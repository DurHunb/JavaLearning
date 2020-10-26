# 0.一、JAVA的标识符

> Java对各种变量、方法和类等要素命名时使用的字符序列成为标识符
>
> 凡是可以自己起名字的地方都叫做标识符

- 由26个英文字母大小写，0-9，__或$  组成
- 数字不可以开头
- 不可以使用关键字和保留字，但可以包含关键字和保留字
- Java中严格区分大小写，长度无限制
- 标识符不能包含空格



## 命名规范

- 包名👉所有字母都小写，`xxxyyyzz`
- 类名、接口名👉所有单词的首字母大写：`XxxxYyyZzzz`
- 变量名、方法名👉第一个单词首字母小写，第二个单词开始每个单词首字母大写：`xxxYyyZzz`
- 常量名👉所有字母都大写，每个单词用下划线连接：`XXX_YYY_ZZZ`



# 二、JAVA的变量

```java
class VariableTest{
    public static void main(String[] args){
        int myAge =12;
        System.out.println(myAge);
    }
}
```

- 概念
  - 内存中的一个存储区域
  - 该区域的数据可以在同一类型范围内不断变化
  - 变量是程序的最基本存储单元，包含变量类型、变量名和存储的值
- 作用：
  - 用于在内存中保存数据
- 注意事项：
  - JAVA变量必须先声明后使用
  - 变量的作用域仅在其定义的一对{}内



## 变量的分类

### 按数据类型

- 基本数据类型
  - 数值型
    - 整数类型：byte，short，int，long
    - 浮点类型：float，double
  - 字符型：char
  - 布尔型：boolean

- 引用数据类型
  - 类：class  👈 ==字符串是一个类==
  - 接口：interface
  - 数组：[]
  
  

### 按声明的位置

- 在方法体外，类体内声明的变量称为==成员变量==
  - 实例变量——不以static修饰
  - 类变量——以static修饰
- 在方法体内声明的变量称为==局部变量==
  - 形参——方法，构造器中定义的变量
  - 方法局部变量——在方法内定义
  - 代码块局部变量——在代码块内定义

## 基本数据类型(8种)

### 整数类型

> byte，short，int，long

- Java各整数类型有固定的表数范围和字段长度，不受具体OS影响，以保证java程序的可移植性
- Java的整型常量默认为int型，声明long型常量必须后加`I`或`L`
- Java程序中变量通常声明为int型，除非不足以表示较大的数，才使用long

==1字节=8bit位，bit是最小存储单位，byte是基本存储单元==

| 类型  | 占用存储空间 |         表数范围          |
| :---: | :----------: | :-----------------------: |
| byte  |    1字节     |         -128~127          |
| short |    2字节     | -2^15       ~      2^15-1 |
|  int  |    4字节     | -2^31       ~      2^31-1 |
| long  |    8字节     | -2^63      ~      2^63-1  |

```java
class Variable{
	public static void main(String[] args){
		byte b1 = 12;
		byte b2 = -128;
		//byte b3 = 128;编译不通过
        short s1 =128;
        int i1=1234;
        //声明long型变量，必须以l或L结尾。
        //不带l或L，会转为int
        long l1=123124523L;
	}
}
```



### 浮点类型

> float，double
>
> e与E没有区别，都表示10的次方；`512.0f`表示浮点型float

- 有固定的表数范围和字段长度，不受具体操作系统影响
- 浮点型常量有两种表现方式
  - 十进制，如：`5.12`      ` 512.0f`   ` .512`        
  - 科学计数法，如：`5.12e2  `    `512E2   `     `100E-2`       
- Java的浮点型常量通常为double型。若声明float型时，需要加`f`或`F`

|     类型     | 占用存储空间 |          表数范围          |
| :----------: | :----------: | :------------------------: |
| 单精度float  |    4字节     |  `-3.403E38` ~ `3.403E38`  |
| 双精度double |    8字节     | `-1.798E308` ~ `1.798E308` |



### 字符类型

>1字符=2字节
>
>Java中的所有字符都是用Unicode编码，故一个字符可以存储一个字母，一个汉字，或者其他书面语的一个字符
>
>char类型是可以进行运算的，因为它都有对应的`Unicode`码。

三种表现形式：

- 字符常量通常是用==单引号==（' '）括起来的==单个==字符，
  - char `c1` = 'a'；char `c2` = ’中‘；char `c3` = ’9‘
  - char `c1` = 97 ，实际上`c1`=a
  - ==多个字符是不行的==，如`c1` = ‘AB’
  - ==没有字符是不行的==，空格也算一个字符
- 转义字符，如：char `c3` = ’\n'
- 直接使用Unicode值来表示字符型常量，`\uXXXX`，如：`\u000a`表示\n



### 布尔类型

> boolean，值只有true 与false
>
> 注意是小写



## 基本数据类型的运算规则

> 不包含boolean类型，只讨论7种基本数据类型变量间的运算

- 自动类型提升
  - 当容量小的跟容量大的做运算时，结果自动提升为容量大的类型
  - byte👉short👉int👉long👉float👉double
    - ==此时的容量大小指的是表示数的范围，而不是内存占用大小==
    -  (从左到右存储容量依次递增) 
  - byte、char、short👉int
    - 这三个变量要是相互之间做运算，结果都是INT
- 强制类型转换——将容量大的转为容量小的
  - 需要强转符：()
  
  - 可能导致精度损失👉只保留了符合容量小的要求的存储位数，其余左边的数全部舍弃
  
    ```java
    int i4 = 128;
    byte b2 = (byte)i4;//b2 = -128
    //因为 int 128 本为 0000 0000 0000 0000 0000 0000 1000 0000
    //转为byte 后 只剩 1000 0000  //根据计算机二级制储存(见下方)，1变为符号位，表负数；其余根据补码的方式读取，表128
    ```
  
- 特殊情况
  - 整型常量，默认类型为int型；浮点型常量，默认类型为double型
  - ==声明long型常量必须后加`I`或`L`，声明float型常量必须加`f`或`F`==

```java
class Variable{
    public static void main(String[] args){
        byte b1 = 2;
        int i1 = 129;
        //自动类型提升，选存储大的
        int i2 = i1 + b1;
        
        //强转类型提升，精度损失1
        double d1 = 12.3;
        int i3 = (int)d1;//i3 = 12
        //精度损失2
        int i4 = 128;
        byte b2 = (byte)i4;//b2 = -128
        
        //特殊情况
        //float f1 = 12.3;//编译出错，12.3视为double型
        //float f1 = (float)12.3;//这样不会出错，或者float f1 = 12.3f
        byte = b3;
        //byte b4 = b3 + 1;//编译错误，1视为int型
        //float f1 = b3 + 12.3;//编译失败，12.3视为double型
    }
}
```

 

## 字符串类型：String

- String不是基本数据类型，属于引用数据类型。

- 字符串和其他八种数据类型做运算，且只能是连接运算，即+

  ```java
  //String 只能做连接运算
  String str1 = 123;//编译不通过
  int num = "123";//编译不通过
  int num = (int)"123";//编译不通过
  //要转也行
  int num1 = Integer.parseInt("123");
  ```

- 声明String类型变量时，使用一对双引号“”

  ```java
  String str = "abcd"  //注意是"",而不是''.单引号是char使用的
  str = str + "xyz";  // abcdxyz
  int n = 100;
  str = str + n;//abcdxyz100
  boolean x = true;
  str =str + x;//abcdxyz100true
  ```

  ```java
  //练习题1
  char c = 'a';//97  A:65
  int num = '10';
  String str = "HHHH";
  System.out.println(c + num + str);//107HHHH
  System.out.println(c + str + num);//aHHHH10
  System.out.println(c + (num + str));//a10HHHH
  System.out.println(str + num + c);//HHHH10a
  
  //练习题2
  //搞明白想要Char做运算还是做连接
  //现在要输出*   *
  System.out.println("*	*");//√
  System.out.println('*' + '\t' + '*');//结果为93,char对应ASCLL码,而且有➕,会做加法运算
  System.out.println('*' + "\t" + '*');//√
  System.out,println('*' + '\t' + "*");//结果为51*,
  System.out.printlm('*' + ('\t' + "*"));//√
  ```

  

# 三、进制

- 二进制（binary）：0，1
  - 满2进1，以`0b`或`0B`开头👉`0b123`
  - ==最高位(最右边)是符号位，1表负数，0表正数==
  - 原码，反码，补码
    - 正数的反码与其原码相同；负数的反码是对其原码逐位取反，但符号位除外。
    - 正数的补码与其原码相同；负数的补码是在其反码的末位加1。

| -14原码                 | 1     | 0     | 0     | 0     | 1     | 1     | 1     | 0     |
| ----------------------- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- |
| **-14反码**（原码取反） | **1** | **1** | **1** | **1** | **0** | **0** | **0** | **1** |
| **-14补码**（反码+1）   | **1** | **1** | **1** | **1** | **0** | **0** | **1** | **0** |

> 计算机底层都以补码的方式存储数据
>
> 所以未有明确说明时，都视为补码

- 十进制（decimal）：0-9
- 八进制（octal）：0-7
  - 满8进1，以数字0开头👉`0123`
- 十六进制（hex）：0-9及A-F
  - 满16进1，以`0x`或`0X`开头
  - A-F不区分大小写，如：`0x21AF + 1 = 0X21B0`





# 四、运算符



## 1、算术运算符

- 加减乘除：+	-	*	/
- 取模：% 👉 `7%5=2`
- 结果的符号和被模数是一样的：`(-12)%(-5)=-2`，`(-12)%5=-2`，`12/(-5)=2`
- ++，--           ==不改变原本数据类型==

```java
a=2;b=++a;//a=3,b=3
a=2;b=a++;//a=3,b=2
```

---

```java
a=2;b=--a;//a=1,b=1
a=2;b=a--;//a=1,b=2
```
---
```java
//让s1改为11
short s1 = 10;
s1 = s1 + 1;//错误，需要强制类型转换
s1 = (short)(s1 + 1);//正确
s1++;//正确，不会改变数据类型
```

- 注意数据类型的范围

```java
int a = 12 ;
int b = 5 ;
double c = a / b ; //2.0
double c = a / (b + 0.0);//2.4
double c = (double)(a / b);//2.0
double c = (double)a / b;//2.4
System.out.println('c='+c);
```



## 2、赋值运算符

- =

```java
  int i1 = 10;
```

```java
  int i1 ,j1 ;
  i1 = j1 = 10;
```

```java
  int i1 = 10,j1 = 10;
```

- `+=`；`-=`；`*=`；`/=`；`%=`           ==不改变数据类型==
  -  用法与python一致

```java
//不改变数据类型
short s1 = 1 ;
s1 += 2;//编译通过,3
int s2 = 1;
s2 *= 0.1;//编译通过，0
```

```java
//练习
int m = 2,n = 3;
n *= m++; //n=6,m=3
int u=10;
u += (u++)+(++u)//u=32
```



## 3、比较运算符

> 比较运算符的结果都为boolean类型
>
> 区分 == 和 =

- `==`,`!=`,`<`,`>`,`<=`,`>=`

- `instanceof`——检查是否是类对象

  ```java
  "Hello" instanceof String //true
  ```

  ```java
  //区分==和=
  boolean b1 = true, b2 = false;
  System.out.println(b2==b1);//false
  System.out.println(b2=b1);//true,这是把b1值赋给b2，再输出b2
  if(b2=true)//把true赋值给b2
  if(b2==true)//判断是否为true
  ```

  

## 4、逻辑运算符

> 和位运算符的区别：==逻辑运算符左右两侧都是Boolean类型==

| 逻辑运算符 | 运算                                                         |
| :--------: | ------------------------------------------------------------ |
|     &&     | 短路与，当第一个条件为假时，不用再判断后面的条件，结果为假； |
|     &      | 与，所有条件都要进行检查。                                   |
|    \|\|    | 短路或，当第一个条件为真时，不用再判断后面的条件，结果为真； |
|     \|     | 或，所有条件都要进行检查。                                   |
|     !      | 逻辑非                                                       |
|     ^      | 逻辑异或，当且仅当两个操作数具有不同的布尔值时，两个布尔型操作数的异或（^）才为true。 |



|   a   |   b   |  a&b  | a&&b  | a\|b  | a\|\|b |  !a   |  a^b  |
| :---: | :---: | :---: | :---: | :---: | :----: | :---: | :---: |
| true  | true  | true  | true  | true  |  true  | false | false |
| true  | false | false | false | true  |  true  | false | true  |
| false | true  | false | false | true  |  true  | true  | true  |
| false | false | false | false | false | false  | true  | false |



## 5、位运算符

>位运算是直接对整数的==二进制补码==进行的运算，且正数的补码反码原码一致，
>
>注意，无<<<
>
>注意，==左移后正负号的变化，首位为符号位；缺的位补0==
>
>注意，==右移后符号位不变，若最高位为1，则缺的位都补1；若最高位为0，则缺的位都补0==

| 位运算符 |    运算    | 范例                                              |
| :------: | :--------: | ------------------------------------------------- |
|    <<    |    左移    | 3<<2=12  (左移两位，每移动一位相当于×2)           |
|    >>    |    右移    | 3>>1=1    (右移一位，每移动一位相当于/2)          |
|   >>>    | 无符号右移 | 3>>>2=1 （==最高位无论是1或者0，空缺位都用0补==） |
|    &     |   与运算   | 6 & 3 =2                                          |
|    \|    |   或运算   | 6 \| 3 = 7                                        |
|    ^     |  异或运算  | 6 ^ 3 = 5  (不一样时为1，一样为0)                 |
|    ~     |  取反运算  | ~ 6 = -7                                          |



## 6、三元运算符

> 简化版if-else语句

- （条件表达式）？表达式1：表达式2
  - 为true，运算后结果是表达式1
  - 为false，运算后结果是表达式2

```java
//求两个整数最大值
int m = 10,n = 5;
int max = (m > n)? m : n;

//没有要求表达式一和表达式二必须要相同，但是必须要统一为一个类型
double num = (m > n)? 2:1.0;

//可以嵌套
String maxStr = (m > n)? "m大":((m == n)? "一样大":"n大");
```



# 五、流程控制

> 流程控制语句是用来控制程序中各语句执行顺序的语句
>
> 其流程控制方式采用结构化程序设计中规定的三种基本流程结构，即：
>
> ​			顺序结构，分支结构，循环结构



## if-else

```java
class TestHeart{
    public static void main(String[] args ){
        int a = 3 , b = 5;
        if(a > b){
            System.out.println("a="+a);
        }
        else if(a == b){
            System.out.println("相等");
        }
        else{
            System.out.println("b="+b)
        }
    }
}
```

- 细节

如果`if`后的不加括号，则默认下一行为条件执行语句。

如果`else`前没有加括号，且上方存在两个`if`，则根据`就近原则`，归属于更靠近的if

```java
int x = 4,y = 1;
//第一种情况
if(x>2){
    if (y>2)
        System.out.println("我是H1");
		System.out.println("我是H2");//不属于if(y>2)的判断，必会执行
}else
    System.out.println("我是H3")
    
//第二种情况
if(x>2)
    if (y>2)
        System.out.println("我是H1");
else//归属if(y>2)
    System.out.println("我是H3");
```





## Scanner类

```java
simport java.util.Scanner;
class ScannerTest{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        System.out.println(num);
    }
}
```

```java
//没有scan.nextChar
String name = scan.next();
int age = scan.nextInt();
double weight = scan.nextDouble();
boolean isLove = scan.nextBoolean();
```





## switch-case

> 根据switch表达式中的值，依次匹配各个case中的常量，一旦匹配成功，则进入相应的case结构中，调用其执行语句。
>
> 当调用完毕后，仍然继续往下执行其他case结构中的执行语句(==不进行case条件匹配==)，直到遇到break关键字，或至此switch-case结构末尾为止



- switch表达式，即switch（数据类型），只能是如下6种数据类型之一：
  - byte、short、char、int、枚举类型、String类型
- ==建议每个case结构都要加break==，当然也可以根据实际情况判断要不要加。
  - 不加break的话，是利用从上至下顺序执行的特性
- case之后只能声明常量，不能声明范围
- `switch-case`执行效率比`if-else`高

```java
//不加break则按顺序执行
class SwitchCaseTest{
    public static void main(String[] args){
        int number = 2;
        switch(number){
            case 0:
                System.out.println("Zero");//不执行
            case 1:
                System.out.println("one");//不执行
            case 2:
                System.out.println("two");//执行
                //break;
            case 3:
                System.out.println("three");//执行
            default:
                System.out.println("other");//执行
        }
    }
}
```

- default 位置灵活。如果default里不加break，则也会按顺序执行其他case结构下执行语句，直到break或执行完。

```java
        int number = 4;
        switch(number){
            default:
                System.out.println("other");//执行
            case 0:
                System.out.println("Zero");//执行
            case 2:
                System.out.println("two");//执行
                break;
            case 3:
                System.out.println("three");//不执行
        }
```

- 当case执行语句相同时，可以合并
  - 其实也是`没有break，则默认执行下方case的执行语句`的原因

```java
int score = 78;
switch(score/10){
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
        System.ou.println("不及格");
        break;
    case 6:
    case 7:
    case 8:
    case 9:
    case 10:
        System.ou.println("及格");
        break;
}
```





## for 循环



```java
class ForTest{
    public static void main(String[] args){
        for(int i = 1;i < 5;i++){
            System.out.println("Ha");
        }
        
        //练习,acbcbcb
        int num = 1;
        for(System.out.print('a');num<=3;System.out.print('b'),num++){
            System.out.print('c');
        }
    }
}
```

