package model.card

class Card(val denomination: Denomination, val suit: Suit) {
    fun isAce(): Boolean = (denomination == Denomination.ACE)

    companion object {
        const val INVALID_RANGE = "0 ~ 51 사이의 숫자 범위여야 합니다"
        private const val MIN_NUMBER = 0
        private const val MAX_NUMBER = 51

        private fun Int.validateRange(): Int {
            require((MIN_NUMBER..MAX_NUMBER).contains(this)) { INVALID_RANGE }
            return this
        }

        fun from(number: Int): Card {
            return number.validateRange().run {
                Card(intToValueType(number), intToMarkType(number))
            }
        }

        fun ofStringType(
            valueType: String,
            markType: String,
        ): Card {
            return Card(stringToValueType(valueType), stringToMarkType(markType))
        }
    }
}
