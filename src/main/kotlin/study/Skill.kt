package study

data class Skill(
    val soft: List<String>,
    val hard: List<String>,
)

class SkillBuilder {
    private val soft: MutableList<String> = mutableListOf()
    private val hard: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        this.soft.add(value)
    }

    fun hard(value: String) {
        this.hard.add(value)
    }

    fun build(): Skill {
        return Skill(soft, hard)
    }
}
