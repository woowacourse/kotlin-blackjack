package blackjack.model.playing.cardhand

import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.playing.participants.Role

class CardHand(hand: List<Card>) {
    private val _hand = hand.toMutableList()
    val hand: List<Card> get() = _hand.toList()

    var hitState: Boolean = false

    constructor(vararg card: Card) : this(card.toList())

    fun calculateScore(): Int {
        var aceCount = hand.count { it.denomination == Denomination.ACE }
        var tempSum = hand.sumOf { it.denomination.score }

        while (aceCount >= MIN_ACE_COUNT && tempSum <= MAX_BONUS_AVAILABILITY) {
            aceCount--
            tempSum += SUBTRACTION_BETWEEN_MAX_AND_MIN
        }
        return tempSum
    }

    fun addNewCard(card: Card) {
        _hand.add(card)
    }

    fun getState(role: Role): CardHandState {
        val score = calculateScore()

        return when {
            score > BLACK_JACK_SCORE -> CardHandState.BUST
            score == BLACK_JACK_SCORE && hand.size == BLACK_JACK_CARD_HAND_SIZE -> CardHandState.BLACKJACK
            role.canDraw() && score < BLACK_JACK_SCORE -> CardHandState.HIT
            !role.canDraw() -> CardHandState.STAY
            else -> CardHandState.STAY
        }
    }

    fun readIsHit(): Boolean = hitState

    fun isBlackjack(role: Role) = getState(role) == CardHandState.BLACKJACK

    fun isHit(role: Role) = getState(role) == CardHandState.HIT

    override fun toString(): String {
        return "CardHand(hand=$hand)"
    }

    companion object {
        private const val MIN_ACE_COUNT = 1
        private const val SUBTRACTION_BETWEEN_MAX_AND_MIN = 10
        private const val MAX_BONUS_AVAILABILITY = 11
        private const val BLACK_JACK_CARD_HAND_SIZE = 2
        private const val BLACK_JACK_SCORE = 21
    }
}
