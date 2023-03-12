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

    val isBust: Boolean
        get() = sum() > BlackJackGame.BLACKJACK_NUMBER

    val isBlackJack: Boolean
        get() {
            return ((score == BlackJackGame.BLACKJACK_NUMBER) && (size == 2))
        }

    val score: Int
        get() {
            val sum = sum()
            if (!hasAce) return sum
            val sumWithAceAdditionalValue = sum + ACE_ADDITIONAL_VALUE
            if (sumWithAceAdditionalValue <= BlackJackGame.BLACKJACK_NUMBER) {
                return sumWithAceAdditionalValue
            }
            return sum
        }

    fun add(card: Card): Cards {
        val newCards = _list.toMutableList()
        newCards.add(card)
        return Cards(newCards)
    }

    private fun sum(): Int {
        return list.sumOf { it.cardNumber.value }
    }

    fun deepCopy(): Cards {
        return Cards(list)
    }

    companion object {
        private const val ACE_ADDITIONAL_VALUE = 10
    }
}
