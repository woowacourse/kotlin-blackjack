package dslstudy


fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private lateinit var skill: Skill
    private lateinit var languages: Map<String, Int>

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        val skillsBuilder = SkillsBuilder().apply(block)
        this.skill = skillsBuilder.build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        val languagesBuilder = LanguagesBuilder().apply(block)
        this.languages = languagesBuilder.getLanguages()
    }

    fun build(): Person = Person(name, company, skill, languages)
}

class SkillsBuilder {
    private val softItems = mutableListOf<String>()
    private val hardItems = mutableListOf<String>()

    fun soft(value: String) = softItems.add(value)

    fun hard(value: String) = hardItems.add(value)

    fun build(): Skill = Skill(softItems, hardItems)
}

class LanguagesBuilder {
    private val languages = mutableMapOf<String, Int>()

    infix fun String.level(level: Int) = languages.put(this, level)

    fun getLanguages(): Map<String, Int> = languages
}

data class Person(
    val name: String,
    val company: String?,
    val skill: Skill,
    val languages: Map<String, Int>,
)

data class Skill(
    val softSkill: List<String>,
    val hardSkill: List<String>,
)

introduce {
    name("박재성")
    company("우아한형제들")
    skills {
        soft("A passion for problem solving")
        soft("Good communication skills")
        hard("Kotlin")
    }
    languages {
        "Korean" level 5
        "English" level 3
    }
}
