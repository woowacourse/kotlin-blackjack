package study.dsl

class Skill {
    val softSkills = mutableListOf<String>()
    val hardSkills = mutableListOf<String>()

    fun soft(s: String) {
        softSkills.add(s)
    }

    fun hard(s: String) {
        hardSkills.add(s)
    }

}
