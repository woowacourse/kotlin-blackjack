package view

fun String.toYesOrNo(): YesOrNo {
    return YesOrNo.values().find { it.answer == this } ?: throw IllegalArgumentException("Yes 또는 No가 아닙니다.")
}

enum class YesOrNo(val answer: String) {
    YES("y"),

    NO("n");

    fun isYes() = this == YES
}
