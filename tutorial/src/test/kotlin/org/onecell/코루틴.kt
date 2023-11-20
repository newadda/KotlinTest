package org.onecell

import javafx.application.Application
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce

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


    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable)
        throwable.printStackTrace()

    }

     GlobalScope.async  (coroutineExceptionHandler) {
        println("start")
        delay(1000)
        println("end")
        throw Exception("asdfasdfasdf")
    }

    val block: suspend CoroutineScope.() -> Unit = {
        println("start")
        delay(1000)
        println("end")
        throw Exception("asdfasdfasdf")

    }

    val async =GlobalScope.launch (coroutineExceptionHandler){
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
    runBlocking {
        delay(3000)
    }


}