package org.onecell

sealed class Animal


object Human:Animal()
object Tiger:Animal()
object Rabbit:Animal()
data class Const(val number: Double) : Animal()


fun main(){
    val test : Animal = Const(11.2)
    when(test){
         Human->{
        println("Human")
        }
         Tiger->{
            println("Tiger")
        }
        is Const->{
            println("Tiger")
        }
    }
}