package dsl

// Backing properties
class Skills(skills: List<String> = mutableListOf()) : List<String> by skills {
    private val _skills: MutableList<String> = skills.toMutableList()
    val skills: List<String>
        get() = _skills.toList()

    fun soft(soft: String) {
        this._skills.add(soft)
    }

    fun hard(hard: String) {
        this._skills.add(hard)
    }
}
