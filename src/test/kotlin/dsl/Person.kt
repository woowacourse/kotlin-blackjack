package dsl

data class Person(
    val name: String,
    val mbti: String,
    val cats: List<Cat>,
    val hobbies: List<String>,
    val skills: List<Skill>
)
