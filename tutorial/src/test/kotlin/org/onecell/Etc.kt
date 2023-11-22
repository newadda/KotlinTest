package org.onecell

/**
 * object 키워드
 */

/// object 는 싱글톤이다.
/// 실제는 클래스가 구현됨과 동시에 할당 된 인스턴스를 말한다.
object ObjectTest {
    fun test(){
        println("ObjectTest->test()")
    }
}

interface Instrument {
    fun play()
}


/**
 * companion 키워드
 */
//// java 의 static 구현
class StTest{
    companion object {
        @JvmStatic /// java 에서 static 으로 사용하기 위한 어노테이션
        var shape = "Rounded Rectangle"

        fun draw(){
            println("draw")
        }
    }
}

/*
위의 StTest 에서 @JvmStatic 어노테이션이 없다면 아래와 같이 java 코드로 변환된다.
즉, static 클래스의 getter, setter로 구현된다.
public final class StTest {
   private static String shape;
   public static final class Companion {
      public final String getShape() {
         return StTest.shape;
      }
      public final void setShape(String var1) {
         StTest.shape = var1;
      }
   }
}

 */


fun main()
{
    ///// object 키워드
    // val = ObjectTest() //error, 이미 인스턴스 이기 때문에 되지 않는다.
    val a = ObjectTest;
    a.test()

    val b=object{} //무명객체

    /// 무명객체 구현
    val c=object:Instrument{
        override fun play() {
            println("play")
        }
    }

    //// companion 키워드
    val d=StTest::shape
    println(d) // 출력Rounded Rectangle

}