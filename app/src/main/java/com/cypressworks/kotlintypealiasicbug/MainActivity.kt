package com.cypressworks.kotlintypealiasicbug

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val foo = Foo(Source())
        Toast.makeText(this, foo.bar("hello"), Toast.LENGTH_SHORT).show()
        textViewFoo.text = foo.bar("world")
    }

    class Source : Foo<String>.Source {
        override fun invoke(p1: String) = p1
    }

}
