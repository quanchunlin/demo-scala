/*
* Base Syntax
*   Scala is multi-paradigm Language
* */
/*
特性:
   面向对象:
     子类继承, 灵活的混入机制 这两种途径能避免多重继承的问题
   函数式编程:
     函数也能当成值来使用, 支持高阶函数, 嵌套多层函数, 支持柯里化
     case class及其内置的模式匹配为 代数类型
   静态类型:
     泛型类,协变和逆变,标注,类型参数的上下限约束,把类别和抽象类型作为对象成员
     复合类型,引用自己时显式指定类型,视图,多态方法
   并发性:
     Actor作为其并发模型,Akka为默认Actor实现,Actor是类似线程的实体，
     Actor可以复用线程，因此可以在程序中可以使用数百万个Actor,而线程只能创建数千个
* */
/*
  Scala可以调用Java类库
  语句末尾的分号通常是可选 可以省略号(;)
  Unit 和 java的 void 等同
  Nothing	 它是任何其他类型的子类型
  Any 是所有其他类的超类
  AnyRef 类是Scala里所有引用类(reference class)的基类
  Null 类是null引用对象的类型(继承自AnyRef的类). Null不兼容值类型
  关键词 "var" 声明变量，使用关键词 "val" 声明常量
  变量声明一定需要初始值，否则会报错
*/
/*
  标识符
    符号 $ 在 Scala 中也看作为字母 不过 程序应该避免使用"$"开始的标识符以免造成冲突
    Java 代码中访问[:->]方法, 需要使用 Scala 的内部名称 $colon$minus$greater
    想使用有效的Scala标识符 yield 比如Thread.yield()必须Thread.`yield`()这样加上``
* */
/*
1.定义包和Java一样
* */
package main.scala
/*
2.类似 C# - 可以在一个文件中定义多个包
  package com.runoob {
    class HelloWorld
  }
* */
/*
  import 关键字引用包
  默认引入(java.lang._,scala._,Predef._) scala开头的包使用时都是省去scala.
  {} selector(选取器): 指定几个需要的包
  _: 引入包内所有成员
  java.util.{HashMap => JavaHashMap}: 引用同时重命名成员
  java.util.{HashMap => _, _}: 引入了util包的所有成员，但是隐藏HashMap
* */
import java.io.{FileNotFoundException, FileReader,
                  IOException, PrintWriter}
import java.util.Date//引入Date
import scala.util.matching.Regex//正则包
import java.io._
import scala.io.Source

object HelloWorld {
  def main(args: Array[String]) {

    /************************************************数据类型*/
/*
  数据类型都是对象,scala没有java中的原生类型 scala是可以对数字等基础类型调用方法
  字面量:
      整型字面量 Int类型,表示Long后面添加 L 或者小写 l: 77L,77l
    浮点型字面量:
      浮点数后面有f或者F后缀时，表示这是一个Float类型，否则就是一个Double类型:3.14159f
    布尔型字面量: true 和 false
    字符字面量: 半角单引号(')中的字符 'a', '\u0041', '\n', '\t' 其中[\]表示转移字符
    字符串字面量: 字符串表示方法是在双引号中(")
  Null 值:Scala.Null和scala.Nothing是处理Scala面向对象类型系统的某些特殊类型
          Null 类是null引用对象的类型(继承自AnyRef的类). Null不兼容值类型
* */
//    数据类型	描述
//    Byte	  8位有符号补码整数。数值区间为 -128 到 127
//    Short	  16位有符号补码整数。数值区间为 -32768 到 32767
//    Int	    32位有符号补码整数。数值区间为 -2147483648 到 2147483647
//    Long	  64位有符号补码整数。数值区间为 -9223372036854775808 到 9223372036854775807
//    Float	  32位IEEE754单精度浮点数
//    Double	64位IEEE754单精度浮点数
//    Char	  16位无符号Unicode字符, 区间值为 U+0000 到 U+FFFF
//    String	字符序列
//    Boolean	true或false
//    Unit	  表示无值，和其他语言中void等同。用作不返回任何结果的方法的结果类型。Unit只有一个实例值，写成()。
//    Null	  null 或空引用
//    Nothing	Nothing类型在Scala的类层级的最低端；它是任何其他类型的子类型。
//    Any	    Any是所有其他类的超类
//    AnyRef	AnyRef类是Scala里所有引用类(reference class)的基类
/*
* 变量声明一定需要初始值，否则会报错
* */
    //声明String变量
    var vr_1:String = "Shawn"
    println("String变量 vr_1: " + vr_1)//变量 vr_1: Shawn
    //声明推断类型
    val vl_2 = 10
    println("推断类型常量 vl_2: " + vl_2)//推断类型常量 vl_2: 10
    //多个变量的声明
    val xmax, ymax = 99//xmax,ymax都声明为99
    println("xmax: " + xmax + ", ymax: " + ymax)//xmax: 99, ymax: 99
    //定义元组
    var pa = (40, "Foo")
    println("pa：" + pa)//pa：(40,Foo)
    //多行字符串
    var vr_2 =
      """ 多行字符串,
        用三个双引号来表示分隔符 """.stripMargin
    println("多行字符串 vr_2: " + vr_2)
//    多行字符串 vr_2:  多行字符串,
//    用三个双引号来表示分隔符

    /************************************************运算符*/
/*
位运算符: 位运算符用来对二进制位进行操作
&	按位与运算符	  (a & b) 输出结果 12 ，二进制解释： 0000 1100
|	按位或运算符	  (a | b) 输出结果 61 ，二进制解释： 0011 1101
^	按位异或运算符	(a ^ b) 输出结果 49 ，二进制解释： 0011 0001
~	按位取反运算符	(~a )   输出结果 -61 ，二进制解释： 1100 0011，
  在一个有符号二进制数的补码形式。
<<	左移动运算符	a << 2  输出结果 240 ，二进制解释： 1111 0000
>>	右移动运算符	a >> 2  输出结果 15 ，二进制解释： 0000 1111
>>>	无符号右移	  A >>>2  输出结果 15, 二进制解释: 0000 1111
*/
    val vr_3 = 60 //对应的二进制 0011 1100
    val vr_4 = 13 //对应的二进制 0000 1101
    println("60 & 13 = " + (vr_3 & vr_4) )//60 & 13 = 12
    println("60 | 13 = " + (vr_3 | vr_4) )//60 | 13 = 61
    println("60 ^ 13 = " + (vr_3 ^ vr_4) )//60 ^ 13 = 49
    println("~60 = " + (~vr_3) )//~60 = -61
    println("60 << 2 = " + (vr_3 << 2) )//60 << 2 = 240
    println("60 >> 2 = " + (vr_3 >> 2) )//60 >> 2 = 15
    println("60 >>> 2 = " + (vr_3 >>> 2) )//60 >>> 2 = 15

    /************************************************循环*/
    //break 语句
    import scala.util.control._
    var a = 0;
    val numList = List(1,2,3,4,5,6,7,8,9,10);
    val loop = new Breaks;
    loop.breakable {
      for( a <- numList){
        println( "循环 Value of a: " + a );
        if( a == 4 ){
          loop.break;
        }
      }
    }
//    循环 Value of a: 1
//    循环 Value of a: 2
//    循环 Value of a: 3
//    循环 Value of a: 4
    /************************************************方法与函数*/
/*
val 语句可以定义函数，def 语句定义方法
函数名可以有以下特殊字符：+, ++, ~, &,-, -- , \, /, : 等
可以 函数内定义函数（内嵌函数）,匿名函数
不写等于号和方法主体，那么方法会被隐式声明为 抽象(abstract),包含它的类型于是也是一个抽象类型
  val f = (x: Int) => x + 3
  def addInt( a:Int, b:Int ) : Int 返回类型 如 没有返回就Unit = { 主体
      var sum:Int = 0
      sum = a + b
      return sum
   }
  函数参数:
    传值调用(call-by-value)先计算参数表达式的值，再应用到函数内部(普通的有参函数)
    函数传名调用(call-by-name)将未计算的参数表达式直接应用到函数内部

* */
    //函数内部进行参数表达式的值计算 Call by name
    println("函数内部进行参数表达式的值计算 delayed(time): " + delayed(time))
    //在 delayed 方法内
    //获取时间，单位为纳秒
    //参数： 84490940786984
    //函数内部进行参数表达式的值计算 delayed(time): ()

    //指定函数参数名
    println("指定函数参数名 appointArgs(b = 5, a = 7)：" + appointArgs(b = 5, a = 7))
    //指定函数参数名 a + b: 12
    //指定函数参数名 appointArgs(b = 5, a = 7)：()

    //函数 - 可变参数
    println("函数(可变参数) variableArgs(args:String* )：" + variableArgs("m1", "x2", "d3"))
    //可变参数 Arg value[0] = m1
    //可变参数 Arg value[1] = x2
    //可变参数 Arg value[2] = d3
    //函数(可变参数) variableArgs(args:String* )：()

    //递归函数
    for (i <- 1 to 4)
      println(i + " 的阶乘为: = " + recursion(i) )
    //1 的阶乘为: = 1
    //2 的阶乘为: = 2
    //3 的阶乘为: = 6
    //4 的阶乘为: = 24

    //默认参数值
    println("默认参数值 defaultArgs：" + defaultArgs(10))//默认参数值 defaultArgs：17
    //高阶函数（Higher-Order Function）就是操作其他函数的函数
    println("高阶函数 higherOrderFunction：" + higherOrderFunction( layout, 5) )//高阶函数 higherOrderFunction：[5]
    //函数嵌套
    println("函数嵌套 factorial(0): " + factorial(0) )//函数嵌套 factorial(0): 1
    println("函数嵌套 factorial(2): " + factorial(2) )//函数嵌套 factorial(2): 2

    //Lambda 匿名函数
    var anonymousFunc = (x:Int) => x + 8;
    /*
    def anonymousFunc = new Function1[Int,Int]{//上述定义的匿名函数是下面这种写法的简写：
        def apply(x:Int):Int = x + 8;
    }
    * */
    println("anonymousFunc：" + (anonymousFunc(6) + 2))//16
    //无参 Lambda 匿名函数
    var noArgsAnonymousFunc = () => { System.getProperty("user.dir") }
    println("noArgsAnonymousFunc：" + noArgsAnonymousFunc())//noArgsAnonymousFunc：D:\xampp\htdocs\java\shawn\demo-scala

    //偏应用函数是一种表达式，你不需要提供函数需要的所有参数，提供部分，或不提供所需参数
    val date = new Date
    val logWithDateBound = log(date, _:String)//参数使用下划线(_)替换缺失的参数
    logWithDateBound("偏应用函数 value1" )//log(date, _:String): Sat Jun 02 19:44:15 CST 2018----偏应用函数 value1
    logWithDateBound("偏应用函数 value2")//log(date, _:String): Sat Jun 02 19:44:15 CST 2018----偏应用函数 value2
    logWithDateBound("偏应用函数 value3" )//log(date, _:String): Sat Jun 02 19:44:15 CST 2018----偏应用函数 value3

    //函数柯里化(Currying)
    val str1:String = "Hello, "
    val str2:String = "Scala!"
    println("函数柯里化 str1 + str2 = " +  strcat(str1)(str2) )//函数柯里化 str1 + str2 = Hello, Scala!
    val curryingFunctionResult = curryingFunction(3)
    println("函数柯里化 curryingFunction(x:Int)(y:Int): " +  curryingFunctionResult(5) )//curryingFunction(x:Int)(y:Int) :8
/*
* 闭包:
*   闭包是一个函数，返回值依赖于声明在函数外部的一个或多个变量 简单的认为是可以访问一个函数里面局部变量的另外一个函数
* */
    println("闭包 closuresFunction: " +  closuresFunction(9) )//闭包 closuresFunction: 27

    /************************************************字符串*/
    //StringBuilder字符串
    val buf = new StringBuilder;
    buf += 'a'
    buf ++= "bcdef"
    println( "StringBuilder : " + buf.toString + ",length():" + buf.length());//StringBuilder : abcdef,length():6
    //Concat字符串链接, 也可以使用加号(+)来连接
    var str3 = "abcdec";
    var str4 = "|aaa";
    println( "String Concat : " + str3.concat(str4));//String Concat : abcdec|aaa
    var floatVar = 12.456f
    var intVar = 9999
    var stringVar = "春林"
    var fs = printf("浮点型变量为：%f, 整型变量为：%d, 字符串为：%s", floatVar, intVar, stringVar)
    println(fs)//printf 浮点型变量为：12.456000, 整型变量为：9999, 字符串为：春林()
    println("charAt: " + str3.charAt(2))//charAt: c
    var str5 = "b";
    var str6 = "B";
    //compareTo 相同0，大>0，小<0
    println("compareTo: " + str5.compareTo(str6))//compareTo: 32
    println("compareToIgnoreCase: " + str5.compareToIgnoreCase(str6))//compareToIgnoreCase: 0
    //endsWith是否以指定的后缀结束
    println("endsWith: " + str3.endsWith("cde"))//endsWith: false
    //返回此字符串的哈希码
    println("hashCode: " + str5.hashCode())//hashCode: 98
    //indexOf返回指定字符在此字符串中第一次出现处的索引
    println("indexOf: " + str3.indexOf("c"))//indexOf: 2
    println("indexOf: " + str3.indexOf("c",3))//indexOf: 5

    /************************************************Array 数组*/
    //三种定义一维数组方式
    var arr1:Array[String] = new Array[String](3)
    var arr2 = new Array[String](3)
    arr2(0) = "aaa";
    arr2(1) = "bbb";
    arr2(4/2) = "ccc"
    var arr3 = Array("aaa", "bbb", "ccc")
    for ( x <- arr3 ) {
      println( x )
    }
    //aaa
    //bbb
    //ccc

    var arr4 = Array(1.9, 2.9, 3.4, 3.5, 2)
    var max = arr4(0);
    for ( i <- 1 to (arr4.length - 1) ) {//1为初始值 然后 递增
      if (arr4(i) > max) max = arr4(i);
    }
    println("最大值为 " + max);//最大值为 3.5

    import Array._ //引用所有数组方法 ofDim

    //多维数组 - ofDim 构造一个三行三列(创建三维数组)
    var myMatrix = ofDim[Int](3,3)
    //创建矩阵
    for (i <- 0 to 2) {
      for ( j <- 0 to 2) {
        myMatrix(i)(j) = j;
      }
    }
    for (i <- 0 to 2) {
      for ( j <- 0 to 2) {
        println("多维数组 ofDim: " + myMatrix(i)(j));
      }
    }
    //多维数组 ofDim: 0
    //多维数组 ofDim: 1
    //多维数组 ofDim: 2
    //多维数组 ofDim: 0
    //多维数组 ofDim: 1
    //多维数组 ofDim: 2
    //多维数组 ofDim: 0
    //多维数组 ofDim: 1
    //多维数组 ofDim: 2

    //合并数组
    var arr5 = Array(1.9, 2.9, 3.4, 3.5)
    var arr6 = Array(8.9, 3.5)
    var arr7 =  concat( arr5, arr6)
    for (x <- arr7 ) {
      println("合并数组 concat: " + x )
    }
    //合并数组 concat: 1.9
    //合并数组 concat: 2.9
    //合并数组 concat: 3.4
    //合并数组 concat: 3.5
    //合并数组 concat: 8.9
    //合并数组 concat: 3.5

    //创建区间数组 - range() 方法最后一个参数为步长，默认为 1
    var myList1 = range(10, 20, 2)
    var myList2 = range(10, 20)
    for ( x <- myList1 ) {
      print( " " + x )
    };println()
    //10 12 14 16 18
    for ( x <- myList2 ) {
      print( " " + x )
    };println()
    //10 11 12 13 14 15 16 17 18 19

    //Scala 集合分为可变的和不可变的集合
    //定义整型 List
    val d1 = List(1,2,3,4)
    //定义 Set
    val d2 = Set(1,3,5,7)
    //定义 Map
    val d3 = Map("one" -> 1, "two" -> 2, "three" -> 3)
    for ( x <- d3 ) {
      print( "集合: " + x )
    };println()
    //集合: (one,1)集合: (two,2)集合: (three,3)

    //创建两个不同类型元素的元组, 元组是不同类型的值的集合
    val d4 = (10, "ten")
    //定义 Option, Option[T] 表示有可能包含值的容器，也可能不包含值
    val d5:Option[Int] = Some(5)

    //Iterator（迭代器）
    val it1 = Iterator("a1", "a2", "a3", "a4")
    while (it1.hasNext){
      println("迭代器 Iterator:" + it1.next())
    }
    //迭代器 Iterator:a1
    //迭代器 Iterator:a2
    //迭代器 Iterator:a3
    //迭代器 Iterator:a4

    val it2 = Iterator(20,40,2,50,69,90)
    val it3 = Iterator(20,40,2,50,69,90)
    val it4 = Iterator(20,40,2,90)
    val it5 = Iterator(20,40,2,50,69,90)
    println("最大元素是：" + it2.max )//90
    println("最小元素是：" + it3.min )//2
    println("it4.size 的值: " + it4.size )//4 和length相同
    println("it5.length 的值: " + it5.length )//6 和size相同

    /************************************************Class 类*/
/*
    1.只有主构造函数才可以往基类的构造函数里写参数
    2.在子类中重写超类的抽象方法时，你不需要使用override关键字
    3.Scala 只允许继承一个父类
    4.Scala 没有 static 关键字，但是它也为我们提供了单例模式的实现方法，那就是使用关键字 object
    5.object和类的区别是，object对象不能带参数
    当单例对象与某个类共享同一个名称时，称作是这个类的伴生对象：companion object。
    你必须在同一个源文件里定义类和它的伴生对象。类被称为是这个单例对象的伴生类：companion class。
    类和它的伴生对象可以互相访问其私有成员
        私有构造方法
    访问修饰符:
      保护（Protected）成员的访问比java更严格.因为它只允许保护成员在定义了该成员的的类的子类中
            被访问.而在java中protected除了定义了该成员的类的子类可以访问,同一个包里的其他类也
            可以进行访问
      没有指定任何的修饰符，则默认为 public。这样的成员在任何地方都可以被访问
    作用域保护: private[x], protected[x]
      x指代某个所属的包、类或单例对象。如果写成private[x],读作"这个成员除了对[…]中的类
      或[…]中的包中的类及它们的伴生对像可见外，对其它所有类都是private
* */
    val pt = new Point(10, 20)
    pt.move(10, 10)
    //x 的坐标点: 20
    //y 的坐标点: 30
    printPoint
    //printPoint x 的坐标点 : 20
    //printPoint y 的坐标点 : 30
    def printPoint {//方法无参可以没有括号 == printPoint()
      println("printPoint x 的坐标点 : " + pt.x)
      println("printPoint y 的坐标点 : " + pt.y)
    }

    val chunlin = new Shawn(33, "chunlin")
    chunlin.salary = 50000
    println(chunlin.age)//33
    println(chunlin.name)//chunlin
    println(chunlin.salary)//50000.0
    println(chunlin.toString)//main.scala.HelloWorld$Shawn[name=chunlin][salary=50000.0]

    //使用trait抽象类的 方法的实现(isNotEqual) 和 抽象方法的子类实现(isEqual)
    val p1 = new Point(2, 3)
    val p2 = new Point(2, 4)
    val p3 = new Point(3, 3)
    println(p1.isNotEqual(p2))//false
    println(p1.isEqual(p2))//true   x 2 == x 2
    println(p1.isEqual(p3))//false  x 2 != x 3
    println(p1.isNotEqual(2))//true 不是同一个类实现

    /************************************************Match, 样例类*/
/*
    模式匹配 match 对应 Java 里的 switch
    一个匹配的case，剩下的case不会继续匹配
    按顺序匹配, 没有完全相同的是 数据类型优先与_
* */
    println(matchTest("two"))//2
    println(matchTest("test"))//many
    println(matchTest(1))//one
    println(matchTest(999))//scala.Int

/*
    样例类 模式匹配 - case class TestCaseClass(name: String, age: Int)
    声明样例类时，下面的过程自动发生
    构造器的每个参数都成为val，除非显式被声明为var，但是并不推荐这么做
    在伴生对象中提供了apply方法，所以可以不使用new关键字就可构建对象
    提供unapply方法使模式匹配可以工作
    生成toString、equals、hashCode和copy方法，除非显示给出这些方法的定义
* */
    val alice = new TestCaseClass("Alice", 25)
    val bob = TestCaseClass("Bob", 32)//样例类 new 可选
    val charlie = new TestCaseClass("Charlie", 32)
    for (person <- List(alice, bob, charlie)) {
      person match {
        case TestCaseClass("Alice", 25) => println("Hi Alice!")
        case TestCaseClass("Bob", 32) => println("Hi Bob!")
        case TestCaseClass(name, age) =>
          println("Age: " + age + " year, name: " + name + "?")
      }
    }
    //Hi Alice!
    //Hi Bob!
    //Age: 32 year, name: Charlie?

    /************************************************Regular Expression, 正则表达式*/
    import scala.util.matching.Regex//正则包

    //正则表达式 使用正则表达式查找单词 Scala
    //findFirstIn 首个匹配项
    //findAllIn 所有的匹配项
    //mkString( ) 方法来连接正则表达式匹配结果的字符串
    //(|)来设置不同的模式
    val pattern1 = "Scala".r
    val testStr1 = "Scala is Scalable and cool"
    println(pattern1 findFirstIn testStr1)//Some(Scala)
    val pattern2 = new Regex("(S|s)cala")  // 首字母可以是大写 S 或小写 s
    val testStr2 = "Scala is scalable and cool"
    println((pattern2 findAllIn testStr2).mkString(","))//使用逗号,连接返回结果-> Scala,scala
    val pattern3 = "(S|s)cala".r
    val testStr3 = "Scala is scalable and cool"
    println(pattern3 replaceFirstIn(testStr3, "Java"))//Java is scalable and cool
    println(pattern3 replaceAllIn(testStr3, "Java"))//Java is Javable and cool
    val pattern4 = new Regex("abl[ae]\\d+")
    val testStr4 = "ablaw is able1 and cool"
    println((pattern4 findAllIn testStr4).mkString(","))//able1

    /************************************************抛出异常, 捕获异常*/
    //抛出异常,捕获异常 基本和其他语言相同
    //throw new IllegalArgumentException
    try {
      val f = new FileReader("input.txt")
    } catch {
      case e: FileNotFoundException =>{
        println("Missing file exception")//v
      } case e: IOException => {
        println("IO Exception")
      } case e: Throwable => {
        println("Throwable")
      }
    } finally {
      println("Exiting finally...")
    }

    /************************************************提取器(Extractor)*/
    //象定义了两个方法： apply 和 unapply 方法。通过 apply 方法我们无需使用 new 操作就可以创建对象
    //构造 字符串对象 Zara@gmail.com
    //可以有0个或者多个的参数
    println ("Apply 方法 : " + apply2("Zara", "gmail.com"))//Apply 方法 : Zara@gmail.com
    //查询 字符串对象 Zara@gmail.com
    println ("Unapply 方法 : " + unapply2("Zara@gmail.com"))//Unapply 方法 : Some((Zara,gmail.com))
    //未查到 输出 None
    println ("Unapply 方法 : " + unapply2("Zara Ali"))//Unapply 方法 : None

    /************************************************文件I/O*/
    val writer = new PrintWriter(new File("test.txt" ))
    writer.write("语法测试1\n语法测试2")
    writer.close()
    //从文件上读取内容
    println("文件内容为:" )
    Source.fromFile("test.txt" ).foreach{
      print
    }
    //文件内容为:
    //语法测试1
    //语法测试2

  }

  /************************************************Scala方法*/
  //注入方法 (可选)
  def apply2(user: String, domain: String) = {
    user +"@"+ domain
  }
  //提取方法（必选）
  def unapply2(str: String): Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2){
      Some(parts(0), parts(1))
    }else{
      None
    }
  }
  //样例类
  case class TestCaseClass(name: String, age: Int)
  //模式匹配 match
  def matchTest(x: Any): Any = x match {
    case 1 => "one"
    case "two" => 2
    case aa: Int => "scala.Int"//相比isInstanceOf来判断类型
    case _ => "many" //_为java switch的default
  }
  //trait 抽象类
  trait Equal {
    def isEqual(x: Any): Boolean
    def isNotEqual(x: Any): Boolean = !isEqual(x)
  }
  //主类
  class Point(xc: Int, yc: Int) extends Equal {
    var x: Int = xc
    var y: Int = yc
    def isEqual(obj: Any) = obj.isInstanceOf[Point] && obj.asInstanceOf[Point].x == x
    def move(dx: Int, dy: Int) {
      x = x + dx
      y = y + dy
      println ("x 的坐标点: " + x);
      println ("y 的坐标点: " + y);
    }
  }//继承类
  class Person(val age : Int){
    val name = "no name"
    override def toString = getClass.getName + "[name=" + name + "]"
  }
  class Shawn(age: Int, override val name: String) extends Person(age){
    var salary = 0.0
    override def toString = super.toString + "[salary=" + salary + "]"
  }
  //闭包 - 简单的认为是可以访问一个函数里面局部变量的另外一个函数
  var factor = 3
  val closuresFunction = (i:Int) => i * factor
  //函数柯里化(Currying)
  def strcat(s1: String)(s2: String) : String = {
    s1 + s2
  }
  def curryingFunction(x:Int)= (y:Int) => x + y

  def log(date: Date, message: String)  = {
    println("log(date, _:String): " + date + "----" + message)
  }
  def factorial(i: Int): Int = {//函数嵌套
    def fact(i: Int, accumulator: Int): Int = {
      if (i <= 1)
        accumulator
      else
        fact(i - 1, i * accumulator)
    }
    fact(i, 1)
  }
  //高阶函数（Higher-Order Function）就是操作其他函数的函数
  //函数 f 和 值 v 作为参数，而函数 f 又调用了参数 v
  def higherOrderFunction(f: Int => String, v: Int) = f(v)
  def layout[A](x: A) = "[" + x.toString() + "]"

  def defaultArgs( a:Int = 5, b:Int = 7 ) : Int = {//默认参数值
    var sum:Int = 0
    sum = a + b
    return sum
  }
  def recursion(n: BigInt): BigInt = {//递归函数
    if (n <= 1)
      1
    else
      n * recursion(n - 1)
  }
  def variableArgs(args:String* ) = {//函数(可变参数) 参数等价String数组
    var i:Int = 0;
    for( arg <- args ){
      println("可变参数 Arg value[" + i + "] = " + arg );
      i = i + 1;
    }
  }
  def appointArgs( a:Int, b:Int ) = {
    println("指定函数参数名 a + b: " + (a + b));
  }
  def time() = {
    println("获取时间，单位为纳秒")
    System.nanoTime
  }
  def delayed( t: => Long ) = {//call-by-name
    println("在 delayed 方法内")
    println("参数： " + t)//此处执行同时 获取返回值输出
  }

}
