package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {

    @Test
    fun name() {
        val person = introduce {
            this.name("name")
        }
        assertThat(person.name).isEqualTo("name")
    }

    @Test
    fun company() {
        val person = introduce {
            this.name("covy")
            company("우테코")
        }
        assertThat(person.name).isEqualTo("covy")
        assertThat(person.company).isEqualTo("우테코")
    }

    @Test
    fun skills() {
        val person = introduce {
            this.name("covy")
            company("우테코")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertThat(person.name).isEqualTo("covy")
        assertThat(person.company).isEqualTo("우테코")

        assertThat(person.skill.soft).isEqualTo(
            listOf(
                "A passion for problem solving",
                "Good communication skills"
            )
        )
        assertThat(person.skill.hard).isEqualTo(listOf("Kotlin"))
    }

    @Test
    fun languages() {
        val person = introduce {
            this.name("pingu")
            company("우테코")
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
        assertThat(person.name).isEqualTo("pingu")
        assertThat(person.company).isEqualTo("우테코")

        assertThat(person.skill.soft).isEqualTo(
            listOf(
                "A passion for problem solving",
                "Good communication skills"
            )
        )
        assertThat(person.skill.hard).isEqualTo(listOf("Kotlin"))

        assertThat(person.language.language["Korean"]).isEqualTo(5)
        assertThat(person.language.language["English"]).isEqualTo(3)
    }

    private fun introduce(block: Person.() -> Unit): Person {

        return Person().apply(block)
    }

    class Person {
        var name: String = ""
            private set
        var company: String = ""
            private set
        var skill: Skill = Skill()
            private set
        var language: Language = Language()
            private set

        fun name(name: String) {
            this.name = name
        }

        fun company(company: String) {
            this.company = company
        }

        fun skills(skill: Skill.() -> Unit): Skill =
            this.skill.apply(skill)

        fun languages(language: Language.() -> Unit): Language =
            this.language.apply(language)
    }

    class Skill {
        val soft = mutableListOf<String>()

        var hard: MutableList<String> = mutableListOf()

        fun soft(soft: String) {
            this.soft.add(soft)
        }

        fun hard(hard: String) {
            this.hard.add(hard)
        }
    }

    class Language {
        val language = mutableMapOf<String, Int>()
        infix fun String.level(other: Int) = language.put(this, other)
    }

    data class Player(var name: String = "pingu", var betAmount: Int = 10000)

    private fun makePlayer1(
        makePlayerAction: (Player) -> Unit
    ): Player {
        val player = Player()
        makePlayerAction(player)
        return player
    }

    @Test
    fun test() {
        println(
            makePlayer1 {
                it.name = "james"
                it.betAmount = 50000
            }
        )
    }

    private fun makePlayer2(
        makePlayerAction: Player.() -> Unit
    ): Player {
        val player = Player()
        player.makePlayerAction()
        return player
    }

    @Test
    fun test2() {
        println(
            makePlayer2 {
                this.name = "james"
                betAmount = 50000
            }
        )
    }
}
