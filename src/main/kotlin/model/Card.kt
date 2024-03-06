package model

class Card private constructor(val value: Value, val mark: Mark) {
    companion object {
        const val INVALID_RANGE = "0 ~ 51 사이의 숫자 범위여야 합니다"
        private const val MIN_NUMBER = 0
        private const val MAX_NUMBER = 51
        private const val DIVIDER = 13

        private fun Int.validateRange(): Int {
            require((MIN_NUMBER..MAX_NUMBER).contains(this)) { INVALID_RANGE }
            return this
        }

        fun from(number: Int): Card {
            return number.validateRange().run {
                Card(getValue(number), getMark(number))
            }
        }

        private fun getMark(number: Int): Mark {
            return when (number / DIVIDER) {
                Mark.SPADE.order -> Mark.SPADE
                Mark.CLOVER.order -> Mark.CLOVER
                Mark.HEART.order -> Mark.HEART
                else -> Mark.DIAMOND
            }
        }

        private fun getValue(number: Int): Value {
            return Value.values()[number % DIVIDER]
        }
    }
}
