package dsl

import java.lang.StringBuilder

fun introduce(f: () -> Unit) = StringBuilder().apply {
    f()
}

fun skills(f: () -> Unit) {
    println("\nMy Skills \n-------------")
    f()
}

fun languages(f: () -> Unit) {
    println("\nI Speak \n-------------")
    f()
}

fun name(name: String) {
    val value = "\nmy name is $name"
    println(value)
}

fun company(company: String) {
    val value = "\nI'm working at $company"
    println(value)
}

fun soft(skill: String) {
    val value = "soft skill : $skill"
    println(value)
}

fun hard(skill: String) {
    val value = "hard skill : $skill"
    println(value)
}

infix fun String.level(level: Int) {
    val value = "$this by level $level"
    println(value)
}

fun main() {
    introduce {
        name("aaron")
        company("WoowaTechCourse")
        skills {
            soft ("A paasion for problem solving")
            soft ("Good communication skills")
            hard ("Kotlin")
        }
        languages {
            "Korean" level 5
            "English" level 3
        }
    }
}