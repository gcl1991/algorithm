## 2.1 增长率排序
$$ {不知道如何左对齐}
\begin{align} 
&[\frac{2}{N}] \\  
&[37] \\ 
&[\sqrt{N} < N] \\ 
&[N\log{\log{N}} < N\log{N} < N\log{N^2} < N\log^2{N}] \\ 
&[N^{1.5} < N^{2} < N^{2}\log{N} < N^{3}] \\ 
&[2^{N/2} < 2^{N}]
\end{align}
$$

## 2.2 等式成立判断

a. T~1~(N) + T~2~(N) = O(f(N))	成立, 加法只会改变常数项

b. T~1~(N) - T~2~(N) = o(f(N))	成立，因为T~2~(N)>0

c. T~1~(N)  /  T~2~(N) = O(1) 	不成立，$N^2/N \neq O(1)$	

d. T~1~(N) = O(T~2~(N))	不成立，无法确定此关系

## 2.3 哪个函数增长更快

$N^{1+\frac{\epsilon}{\sqrt{\log{N}}}}$增长速率快于$N\log{N}$

$$
\lim_{N \to \infty} \frac{N\log{N}} {N^{1 + \frac{\epsilon} {\sqrt{\log{N}}}}} = \frac{\log{N}} {N^{\frac{\epsilon}{\sqrt{\log{N}}}}}
$$

因为$\frac{\epsilon}{\sqrt{\log{N}}}>0$，即证明$\lim_{N \to \infty} \frac{\log{N}} {N^{k}},(k>0)$,k>1此式一定成立，只需证明1>k>0时成立即可

$$
\begin{align}
\lim_{N \to \infty} \frac{\log{N}} {N^{k}} &= \frac{1}{N} \cdot \frac{1}{k \cdot N^{k-1}},(1>k>0)(洛必达法则) \\
                                           &= \frac{1} {k \cdot N^{k}} \\ 
                                           &= 0
\end{align}
$$





## 2.4 证明对于任意常数k，$\log^k{N}=o(N)$

可以利用洛必达法则证明
$$
\begin{align}
\lim_{N \to \infty} \frac{\log^k{N}}{N} &= \frac{\frac{k\log^{k-1}{N}}{N}}{1} \\
										&= \frac{k\log^{k-1}{N}}{N} \\
										&= \frac{k(k-1)\log^{k-2}{N}}{N} \\
										&\cdots \\
                                        &=k\cdot(k-1)\cdots3\cdot2\log{N}/N \\
                                        &=k\cdot(k-1)\cdots3\cdot2\cdot1/N \\
                                        &=0
\end{align}
$$


## 2.5 求函数f(N)和g(N)

这个题没有思路

## 2.6 法庭审理案件问题

a 第N天的罚金为$2^{2^{N-1}}$,指数为等比数列，*a*~1~=1,等比_q_=2，通项公式为$2^{N-1}$

b 罚金达到D美元需要$\log{\log{D}}+1$
$$
\begin{align}
2^{2^{N-1}} & = D \\ 
\log{D} & = 2^{N-1} \\ 
2^{N-1} & = \log{D} \\
\log{\log{D}} & = N - 1 \\
N & = \log{\log{D}} +1
\end{align}
$$

## 2.7 六个程序片段的分析

a. 大O运行时间分析，（1）所有声明均不计时间；（2）忽略调用方法和返回值的开销；

(1)sum=0 is1; 	for is 1+(n+1)+n=2n+2;	sum++ is 2；

result = $1+ (2n+2) + n*2 = 3+2n+2n = 3+4n = O(n)$

(2) $1 + (2n+2) + n(2n+2) + n^2(2) = 3+2n+2n^2+2n+2n^2 = 3+4n+4n^2 = O(n^2)$

(3) $1 + (2n+2) + n(2n^2+2) + n^3(2) = 3+2n+2n^3+2n+2n^3 = 3+4n+4n^3 = O(n^3)$

(4) $1 + (2n+2) + (2(0+1+2...+n-1) + 2n) + (0+1+2...+n-1)*2 = O(n^2)$

(5) 
$$
\begin{align}
1 + (2n+2) + (2(0^2 + 1^2 + 2^2+ ... +(n-1)^2) + 2n) + \\
(2(0^3 + 1^3 + 2^3+ ... +(n-1)^3)+2(0^2 + 1^2 + 2^2+ ... +(n-1)^2)) + \\
2(0^3 + 1^3 + 2^3+ ... +(n-1)^3)  \\
=O(n^{4})
\end{align}
$$

(6) 
$$
\begin{align}
1 + (2n+2) + (3(1^2 + 2^2+ ... +(n-1)^2) + 2(n-1) + \\
\{2((1-1)*1 + (2-1)*2+ ... +(n-1-1)*(n-1))+2((1-1) + (2-1)+ ... +(n-1-1))\} + \\
2\{(1-1)*1 + (2-1)*2+ ... +(n-1-1)*(n-1)\}  \\
=O(n^{3})
\end{align}
$$

b. java语言编程计算实际运行时间

数据类型long,依次为a1-a6

n=100

RunTime:2 ms
RunTime:1 ms
RunTime:4 ms
RunTime:0 ms
RunTime:665 ms
RunTime:24 ms

n=200

a1 RunTime:0 ms
a2 RunTime:1 ms
a3 RunTime:12 ms
a4 RunTime:1 ms
a5 RunTime:19822 ms
a6 RunTime:147 ms

n=400

RunTime:2 ms
RunTime:4 ms
RunTime:41 ms
RunTime:1 ms
RunTime:622346 ms
RunTime:2174 ms

n=800

RunTime:1 ms
RunTime:4 ms
RunTime:272 ms
RunTime:1 ms
RunTime:未测，时间太长
RunTime:36042 ms

n=1600

RunTime:1 ms
RunTime:4 ms
RunTime:1806 ms
RunTime:3 ms
RunTime:未测，时间太长
RunTime:未测，时间太长

n=3200

RunTime:1 ms
RunTime:9 ms
RunTime:12440 ms
RunTime:4 ms
RunTime:未测，时间太长
RunTime:未测，时间太长

n=10240(1w)

a1 RunTime:2 ms
a2 RunTime:66 ms
a4 RunTime:24 ms

n= 102400(10w)

RunTime:2 ms
RunTime:4157 ms
RunTime:1905 ms

n= 1024000(100w)

a1 RunTime:6 ms

a2 未测，时间太长

a4 未测，时间太长

c. 比较程序运行时间与分析结果

n=200,数据类型long

a1 RunTime:0 ms $O(N)$
a2 RunTime:1 ms $O(N^2)$
a3 RunTime:12 ms $O(N^3)$
a4 RunTime:1 ms $O(N^2)$
a5 RunTime:19822 ms $O(N^4)$
a6 RunTime:147 ms $O(N^3)$

$O(N^{2})$以上的时间复杂度还是蛮可怕的

10w级别$O(N^{2})$还是可以接受的

100w级别$O(N^2)$也是难以接受了

## 2.8 随机置换

a. 很容易得出算法是合理且所有置换都是可能的。

b. 第一个算法为$O(N^{2})$, 第二个算法为$\Omega (N)$和$O(N^{2})$之间, 	第三个算法为$\Theta(N)$

c. 

算法1 10次平均

250 avg runtime:1 ms
500 avg runtime:1 ms
1000 avg runtime:7 ms
2000 avg runtime:22 ms
RunTime:402 ms

算法2 

25000 avg runtime:5 ms
50000 avg runtime:8 ms
100000 avg runtime:17 ms
200000 avg runtime:38 ms
400000 avg runtime:94 ms
800000 avg runtime:181 ms
RunTime:3547 ms

算法3

100000 avg runtime:3 ms
200000 avg runtime:4 ms
400000 avg runtime:8 ms
800000 avg runtime:20 ms
1600000 avg runtime:45 ms
3200000 avg runtime:120 ms
6400000 avg runtime:231 ms
RunTime:4372 ms

d. 不知道怎么进行比较，但是800000 avg runtime:20 ms与100000 avg runtime:17 ms与2000 avg runtime:22 ms时间上基本是相同的。

e. 第一个算法最坏每次randint，平均下来都需要N次，最坏$1.5N^{2}$；第二个算法最坏每次randint，平均下来都需要N次,最坏$N^2$；第三个算法没有最坏情况。

## 2.9没读懂这个题什么意思

## 2.10 手工算法的时间分析

a. N位整数相加，涉及到N次加法和0\~(N-1)可能的进位，所以是N\~2N-1之间，$O(N)$

b. N位整数相乘，涉及到N*N次乘法和(N-1)\*N次加法，所以是$O(N^2)$

c. N位整数除法，每次运算涉及到1次乘法和1次减法，所以是$O(N)$

## 2.11运行时间估计

a. 线性：时间为0.5ms*500/100 = 2.5ms

b. NlogN：时间为100\*2(log100=2)是0.5ms，500运行时间为0.5\*500\*2.69/100/2=3.36ms

c. $O(N^{2})$：时间为：0.5ms\*500\*500/100/100=12.5ms

d.$O(N^3)$: 时间为: 0.5ms*5^3=62.5ms

## 2.12 解决问题规模分析（此处为了方便计算log以10为底）

a. 线性：1分钟可以解决问题规模为1000*(100/0.5ms)=200,000

b. NlogN：1000*100\*2/0.5ms=400,000=NlogN,N$\approx$100,000

c. $O(N^{2})$：1000\*100\*100/0.5ms=10^7=N^2,N=3333

d.$O(N^3)$: 1000\*100\*100\*100/0.5ms=10^9=N^3,N=1000

## 2.13 计算求和时间

编程实现，a=1，x=2，n=10000000,（注意：此时计算结果已经超出long范围）

a  266ms

b 969ms

## 2.14 Horner法则

a. 对于$4x^4+8x^3+x+2$，N=4，a~i~数组为[4,8,0,1,2], 当x=3时，展开式为

$((((0*3+4)*3+8)*3+0)*3+1)*3+2$

b. 该算法相当于从高阶到低阶逐层嵌套，每层外面乘*x*

c. 1+{2(n+1)+2}+{3(n+1)}=$O(n)$

## 2.15 确定整数序列中存在A~i~=i问题

因为序列是递增的整数，所以存在两种情况，对于A~i~>i,则A~i~toA~N~均不满足要求

先用二分法找出小于A~i~<=i的i位置，然后遍历这个序列，最坏时间复杂度为O(logN)+N



## 2.17  子序列和问题

a. 求最小子序列和

证明$O(N)$时间复杂度算法的正确性

假设求和序列为a~i~，数组长度为N。

1. 首先检查数组中是否存在负数，如果不存在负数，直接返回数组中的最小值即可。

2. 如果数组中存在负数，则最小子序列和一定是小于0；

针对情况2，可利用下列代码求得，也就是说序列和一定为负数，如果为正数则将序列和清空为0，序列和只有两种合法状态，小于0和0。

```java
int minSum = 0, thisSum = 0;
for (int i = left; i <= right; i++) {
    thisSum += array[i];
    if (thisSum > 0)
        thisSum = 0;
    else if (thisSum < minSum)
        minSum = thisSum;
}
```

下面证明代码的正确性

首先可以得知最小子序列首元素一定为负数，假设可能的最小子序列包含于子序列$a[i \to j]$中。

假设$a[i]<0,a[j]>0,\sum_{x=i}^{j-1}(a[x])<0,\sum_{x=i}^{j}(a[x])>0$;

只需要证明（定义问题）

可能的最小子序列一定以a[i]为首元素，而任何以其他元素开头的序列都不可能为最小子序列

首先将序列拆分成x与y两段，x段代表以a[i]为首元素的序列，y代表不以a[i]为首元素的序列，同时x与y正好组成完成的a[i->j]序列,（问题转换为数学证明）

1 首先证明序列x<0，y>0恒成立，即任何以a[j]结尾的序列都不可能是最小子序列,任何以a[i]为首元素的序列可能是最小序列。

根据已知条件$\sum_{x=i}^{j-1}(a[x])<0$，即x<0恒成立，而由于x+y>0,所以y<0恒成立，1得证。

2 下面证明任何不包含a[i]的序列都不可能是最小序列。

将序列x拆分为x1与x2，其中x1以a[i]为首元素，则有

x1+x2=x<0

已知$\sum_{x=i}^{j-1}(a[x])<0$，可得x1<0恒成立，而对于x2存在三种情况

x2<0，x2>0，x2=0

假设x2为最小序列，则有x1+x2<x2,假设不成立。

所以任何不包含a[i]的序列都不可能是最小序列得证。

综上所述：对于包含可能最小序列的序列$a[i]<0,a[j]>0,\sum_{x=i}^{j-1}(a[x])<0,\sum_{x=i}^{j}(a[x])>0$;可能的最小子序列一定以a[i]为首元素，而任何以其他元素开头的序列都不可能为最小子序列。

所以if (thisSum > 0) then thisSum = 0成立。

b. 求最小正子序列和

递归

递归方式与书中讲述计算最大子序列递归方式相同

唯一不同之处在于对于中间情况，应先扫描获取中间部分的最小值，然后再向两边逐渐扩展扫描，找到第一个正数和，即为最小正数和。

非递归

```java
int minSumPositive = Integer.MAX_VALUE;
int currentSum = 0;
for (int value : array) {
    currentSum += value;
    if (currentSum > 0 && currentSum < minSumPositive) {
        minSumPositive = currentSum;
    }
    if (currentSum > 0) {
        currentSum = 0;
    }
}
```

c.求最大子序列乘积

