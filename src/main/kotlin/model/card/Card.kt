package model.card

class Card private constructor(val valueType: ValueType, val markType: MarkType) {
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

        private fun getMark(number: Int): MarkType {
            return when (number / DIVIDER) {
                MarkType.SPADE.order -> MarkType.SPADE
                MarkType.CLOVER.order -> MarkType.CLOVER
                MarkType.HEART.order -> MarkType.HEART
                else -> MarkType.DIAMOND
            }
        }

        private fun getValue(number: Int): ValueType {
            return ValueType.values()[number % DIVIDER]
        }
    }
}
