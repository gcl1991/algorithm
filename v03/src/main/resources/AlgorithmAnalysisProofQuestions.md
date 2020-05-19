## 3.1 打印指定位置的值

见代码（没想出只使用Collections的public方法，在打印时使用了List的get方法）

时间复杂度$N*O(max(Collections.min(),Collections.replaceAll())$

## 3.3~3.5已经在程序中实现

## 3.6 josephus问题

a 使用双链表迭代器，在迭代器上进行remove较为高效

b 时间复杂度$(m+1)*(n-1)$

假设外层循环需要x次

有 $x*n/(m+1)=n-1$,每隔m删除1个，所以是m+1,同时需要保留最后一个所以共删除n-1个元素。

则时间复杂度$n*x=(m+1)*(n-1)$

