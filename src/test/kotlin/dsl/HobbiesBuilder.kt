package dsl

class HobbiesBuilder {
    private val hobbies = mutableListOf<String>()

    operator fun String.unaryPlus() {
        hobbies.add(this)
    }

    fun build(): List<String> = hobbies
}