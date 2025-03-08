package dsl

import dsl.languages.Language
import dsl.languages.LanguagesBuilder
import dsl.person.Person
import dsl.person.PersonBuilder
import dsl.skill.SkillBuilder
import dsl.skill.Skills

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

fun skill(block: SkillBuilder.() -> Unit): Skills {
    return SkillBuilder().apply(block).build()
}

fun languages(block: LanguagesBuilder.() -> Unit): Language? {
    return LanguagesBuilder().apply(block).build()
}

fun main()  {
    introduce {
        name("페토")
        company("우아한형제들")

        skill {
            soft("A passion for problem solving")
            soft("Good communication skills")
            hard("Kotlin")
        }
        languages {
            "Korean" level 5
            "English" level 3
        }
    }
}

// introduce {
//  name("박재성")
//  company("우아한형제들")
//  skills {
//    soft("A passion for problem solving")
//    soft("Good communication skills")
//    hard("Kotlin")
//  }
//  languages {
//    "Korean" level 5
//    "English" level 3
//  }
// }
