package model

import model.GameResult.Companion.BLACKJACK_SCORE

class Hand(initialCards: List<Card>) {
    private val _handCards: MutableList<Card> = initialCards.toMutableList()
    val handCards: List<Card> get() = _handCards.toList()

    fun getTotalScore(): Int {
        val baseScore = getScore()
        return if (isAceExist() && baseScore + ACE_PLUS_VALUE <= BLACKJACK_SCORE) {
            baseScore + ACE_PLUS_VALUE
        } else {
            baseScore
        }
    }

    fun addCards(cards: List<Card>) {
        cards.forEach {
            _handCards.add(it)
        }
    }

    fun getScore(): Int {
        return _handCards.sumOf { it.cardRank.score }
    }

    fun getCardsCount(): Int = _handCards.size

    private fun isAceExist(): Boolean = _handCards.any { it.cardRank == CardRank.ACE }

    fun isBust(): Boolean = getTotalScore() > BLACKJACK_SCORE

    fun isBlackJack(): Boolean = getTotalScore() == BLACKJACK_SCORE && _handCards.size == 2

    companion object {
        private const val ACE_PLUS_VALUE = 10
    }
}
