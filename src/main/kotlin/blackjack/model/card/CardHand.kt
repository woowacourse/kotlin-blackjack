package blackjack.model.card

import blackjack.model.config.GameRule.BLACK_JACK_SCORE
import blackjack.model.config.GameRule.INITIAL_CARD_COUNT
import blackjack.model.result.Score

class CardHand(hand: List<Card>) {
    private val _hand = hand.toMutableList()
    val hand: List<Card> get() = _hand.toList()

    val isReady: Boolean
        get() = hand.size < INITIAL_CARD_COUNT

    val isBlackJack: Boolean
        get() = (calculateScore() == BLACK_JACK_SCORE) && (hand.size == INITIAL_CARD_COUNT)

    val isBust: Boolean
        get() = (calculateScore() > BLACK_JACK_SCORE)

    constructor(vararg card: Card) : this(card.toList())

    fun calculateScore(): Score {
        val numbersSum = numbersSum()
        if (canGetBonusPoint()) return Score(numbersSum + BONUS_POINT)
        return Score(numbersSum)
    }

    fun addNewCard(card: Card) {
        _hand.add(card)
    }

    private fun canGetBonusPoint(): Boolean = hasAce() && (numbersSum() <= MAX_NUMBER_SUM_FOR_BONUS_POINT)

    private fun hasAce(): Boolean = hand.any { card -> card.number == CardNumber.ACE }

    private fun numbersSum(): Int = hand.sumOf { card -> card.number.number }

    override fun toString(): String {
        return "CardHand(hand=$hand)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CardHand

        if (hand != other.hand) return false

        return true
    }

    override fun hashCode(): Int {
        return hand.hashCode()
    }

    companion object {
        private const val MAX_NUMBER_SUM_FOR_BONUS_POINT = 11
        private const val BONUS_POINT = 10
    }
}
