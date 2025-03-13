package dsl.skill

class SkillBuilder {
    private lateinit var soft: String
    private lateinit var hard: String

    fun soft(value: String) {
        require(value.isNotEmpty()) { SOFT_SKILL_EMPTY }
        soft = value
    }

    fun hard(value: String) {
        require(value.isNotEmpty()) { HARD_SKILL_EMPTY }
        hard = value
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }

    companion object {
        private const val SOFT_SKILL_EMPTY = "빈 소프트 스킬 이름이 입력 되었습니다. 소프트 스킬을 입력해 주세요."
        private const val HARD_SKILL_EMPTY = "빈 하드 스킬 이름이 입력 되었습니다. 하드 스킬을 입력해 주세요."
    }
}
