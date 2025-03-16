package model

import model.GameResult.Companion.BLACKJACK_SCORE

class Hand(initialCards: List<Card>) {
    private val _handCards: MutableList<Card> = initialCards.toMutableList()
    val handCards: List<Card> get() = _handCards.toList()

    fun getTotalScore(): Int = ScoreCalculator(this).calculateTotalCardScore()

    fun addCards(cards: List<Card>) {
        cards.forEach {
            _handCards.add(it)
        }
    }

    fun getScore(): Int {
        return _handCards.sumOf { it.cardRank.score }
    }

    fun getCardsCount(): Int = _handCards.size

    fun isAceExist(): Boolean = _handCards.any { it.cardRank == CardRank.ACE }

    fun isBust(): Boolean = getTotalScore() > BLACKJACK_SCORE

    fun isBlackJack(): Boolean = getTotalScore() == BLACKJACK_SCORE && _handCards.size == 2
}
