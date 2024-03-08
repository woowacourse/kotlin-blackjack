package study.builder

import study.model.Skill

class SkillBuilder {
    val softSkills = mutableListOf<String>()
    val hardSkills = mutableListOf<String>()

    fun soft(str: String) {
        this.softSkills.add(str)
        println(softSkills)
    }

    fun hard(str: String) {
        this.hardSkills.add(str)
    }

    fun build(): Skill {
        println("build: ${softSkills.toList()} ${hardSkills.toList()}")
        return Skill(softSkills.toList(), hardSkills.toList())
    }
}
