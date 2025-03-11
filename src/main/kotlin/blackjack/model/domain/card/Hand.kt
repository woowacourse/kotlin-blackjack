package blackjack.model.domain.card

import blackjack.model.domain.GameResult
import blackjack.model.domain.GameResult.Lose
import blackjack.model.domain.GameResult.None

class Hand(cards: MutableList<Card>) {
    private val _cards: MutableList<Card> = cards
    val cards: List<Card>
        get() = _cards.deepCopy()

    fun getSumNumber(): Int {
        var sum = cards.sumOf { it.cardNumber.number }
        val haveAce: Boolean = CardNumber.Ace in cards.map { it.cardNumber }

        if (haveAce && sum + CardNumber.BONUS_SCORE <= BUST_STANDARD) {
            sum += CardNumber.BONUS_SCORE
        }

        return sum
    }

    fun append(card: Card) {
        _cards.add(card)
    }

    fun isBust(): GameResult {
        if (getSumNumber() > BUST_STANDARD) return Lose
        return None
    }

    companion object {
        const val BUST_STANDARD: Int = 21
    }
}

private fun MutableList<Card>.deepCopy(): List<Card> = map { it.copy() }.toList()
