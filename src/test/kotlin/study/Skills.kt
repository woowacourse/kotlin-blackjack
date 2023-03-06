package study

import study.SkillType.HARD
import study.SkillType.SOFT

class Skills {
    private val _skills = mutableListOf<Skill>()
    val skills: List<Skill> get() = _skills.toList()

    fun soft(value: String) {
        _skills.add(Skill(SOFT, value))
    }

    fun hard(value: String) {
        _skills.add(Skill(HARD, value))
    }

    data class Skill(val type: SkillType, val name: String)
}
