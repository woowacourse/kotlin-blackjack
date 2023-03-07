package view.draw

object DrawInput {

    fun inputDecision(): Decision? {
        val input = readln().trim()
        return Decision.of(input)
    }

    enum class Decision(val texts: List<String>, val doesGetMoreCard: Boolean) {
        YES(listOf("Y", "y"), true), NO(listOf("N", "n"), false);

        companion object {

            fun of(text: String): Decision? {
                return values().find { isTextSame(it, text) }
            }

            private fun isTextSame(decision: Decision, text: String): Boolean {
                return decision.texts.contains(text)
            }
        }
    }
}
