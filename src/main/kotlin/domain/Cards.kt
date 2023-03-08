package domain

import controller.BlackJackGame

class Cards(cards: List<Card>) {
    private val _cards = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    val size: Int
        get() = _cards.size

    private val hasAce: Boolean
        get() = cards.any { it.isAce }

    val isBurst: Boolean
        get() = sum() > BlackJackGame.BLACKJACK_NUMBER

    val isBlackJack: Boolean
        get() {
            return ((resultSum == BlackJackGame.BLACKJACK_NUMBER) && (size == 2))
        }

    val resultSum: Int
        get() {
            var sum = sum()
            if (hasAce) {
                sum += ACE_ADDITIONAL_VALUE
            }
            return if (sum <= BlackJackGame.BLACKJACK_NUMBER) sum
            else sum()
        }

    init {
        check(cards.size >= MINIMUM_CARDS_SIZE) { ERROR_CARDS_SIZE }
    }

    fun add(card: Card) {
        _cards.add(card)
    }

    private fun sum(): Int {
        return cards.sumOf { it.cardNumber.value }
    }

    companion object {
        private const val MINIMUM_CARDS_SIZE = 2
        private const val ERROR_CARDS_SIZE = "[ERROR] 초기 카드는 ${MINIMUM_CARDS_SIZE}장 이상이어야 합니다."
        private const val ACE_ADDITIONAL_VALUE = 10
    }
}
