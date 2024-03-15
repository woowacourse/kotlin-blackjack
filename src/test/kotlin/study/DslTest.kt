package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource


class Person(val name: String, val company: String, val skills: Skills, val languages: Languages)

data class Languages(
    val language: Map<String, Int> = mapOf(),
)

class LanguagesBuilder {
    private var languages: Languages = Languages()

    infix fun String.level(that: Int) {
        languages = languages.copy(language = languages.language + Pair(this, that))
    }

    fun build(): Languages {
        return languages
    }

}

data class Skills(
    val soft: List<String> = emptyList(),
    val hard: List<String> = emptyList(),
)

class SkillsBuilder {
    private var skills: Skills = Skills()

    fun soft(value: String) {
        skills = skills.copy(soft = skills.soft + value)
    }

    fun hard(value: String) {
        skills = skills.copy(hard = skills.hard + value)
    }

    fun build(): Skills {
        return skills
    }
}

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: Skills
    private lateinit var languages: Languages


    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
//    apply: 해당 인스턴스에 추가적인 작업을 하겠다
    return PersonBuilder().apply(block).build()
//    return Person("").apply {
//        block()   // this는 생략 가능
//    }
}


class DslTest {
    @ParameterizedTest
    @CsvSource("홍길동,우형", "김철수,네이버")
    fun nameAndCompany(nameInput: String, companyInput: String) {
        val person: Person = introduce {
            name(nameInput)
            company(companyInput)
            skills {
                soft("소통 잘해요~")
                soft("문제를 해결하려고 노력해요~")
                hard("코틀린")
            }

            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(person.name).isEqualTo(nameInput)
        assertThat(person.company).isEqualTo(companyInput)
        assertThat(person.skills.soft).isEqualTo(listOf("소통 잘해요~", "문제를 해결하려고 노력해요~"))
        assertThat(person.skills.hard).isEqualTo(listOf("코틀린"))
        assertThat(person.languages.language["Korean"]).isEqualTo(5)
        assertThat(person.languages.language["English"]).isEqualTo(3)

    }
}
