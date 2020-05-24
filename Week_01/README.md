学习笔记（Queue和PriorityQueue源码分析）

一、Queue源码
主要有会抛异常的add,remove,element和允许返回null的offer,poll,peek六个方法，其中add和offer的作用是添加一个元素，remove和poll的作用是删除一个元素，element和peek的作用是查看最上面一个元素。
添加、删除时间复杂度均为O(1).

二、PriorityQueue源码
成员private static final int DEFAULT_INITIAL_CAPACITY = 11;表明默认的初始化容量为11，其构造方法：
public PriorityQueue(int initialCapacity,
                         Comparator<? super E> comparator) {
        // Note: This restriction of at least one is not actually needed,
        // but continues for 1.5 compatibility
        if (initialCapacity < 1)
            throw new IllegalArgumentException();
        this.queue = new Object[initialCapacity];
        this.comparator = comparator;
    }
	它也有插入元素的add和offer方法(含上浮方法siftUp)，删除元素的remove和poll方法（含下移方法siftDown）。
	插入时间复杂度为O(1),取出时间复杂度为O(logN)。

参考：https://blog.csdn.net/youyou1543724847/article/details/52164895
