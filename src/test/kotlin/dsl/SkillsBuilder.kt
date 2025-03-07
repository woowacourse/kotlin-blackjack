package dsl

class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(skill: String) {
        skills.add(Skill(skill, "soft"))
    }

    fun hard(skill: String) {
        skills.add(Skill(skill, "hard"))
    }

    fun build(): List<Skill> = skills
}
