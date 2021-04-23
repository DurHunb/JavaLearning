> 数组存储的特点：
>
> - 一旦初始化，其长度也就确定了
> - 数组一旦定义好，其元素类型也就确定了。只能操作指定类型的数据

> 数组存储的弊端：
>
> - 一旦初始化，长度不可修改
> - 数组提供的方法非常有限
> - 数组只能存储有序、可重复的数据







# Collection接口



<img src="D:\ForLife\Learning\JavaLearning\Java集合.assets\image-20210422205658860.png" alt="image-20210422205658860" style="zoom: 40%;" />

### Collection接口常用方法

```java
    @Test
    public void test1(){
        Collection coll = new ArrayList();

        //1. add(Object e)
        coll.add("AA");
        coll.add(LocalDateTime.now());

        //2. size()
        System.out.println(coll.size());//4
        //2.1 addALL(Collection coll)
        Collection coll1 = new ArrayList();
        coll1.add("BB");
        coll1.add(456);
        coll.addAll(coll1);
        System.out.println(coll.size());//6

        //3. isEmpty(),判断当前集合是否为空
        System.out.println(coll.isEmpty());//false

        //4. clear 清空集合元素
        coll.clear();

        //5. contains(Obj obj)判断当前集合中是否包含obj
        coll.add("123");
        coll.add(new String("Tom"));

        boolean contains = coll.contains("123");
        System.out.println(contains);//true
        //注意，以下String对象是内容相同，虽然是两个对象，但仍然是true
        //调用equals()方法判断，故如果是自定义类，需要重写equals方法
        System.out.println(coll.contains(new String("Tom")));//true

        //5.1 containsAll(Collection coll1)
        //判断形参coll1中所有元素是否都存在于当前集合中
        Collection coll2 = Arrays.asList(123,456);//List对象
        System.out.println(coll.containsAll(coll2));//false
    }
```



```java
    @Test
    public void test2(){
        //6. remove(Obj obj),从当前集合中删除指定元素，返回boolean值
        //也调用equals方法
        Collection coll = new ArrayList();
        coll.add(111);
        coll.add(222);
        coll.add(123);
        coll.add("456");
        coll.add(new String("Tom"));
        coll.add(789);
        System.out.println(coll.remove(111));//true

        //6.1 removeAll(Collection coll1),从当前集合中移除coll1中所有的元素
        Collection coll1 =Arrays.asList(new String("Tom"),789);
        System.out.println("removeALL:"+coll.removeAll(coll1));//true
        System.out.println("coll:"+coll);//[222, 123, 456]

        //7. retainAll(Collection coll2),
        //获取当前集合和coll2集合的交集，并返回给当前集合
        Collection coll2 =Arrays.asList(123,222);
        coll.retainAll(coll2);
        System.out.println("coll:"+coll);//coll:[222, 123]

        //8.equals()
        //逐个相比，即使内容一样，如果位置不一样，也是false
        //因为List是有序的，如果是set则不比有序，结果为true
        System.out.println(coll.equals(coll2));//false
    }
```



```java
    @Test
    public void test3() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add("456");
        coll.add(new String("Tom"));
        coll.add(789);

        //9.集合 ----> 数组,toArray()
        Object[] arr = coll.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        //数组 ----> 集合,调用Arrays的静态方法asList
        List<Object> list = Arrays.asList(arr);
        System.out.println(list);//[123, 456, Tom, 789]

        /*
        注意:
        如果直接传递数组，基本类型数组将被视为一个对象而不会被解析成数组
        但是如果直接传递参数将能正常解析。
        因此传递基本类型数组时强烈建议转为其封装类对象的数组
        */
        //情况1:只要传递的基本类型的数组，生成List的元素个数均为1
        List arr1 = Arrays.asList(new int[]{123, 456});
        System.out.println(arr1.size());//1
        //情况2:传递对象数组，元素个数正确。
        List arr2 = Arrays.asList(new Integer[]{123, 456});
        System.out.println(arr2.size());//2
        //情况3:传递参数，元素个数正确。
        List arr1 = Arrays.asList(123, 456);
        System.out.println(arr1.size());//2
    }
```



```java
        //10.iterator,返回Iterator接口实例，用于遍历集合元素
        //见本文Iterator
```



### Iterator

> 集合元素的遍历操作，使用迭代器Iterator接口。
>
> Collection接口继承了`java.lang.Iteratble`接口，该接口有一个Iterator()方法，用以返回一个实现了Iterator接口的对象。



## 1、List接口

> 元素有序、可重复的集合。
>
> 可以扩展长度，称作”动态数组“，相对静态数组而言

- Vector
- ArrayList
- LinkedList





## 2、Set接口

> 元素无序、不可重复的集合

- HashSet
- LinkedHashSet
- TreeSet



# Map接口

> 保存具有映射关系"key-value"的集合

<img src="D:\ForLife\Learning\JavaLearning\Java集合.assets\image-20210422205621284.png" alt="image-20210422205621284" style="zoom:40%;" />



- HashMap
- LinkedHashMap
- TreeMap
- Hashtable
- Properties