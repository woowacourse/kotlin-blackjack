package dsl

import dsl.languages.Language
import dsl.languages.LanguagesBuilder
import dsl.person.Person
import dsl.person.PersonBuilder
import dsl.skill.SkillBuilder
import dsl.skill.Skills

fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

fun skill(block: SkillBuilder.() -> Unit): Skills = SkillBuilder().apply(block).build()

fun languages(block: LanguagesBuilder.() -> Unit): Language? = LanguagesBuilder().apply(block).build()

fun main() {
    introduce {
        name("비비")
        company("우아한테크코스")

        skill {
            soft("A passion for problem solving")
            soft("Good communication skills")
            hard("Kotlin")
        }
        languages {
            "Korean" level 5
            "English" level 4
        }
    }
}
