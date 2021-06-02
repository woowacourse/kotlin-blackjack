package dsl

class Skills(private val skills: MutableList<String> = mutableListOf()) : List<String> by skills {

    fun soft(soft: String) {
        this.skills.add(soft)
    }

    fun hard(hard: String) {
        this.skills.add(hard)
    }
}
