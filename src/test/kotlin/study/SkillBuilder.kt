package study

class SkillBuilder {
    private val skill = mutableListOf<String>()

    fun soft(value: String) {
        this.skill.add(value)
    }

    fun hard(value: String) {
        this.skill.add(value)
    }

    fun build(): Skills {
        return Skills(skill.toList())
    }
}
