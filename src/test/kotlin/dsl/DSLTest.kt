package dsl

import dsl.Languages.LanguageModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/*
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
 */

class DSLTest {
    @ValueSource(strings = ["박재성", "제이슨"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person =
            introduce {
                name(value)
            }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("박재성")
                company("우아한형제들")
            }
        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.company).isEqualTo("우아한형제들")
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("박재성")
                company("우아한형제들")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }

        assertThat(person.skills?.soft).isEqualTo(listOf("A passion for problem solving", "Good communication skills"))
        assertThat(person.skills?.hard).isEqualTo(listOf("Kotlin"))
    }

    @Test
    fun languages() {
        val person =
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
        assertThat(person.languages?.languages).isEqualTo(
            listOf(
                LanguageModel("Korean", 5),
                LanguageModel("English", 3),
            ),
        )
    }
}

// Function
fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

// Builder
class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person = Person(name, company, skills, languages)
}

class SkillsBuilder {
    private val soft: MutableList<String> = mutableListOf()
    private val hard: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build(): Skills = Skills(soft, hard)
}

class LanguagesBuilder {
    private val languages: MutableList<Pair<String, Int>> = mutableListOf()

    fun build(): Languages {
        val languages: List<LanguageModel> =
            languages.map { language -> LanguageModel(language.first, language.second) }
        return Languages(languages)
    }

    infix fun String.level(other: Int) {
        languages.add(this to other)
    }
}

// Model
data class Person(
    val name: String,
    val company: String?,
    val skills: Skills?,
    val languages: Languages?,
)

data class Skills(
    val soft: List<String>,
    val hard: List<String>,
)

data class Languages(
    val languages: List<LanguageModel>,
) {
    data class LanguageModel(
        val language: String,
        val level: Int,
    )
}
