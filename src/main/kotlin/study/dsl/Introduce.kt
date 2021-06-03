package study.dsl

class Resume {
    var name = ""
    var company = ""
    var skills = Skill()
    var languages = Languages()

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
        this.skills = skill
    }

    fun languages(function: Languages.() -> Unit) {
        val languages = Languages()
        languages.apply { function() }
        this.languages = languages
    }

}