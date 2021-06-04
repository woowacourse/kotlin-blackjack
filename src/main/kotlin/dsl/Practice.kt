package dsl

class People(var name: String = "", var company: String = "", val skills: Skills = Skills(), var languages: Languages = Languages()) {
    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(f: Skills.() -> Unit) {
        skills.f()
    }

    fun languages(f: Languages.() -> Unit) {
        languages.f()
    }

}

class Languages(languages: MutableList<Pair<String, Int>> = mutableListOf()) {
    private val _languages: MutableList<Pair<String, Int>> = languages
    val languages: List<Pair<String, Int>>
        get() = _languages.toList()

    infix fun String.level(level: Int) {
        _languages.add(Pair(this, level))
    }
}

class Skills(
    soft: MutableList<String> = mutableListOf(), hard: MutableList<String> = mutableListOf(),
) {
    private val _soft: MutableList<String> = soft.toMutableList()
    private val _hard: MutableList<String> = hard.toMutableList()
    val soft: List<String>
        get() = _soft.toList()
    val hard: List<String>
        get() = _hard.toList()

    fun soft(skill: String) {
        _soft.add(skill)
    }

    fun hard(skill: String) {
        _hard.add(skill)
    }
}


fun introduce(f: People.() -> Unit): People {
    val people = People()
    people.f()
    return people
}
