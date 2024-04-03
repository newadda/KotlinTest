package org.onecell

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce
import kotlin.coroutines.CoroutineContext

class 코루틴 {

    suspend fun delayPrint(delayTime:Long=5000,text:String="테스트")
    {
        delay(delayTime);
        println(text)
    }

    /// 코루틴 스코프 상속 개념
    fun test01(){
        val async = GlobalScope.async {
            GlobalScope.async(context=this.coroutineContext){
                for (i:Int in 1..99)
                {
                    println("상속받은 코루틴 스코프")
                    delay(5000);
                }
            }

            GlobalScope.async{
                for (i:Int in 1..99)
                {
                    println("상속받지 않은 코루틴 스코프")
                    delay(5000);
                }
            }
        }

        runBlocking {
            delay(10000)
        }


        println("작업취소")
        async.cancel()
        runBlocking {
            delay(10000)
        }
    }

}

fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    while (true) send(x++) // infinite stream of integers starting from 1
}

fun main(args:Array<String>){
    val a= 코루틴()
   // a.test01()

    runBlocking{
        launch(){

            launch() {
                delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
                println("heoool!") // print after delay
            }
            println("World!") // print after delay


        }
        println("heoool1!") // print after delay
    }
    println("heoool2!") // print after delay
    val dis = newSingleThreadContext("1")

   var  l =GlobalScope.async(dis) {
        println("GlobalScope.async")
        a.delayPrint(1000)

        a.delayPrint(200,"test")

        async() {
            delay(2000)
            println("done")
        }

        async() { println("done!!") }
    }

    println("runBlocking")
    runBlocking (dis){
        println("await")
        l.await()
        println("done!!!!!!!")
    }



    /// ==========================================================
    /// ============= 예외 핸들러 처리
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    /// launch 는 결과를 반환하지 않는다. 따라서 예외 핸들러가 동작한다.
    val job = GlobalScope.launch(handler) { // root coroutine, running in GlobalScope
        throw AssertionError()
    }

    /// async 는 결과를 반환한다. 즉, 예외 또한 반환하게 된다. 따라서 예외 핸들러는 동작하지 않는다.
    val deferred = GlobalScope.async(handler) { // also root, but async instead of launch
        throw ArithmeticException() // Nothing will be printed, relying on user to call deferred.await()
    }

    runBlocking {
        /// deferred 의 경우 await()를 통해 결과를 반환한다. 결과는 예외도 포함한다.
        try {
            deferred.await()
        }catch (e:Exception)
        {
            println("try-catch $e")
        }
        joinAll(job, deferred)

    }



    /// ==========================================================
    /// ============= 코루틴 cancel()
    /// cancel() 여부는 delay() 함수나 isActive 를 통해 알 수 있다.
    /// delay() 호출전이나 호출중에 cancel() 발생시 CancellationException() 이 발생한다.
    val block: suspend CoroutineScope.() -> Unit = {
        println("start")
        delay(1000)
        println("end")
        throw Exception("asdfasdfasdf")
    }

    val async =GlobalScope.launch (handler){
        try {
            block();
        }catch (e:Exception)
        {
            println(e)
            e.printStackTrace()
            throw e;

        }
    }
    println("cancel")
    async.cancel(CancellationException("aaaa"))


    /// isActive 확인
    val async2 =GlobalScope.launch (
        CoroutineExceptionHandler { _, exception ->
            println("catce got $exception")
        }
    ){
        println("start")
        delay(1000)
        println("isActive : $isActive") /// isActive :true 가 출력된다.
        cancel(CancellationException("bbbbb"))

        println("isActive : $isActive") /// isActive : false 가 출력된다.
    }




    runBlocking {
        async2.join()
        delay(3000)
    }



}