package dsl

class SkillsBuilder {
    val softSkills = mutableListOf<Skill>()
    val hardSkills = mutableListOf<Skill>()

    fun soft(value: String) {
        softSkills.add(Skill(Type.SOFT, value))
    }

    fun hard(value: String) {
        hardSkills.add(Skill(Type.HARD, value))
    }
}
