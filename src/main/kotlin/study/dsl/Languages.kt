package study.dsl

class Languages {
    val languages = HashMap<String, Int>()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

}
