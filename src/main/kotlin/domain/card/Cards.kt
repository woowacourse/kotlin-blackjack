package domain.card

import domain.BlackJackGame
import domain.Score

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
            return ((score.value == BlackJackGame.BLACKJACK_NUMBER) && (size == 2))
        }

    val score: Score
        get() = Score(sum(), hasAce)

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
}
