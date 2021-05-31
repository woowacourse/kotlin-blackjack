package view

enum class YesOrNo(val answer: String) {
    YES("y"),
    NO("n");

    fun isYes(): Boolean {
        return this == YES
    }

    companion object {
        fun parse(str: String): YesOrNo {
            return values().find { it.answer == str } ?: throw IllegalArgumentException("YesOrNo가 아닙니다.")
        }
    }
}