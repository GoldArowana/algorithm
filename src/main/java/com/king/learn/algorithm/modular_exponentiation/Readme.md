# 模幂运算 a^b % c #
<ul>
    <li><a href="https://en.wikipedia.org/wiki/Modular_exponentiation">模幂运算维基百科</a></li>
    <li><a href="http://blog.csdn.net/delphiwcdj/article/details/5973026">CSDN博客</a></li>
    <li><a href="http://www.cnblogs.com/yan-boy/archive/2012/11/29/2795294.html">矩阵快速幂</a></li>
    <li><a href="http://blog.csdn.net/u013923947/article/details/44264723">快速乘法</a></li>
</ul>

例如  ：4 ^ 13 mod 497 = 445<br>
例如  ：67 ^ 39 mod 356 = 223

## 算法一：Memory-efficient method ##
公式：
<ul>
    <li>c mod m = (a ⋅ b) mod m</li>
    <li>c mod m = [(a mod m) ⋅ (b mod m)] mod m</li>
</ul>


算法步骤:
<ol>
    <li>Set c = 1, e′ = 0.</li>
    <li>Increase e′ by 1.</li>
    <li>Set c = (b ⋅ c) mod m.</li>
    <li>If e′ < e, goto step 2. Else, c contains the correct solution to c ≡ be mod m.</li>
</ol>



伪代码：
<blockquote><pre>
function modular_pow(base, exponent, modulus)
    if modulus = 1 then return 0 
    c := 1
    for e_prime = 1 to exponent 
        c := (c * base) mod modulus
    return c
</pre></blockquote>

c++代码，来自博客http://blog.csdn.net/delphiwcdj/article/details/5973026
```c++
#include <cstdio>  
#include <cassert>  
// calculate a**b%c  
int fun(int a, int b, int c)  
{  
    assert(a && (b>=0) && c);  
    if (0==b)  
    {  
        return 1;  
    }   
    else  
    {  
        return fun(a, b-1, c)*a%c;  
    }  
}  
int main()  
{     
    int a, b, c;  
    while (3==scanf("%d%d%d",&a,&b,&c))  
    {  
        printf("%d/n",fun(a,b,c));// calculate a**b%c  
    }  
    return 0;  
}  
```

我用Java实现的代码：
```java
package Algorithm.NumberTheory.ModularExponentiation;

/**
 * Created by king on 2017/6/11.
 * <p>
 * 模幂运算 a^b mod c
 * <p>
 * 公式：
 * c mod m = (a * b) mod m
 * c mod m = [(a mod m) * (b mod m)] mod m
 */
public class MemoryEfficient {//内存高效法

    private int a;
    private int b;
    private int c;

    public MemoryEfficient(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void go() {
        int t = 1;
        for (int i = 0; i < b; i++) {
            t = (t * a) % c;
        }

        // 输出结果 t
        System.out.println(t);
    }

    //测试
    public static void main(String[] args) {
        MemoryEfficient m = new MemoryEfficient(4, 13, 497);// 4 ^ 13 % 497
        m.go();//计算并输出
    }
}

```
算法同上，只是采用了递归，Java代码：
```java
package Algorithm.NumberTheory.ModularExponentiation;

/**
 * Created by king on 2017/6/11.
 * <p>
 * 模幂运算 a^b mod c
 * <p>
 * 公式：
 * c mod m = (a * b) mod m
 * c mod m = [(a mod m) * (b mod m)] mod m
 */
public class MemoryEfficient_recursion {//利用递归

    private int a;
    private int b;
    private int c;

    public MemoryEfficient_recursion(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void go() {
        int answer = fun(a, b, c);
        System.out.println(answer);
    }

    public int fun(int a, int b, int c) {
        if (b == 0) {
            return 1;
        }

        return fun(a, b - 1, c) * a % c;
    }

    //测试
    public static void main(String[] args) {
        MemoryEfficient_recursion m = new MemoryEfficient_recursion(4, 13, 497);
        m.go();
    }
}

```

## 算法二：Right-to-left binary method ##
算法步骤：
><img src="../../../../msg/2017-06-11_115802.png">
当a=4，b=13，c=497 时：
><img src="../../../../msg/2017-06-11_115827.png">

伪代码：
<blockquote><pre>
function modular_pow(base, exponent, modulus)
    if modulus = 1 then return 0
    Assert :: (modulus - 1) * (modulus - 1) does not overflow base
    result := 1
    base := base mod modulus
    while exponent > 0
        if (exponent mod 2 == 1):
           result := (result * base) mod modulus
        exponent := exponent >> 1
        base := (base * base) mod modulus
    return result
</pre></blockquote>
我用Java实现的代码

```java

package Algorithm.NumberTheory.ModularExponentiation;

/**
 * Created by king on 2017/6/11.
 * <p>
 * 将指数的二进制 从右往左看
 */
public class RightToLeftBinary {

    private int a;
    private int b;
    private int c;

    public RightToLeftBinary(int a, int b, int c) {
        this.a = a % c;
        this.b = b;
        this.c = c;
    }

    public int fun() {
        int r = 1;
        if (c == 0) return 0;
        while (b > 0) {
            if ((b & 1) == 1) r = (r * a) % c;
            b = b >> 1;
            a = (a * a) % c;
        }
        return r;
    }

    public void go() {
        int answer = fun();
        System.out.println(answer);
    }

    //测试
    public static void main(String[] args) {
        RightToLeftBinary d = new RightToLeftBinary(4, 13, 497);
        d.go();
    }
}

```

## 算法三：Left-to-right binary  ##
<blockquote>
<img src="../../../../msg/2017-06-11_132746.png">
</blockquote>

我用Java实现的代码：
```java

package Algorithm.NumberTheory.ModularExponentiation;

/**
 * Created by king on 2017/6/11.
 * <p>
 * 将指数的二进制 从左往右看
 */
public class LeftToRightBinary {

    private int a;
    private int b;
    private int c;

    //指数b的二进制形式的位数
    private int b_bitSize;

    public LeftToRightBinary(int a, int b, int c) {
        this.a = a % c;
        this.b = b;
        this.c = c;

        for (int i = 0; (1 << i) <= b; i++) b_bitSize = i + 1;
    }
    

    public int fun() {
        int r = 1;
        for (int i = b_bitSize - 1; i >= 0; i--) {
            r = (r * r) % c;
            if (((1 << i) & b) == (1 << i)) r = (r * a) % c;
        }
        return r;
    }

    public void go() {
        int answer = fun();
        System.out.println(answer);
    }


    //测试
    public static void main(String[] args) {
        LeftToRightBinary d = new LeftToRightBinary(4, 13, 497);
        d.go();
    }
}
```

## 算法四：Matrices ##
>想办法在方法1的基础上减少递归次数，发现利用公式二可以做到。<br>
>(a*b) % c =  (a%c) * (b%c) % c【公式二】<br>
>当b是奇数的时候，f(b) = f(b-1) * (a % c) % c = a * f(b-1) % c<br>
>当b是偶数的时候，f(b) = f(b/2) * f(b/2) % c<br>

伪代码：
<blockquote>A recursive algorithm for ModExp(A, b, c) = Ab (mod c), where A is a square matrix.
<pre>
function Matrix_ModExp(Matrix A, int b, int c)
   if (b == 0):
         return I  // The identity matrix
   if (b mod 2 == 1):
         return (A * Matrix_ModExp(A, b - 1, c)) mod c 
   Matrix D := Matrix_ModExp(A, b / 2, c)
   return (D * D) mod c
</pre></blockquote>

c++代码，来自博客http://blog.csdn.net/delphiwcdj/article/details/5973026
```c++
#include <cstdio>  
#include <ctime>  
#include <cassert>  
// calculate a**b%c  
int fun(int a, int b, int c)  
{  
    assert(a && (b>=0) && c);  
    if (0==b)  
    {  
        return 1;  
    }   
    else  
    {  
        return (1==(b&1)) ? a*fun(a, b-1, c)%c : fun(a, b/2, c)*fun(a, b/2, c)%c;   
    }  
}  
int main()  
{     
    int a, b, c;  
    clock_t beg, end;  
    while (3==scanf("%d%d%d",&a,&b,&c))  
    {  
        beg=clock();  
        printf("%d/n",fun(a,b,c));// calculate a**b%c  
        end=clock();  
        printf("time used: %.2lf/n",double(end-beg)/CLOCKS_PER_SEC);  
    }  
    return 0;  
}  
```
我用Java实现的代码：
```java
package Algorithm.NumberTheory.ModularExponentiation;

/**
 * Created by king on 2017/6/11.
 * <p>
 * 利用矩阵快速幂
 * <p>
 * 递归实现
 * <p>
 * 公式：(a*b) % c = (a%c) * b % c
 */
public class Matrices {

    private int a;
    private int b;
    private int c;

    public Matrices(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int fun(int a, int b, int c) {
        if (b == 0) return 1;

        if ((b & 1) == 1) {//如果是奇数  b%2==1
            return fun(a, b - 1, c) * a % c;
        } else {       //如果是偶数  b%2==0
            return fun(a, b / 2, c) * fun(a, b / 2, c) % c;
        }
    }

    public void go() {
        int answer = fun(a, b, c);
        System.out.println(answer);
    }

    //测试
    public static void main(String[] args) {
        Matrices m = new Matrices(4, 13, 497);
        m.go();
    }
}

```

## 算法五：利用快速乘优化快速幂 ##
我的Java代码实现如下：
```java
package Algorithm.NumberTheory.ModularExponentiation;

/**
 * Created by king on 2017/6/11.
 *
 * 用快速幂求模幂 ， 再用快速乘法优化。
 */
public class RightToLeftBinary2 {
    private int a;
    private int b;
    private int c;

    public RightToLeftBinary2(int a,int b,int c){
        this.a=a;
        this.b=b;
        this.c=c;
    }

    //快速乘
    public int quick_mul(int m1,int m2,int c){//  m1 * m2
        int res=0;
        while (m2>0){
            if((m2&1)==1)res=(res+m1)%c;
            m2>>=1;
            m1=(m1+m1)%c;
        }
        return res;
    }

    //快速幂
    public int quick_pow(){
        int r=1;
        while (b>0){
            if((b&1)==1)r= quick_mul(r,a,c);//(r * a) % c
            b>>=1;
            a=(a*a)%c;
        }
        return r;
    }

    public void go(){
        int answer=quick_pow();
        System.out.println(answer);
    }

    //测试
    public static void main(String[] args) {
        RightToLeftBinary2 d=new RightToLeftBinary2(4,13,497);
        d.go();
    }
}

```