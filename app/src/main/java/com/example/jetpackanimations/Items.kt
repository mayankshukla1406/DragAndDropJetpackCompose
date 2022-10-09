package com.example.jetpackanimations

import androidx.annotation.DrawableRes

data class Language(val id: Int, val name: String,val description: String, @DrawableRes val image: Int)

val languages = listOf(
    Language(1, "Java","Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.", R.drawable.java),
    Language(2, "C++","C++ is a general-purpose programming language created by Danish computer scientist Bjarne Stroustrup as an extension of the C programming language, or \"C with Classes", R.drawable.c),
    Language(3, "Python","Python is a high-level, general-purpose programming language. Its design philosophy emphasizes code readability with the use of significant indentation. Python is dynamically-typed and garbage-collected. It supports multiple programming paradigms, including structured, object-oriented and functional programming.", R.drawable.python),
)