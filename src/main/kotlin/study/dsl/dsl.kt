package study

import study.dsl.Resume


fun introduction() = Resume().introduce {
    name("김배럴")
    company("우테코코")
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


fun main() {
    introduction()
}

