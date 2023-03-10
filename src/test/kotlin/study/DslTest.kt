import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest() {
    @Test
    fun name() {
        // Person().name("베리") 대신 아래 처럼 사용하는 이유는?
        val person = introduce {
            name("베리")
        }
        assertThat(person.name).isEqualTo("베리")
    }

    @Test
    fun company() {
        val person = introduce {
            name("베리")
            company("우아한테크코스")
        }
        assertThat(person.name).isEqualTo("베리")
        assertThat(person.company).isEqualTo("우아한테크코스")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("베리")
            company("우아한테크코스")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertThat(person.name).isEqualTo("베리")
        assertThat(person.company).isEqualTo("우아한테크코스")
        assertThat(person.skills.soft).isEqualTo(listOf("A passion for problem solving", "Good communication skills"))
        assertThat(person.skills.hard).isEqualTo(listOf("Kotlin"))
    }

    @Test
    fun languages() {
        val person = introduce {
            name("베리")
            company("우아한테크코스")
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
        assertThat(person.languages.languages).isEqualTo(listOf(Pair("Korean", 5), Pair("English", 3)))
    }
}

fun introduce(block: Person.() -> Unit): Person {
    // Person.() -> Unit 은 Person이 가지고 있는 아무 함수나 받겠다는 뜻
    // 위 함수를 아래처럼 간결하게 작성 가능
    return Person().apply(block)
}

class Person {
    var name: String = ""
    var company: String = ""
    var skills: Skills = Skills()
    var languages: Languages = Languages()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: Skills.() -> Unit) {
        this.skills.apply(block)
    }

    fun languages(block: Languages.() -> Unit) {
        this.languages.apply(block)
    }
}

class Skills {
    var soft: MutableList<String> = mutableListOf()
    var hard: MutableList<String> = mutableListOf()

    fun soft(soft: String) {
        this.soft.add(soft)
    }

    fun hard(hard: String) {
        this.hard.add(hard)
    }
}

class Languages {
    val languages: MutableList<Pair<String, Int>> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Pair(this, level))
    }
}
