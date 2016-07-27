package com.cypressworks.kotlintypealiasicbug

class Foo<T>(val source: Source) {
    typealias Source = (String) -> T

    fun bar(s: String) = source(s)
}

