package blackjack.model

import blackjack.model.CardsStatus.Companion.BLACKJACK_SCORE
import blackjack.model.Denomination.Companion.ACE_BONUS_NUMBER

class Hand(
    value: List<Card>,
) {
    private val _value: MutableList<Card> = value.toMutableList()
    val value: List<Card> get() = _value.map { card -> card.copy() }

    private var status: CardsStatus = CardsStatus.from(cardsScore = getScore(), cardsSize = value.size)

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

    fun gameResult(opponent: Hand): GameResult =
        when {
            isBlackjack() && opponent.isBlackjack().not() -> GameResult.BLACKJACK_WIN
            isBust() -> GameResult.LOSE
            opponent.isBust() -> GameResult.WIN
            else -> GameResult.of(getScore(), opponent.getScore())
        }

    fun isBlackjack(): Boolean = status == CardsStatus.BLACKJACK

    fun isBust(): Boolean = status == CardsStatus.BUST
}
