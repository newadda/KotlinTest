package org.onecell

import kotlin.reflect.KProperty

class Delegation {
}

interface RemoteController{
    fun upVolume():Unit
    fun downVolume():Unit
}

class SamsungRC:RemoteController{
    override fun upVolume() {
        println("삼성 볼륨업")
    }

    override fun downVolume() {
        println("삼성 볼륨다운")
    }


}

class ProxyRC(private val originalRC:RemoteController):RemoteController by originalRC{
    override fun upVolume() {
        println("Proxy 볼륨업")
       this.originalRC.upVolume()
    }

    operator fun getValue(nothing: Nothing?, property: KProperty<*>): RemoteController {
        println(property)
        return this
    }
}


class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

class Example {
    var p: String by Delegate()
}

fun main(){
    /// class delegation
    val samsungRC=SamsungRC()
    val proxyRC=ProxyRC(samsungRC)

    proxyRC.upVolume();
    proxyRC.downVolume()

    /// Delegated Properties
    val ex:Example= Example()
    println(ex.p)
    ex.p="zzz";

}
