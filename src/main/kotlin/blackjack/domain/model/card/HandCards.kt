package blackjack.domain.model.card

import blackjack.domain.model.GameResult

class HandCards {
    private val _cards = mutableListOf<Card>()
    private val cards
        get() = _cards.toList()

    fun add(card: Card) {
        _cards += card
    }

    fun show(): List<Card> {
        return cards
    }

    fun isBurst(): Boolean {
        return getScore() > BLACK_JACK_NUMBER
    }

    fun isLessOrSameThan(score: Int): Boolean {
        return getScore() <= score
    }

    fun compareTo(opponentHandCards: HandCards): GameResult {
        val myScore = getScore()
        val opponentScore = opponentHandCards.getScore()

        if (isBurst()) {
            return GameResult.LOSE
        }
        if (getScore() > opponentHandCards.getScore()) {
            return GameResult.WIN
        }
        if (getScore() == opponentHandCards.getScore()) {
            return GameResult.DRAW
        }
        return GameResult.LOSE
    }

    private fun getScore(): Int {
        val cardValues: Int = _cards.sumOf { it.cardNumber.value }

        if (_cards.any { it.isAce() } && cardValues + ACE_EXTRA_SCORE <= BLACK_JACK_NUMBER) {
            return cardValues + ACE_EXTRA_SCORE
        }
        return cardValues
    }

    companion object {
        private const val ACE_EXTRA_SCORE = 10
        private const val BLACK_JACK_NUMBER = 21
    }
}
