package domain.card

class Card(val shape: Shape, val value: String) {
    init {
        require(VALUES.contains(value)) { println(ERROR_MESSAGE_NOT_CONTAIN_VALUE) }
    }

    fun valueOf(): Int {
        return when (value) {
            "A" -> 11
            "J", "Q", "K" -> 10
            else -> value.toInt()
        }
    }

    companion object {
        val VALUES = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "K", "J", "Q")
        private const val ERROR_MESSAGE_NOT_CONTAIN_VALUE = "[ERROR] value값이 정확한 카드값이 아닙니다."
    }
}
