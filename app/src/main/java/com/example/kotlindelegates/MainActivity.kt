package com.example.kotlindelegates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MainActivity : AppCompatActivity(),LifecycleLogger /**Here is Delegation*/by LifecyclerLoggerImplementation() /** Here is Delegation*/ {
    //Property Delegated
    private val myVariable by lazy {
        //Bu değişkene ulaşmadan bu değişken hafızada yer TUTMAZ.
        println("hello this is a lazy implementation")
        10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerLifecycleOwner(this)
        println(myVariable)
    }
}

interface LifecycleLogger {
    fun registerLifecycleOwner(owner: LifecycleOwner)
}

class LifecyclerLoggerImplementation : LifecycleLogger, LifecycleEventObserver {
    override fun registerLifecycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_RESUME -> println("OnResume called")
            Lifecycle.Event.ON_CREATE -> println("OnCreate called")
            Lifecycle.Event.ON_DESTROY -> println("OnDestroy called")
            Lifecycle.Event.ON_PAUSE -> println("OnPause called")
            else ->Unit
        }

    }

}