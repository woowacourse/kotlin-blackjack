package domain.card

import domain.BlackJackGame

class Cards(list: List<Card>) {
    private val _list = list.toMutableList()
    val list: List<Card>
        get() = _list.toList()

    val size: Int
        get() = _list.size

    private val hasAce: Boolean
        get() = list.any { it.isAce }

    val isBurst: Boolean
        get() = sum() > BlackJackGame.BLACKJACK_NUMBER

    val isBlackJack: Boolean
        get() {
            return ((resultSum == BlackJackGame.BLACKJACK_NUMBER) && (size == 2))
        }

    val resultSum: Int
        get() {
            val sum = sum()
            if (hasAce && (sum + ACE_ADDITIONAL_VALUE <= BlackJackGame.BLACKJACK_NUMBER)) {
                return sum + ACE_ADDITIONAL_VALUE
            }
            return sum
        }

    init {
        check(list.size >= MINIMUM_CARDS_SIZE) { ERROR_CARDS_SIZE }
    }

    fun add(card: Card) {
        _list.add(card)
    }

    private fun sum(): Int {
        return list.sumOf { it.cardNumber.value }
    }

    companion object {
        private const val MINIMUM_CARDS_SIZE = 2
        private const val ERROR_CARDS_SIZE = "[ERROR] 초기 카드는 ${MINIMUM_CARDS_SIZE}장 이상이어야 합니다."
        private const val ACE_ADDITIONAL_VALUE = 10
    }
}
