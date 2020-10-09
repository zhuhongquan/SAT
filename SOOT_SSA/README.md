# SOOT_SSA

## 作业要求
> - 采用你喜欢的任意程序分析框架（如LLVM、SOOT等）  
> - 构造一个无法完全转换成SSA的程序，用所选框架转换成部分SSA
> - 提交原始代码和转换后的代码，并说明哪些地方没有做到赋值后内存位置不变
> - 在soulike网站上提交，放在每个人的目录下，把链接私信给助教

## 准备工作
**拟采用SOOT框架对JAVA程序进行分析**
#### 官方文档
* “A Survivor's Guide to Java Program Analysis with Soot”: http://www.brics.dk/SootGuide/
* Soot command-line options：https://soot-build.cs.uni-paderborn.de/public/origin/develop/soot/soot-develop/options/soot_options.htm
* 官方教程： https://github.com/Sable/soot/wiki/Tutorials
#### 工具
* soot-4.2.1-jar-with-dependencies.jar

## 实验
1. 编写JAVA程序Test1.java，并编译成字节码文件。
2. 使用命令行对.class文件进行转换，将输出类型指定为Jimple，基本命令如下：
```bash
java -cp soot.jar soot.Main -cp . -pp Test1 -f J
```
3. SOOT会在当前目录的sootOutput文件夹内生成Test1.jimple文件。

## 分析
为了进行对比，设计了两段代码。
```bash
public class Test1{
    public static void main(String args[]) {
        A a = new A();  //堆上的变量
        A b = new A();
        a.f=10;
        b = a;
        b.f = 20;  //赋值后内存位置改变，未优化
        System.out.println(a.f);
		
        int n1=1;  //栈上的变量
        int n2=2;
        n2=n1*10;  //赋值后内存位置不变，优化
        System.out.println(n2);
    }
}

class A{
    int f;
}
```
JAVA中的优化原则是“栈上的变量为优化组，堆上的变量为不优化组”。代码中，变量a, b 均为类实例，为堆上的变量，在jimple中以$开头的变量来命名，不会对其进行优化；第二段代码中的n1, n2均为局部变量，且为基础类型int，所以会被分配到栈上，所以在jimple码中对第二段代码进行了优化。
