package study.dsl

class Resume {
    lateinit var name: String
    lateinit var company: String
    lateinit var skill: Skill
    lateinit var languages: Languages

    fun introduce(function: Resume.() -> Unit): Resume {
        this.function()
        return this
    }

    fun name(s: String) {
        name = s
    }

    fun company(s: String) {
        company = s
    }

    fun skills(function: Skill.() -> Unit) {
        val skill = Skill()
        skill.function()
        this.skill = skill
    }

    fun languages(function: Languages.() -> Unit) {
        val languages = Languages()
        languages.apply { function() }
        this.languages = languages
    }

}