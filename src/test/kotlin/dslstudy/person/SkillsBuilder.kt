package dslstudy.person

class SkillsBuilder {
    private val softItems = mutableListOf<String>()
    private val hardItems = mutableListOf<String>()

    fun soft(value: String) = softItems.add(value)

    fun hard(value: String) = hardItems.add(value)

    fun build(): Skill = Skill(softItems, hardItems)
}
