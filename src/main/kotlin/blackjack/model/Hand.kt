package blackjack.model

import blackjack.model.CardsStatus.Companion.BLACKJACK_SCORE
import blackjack.model.Denomination.Companion.ACE_BONUS_NUMBER

class Hand(
    value: List<Card>,
) {
    private val _value: MutableList<Card> = value.toMutableList()
    val value: List<Card> get() = _value.map { card -> card.copy() }

    var status: CardsStatus = CardsStatus.from(cardsScore = getScore(), cardsSize = value.size)
        private set

    fun add(card: Card) {
        _value.add(card)
        status = CardsStatus.from(cardsScore = getScore(), cardsSize = value.size)
    }

    fun getScore(): Int {
        val score: Int = value.sumOf { card -> card.denomination.number }
        val modifiedScore: Int = score + ACE_BONUS_NUMBER
        return if (modifiedScore <= BLACKJACK_SCORE && value.any { card -> card.isAce() }) {
            modifiedScore
        } else {
            score
        }
    }
}
