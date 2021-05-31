package view

enum class YesOrNo(val answer: String) {
    YES("y"),
    NO("n");

    fun isYes() = this == YES

    companion object {
        fun parse(input: String): YesOrNo {
            return values().find { it.answer == input } ?: throw IllegalArgumentException("Yes 또는 No가 아닙니다.")
        }
    }
}