package study.builder

import study.model.Language
import study.model.Person
import study.model.Skill

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skill: Skill = Skill(mutableListOf(), mutableListOf())
    private var language: Language = Language(emptyMap())

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun build(): Person {
        return Person(name, company, skill, language)
    }

    fun skills(block: SkillBuilder.() -> Unit): Skill {
        return SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit): Language {
        return LanguageBuilder().apply(block).build()
    }
}
