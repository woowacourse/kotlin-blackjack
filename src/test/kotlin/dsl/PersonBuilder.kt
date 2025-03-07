package dsl

class PersonBuilder {
    private lateinit var name: String
    private lateinit var mbti: String
    private val cats = mutableListOf<Cat>()
    private val hobbies = mutableListOf<String>()
    private val skills = mutableListOf<Skill>()

    fun name(value: String) {
        name = value
    }

    fun mbti(value: String) {
        mbti = value
    }

    fun cats(block: CatsBuilder.() -> Unit) {
        cats.addAll(CatsBuilder().apply(block).build())
    }

    fun hobby(block: HobbiesBuilder.() -> Unit) {
        hobbies.addAll(HobbiesBuilder().apply(block).build())
    }

    fun skill(block: SkillsBuilder.() -> Unit) {
        skills.addAll(SkillsBuilder().apply(block).build())
    }

    fun build(): Person = Person(name, mbti, cats, hobbies, skills)

}
