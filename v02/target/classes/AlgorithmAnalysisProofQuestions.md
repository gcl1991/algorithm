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