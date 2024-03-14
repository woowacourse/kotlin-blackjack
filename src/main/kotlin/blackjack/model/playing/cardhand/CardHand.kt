package blackjack.model.playing.cardhand

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.card.CardNumber

class CardHand(hand: List<Card>) {
    private val _hand = hand.toMutableList()
    val hand: List<Card> get() = _hand.toList()

    lateinit var state: CardHandState
        private set

    constructor(vararg card: Card) : this(card.toList())

    fun sum(): Int {
        var aceCount = hand.count { it.number == CardNumber.ACE }
        var tempSum = hand.sumOf { it.number.number }

        while (aceCount >= MIN_ACE_COUNT && tempSum <= MAX_BONUS_AVAILABILITY) {
            aceCount--
            tempSum += SUBTRACTION_BETWEEN_MAX_AND_MIN
        }
        return tempSum
    }

    fun addNewCard(cardDeck: CardDeck) {
        _hand.add(cardDeck.draw())
    }

    fun getPlayerState() {
        val sum = sum()

        state =
            when {
                sum > CardHandState.BLACKJACK.precondition -> CardHandState.BUST
                sum == CardHandState.BLACKJACK.precondition && hand.size == BLACK_JACK_CARD_HAND_SIZE -> CardHandState.BLACKJACK
                else -> CardHandState.DRAW_POSSIBILITY
            }
    }

    fun getDealerState() {
        val sum = sum()

        state =
            when {
                sum > CardHandState.BLACKJACK.precondition -> CardHandState.BUST
                hand.size == BLACK_JACK_CARD_HAND_SIZE && sum == CardHandState.BLACKJACK.precondition -> CardHandState.BLACKJACK
                sum <= DEALER_MAX_HIT_SUM -> CardHandState.HIT
                else -> CardHandState.STAY
            }
    }

    override fun toString(): String {
        return "CardHand(hand=$hand)"
    }

    companion object {
        private const val MIN_ACE_COUNT = 1
        private const val SUBTRACTION_BETWEEN_MAX_AND_MIN = 10
        private const val DEALER_MAX_HIT_SUM = 16
        private const val MAX_BONUS_AVAILABILITY = 11
        private const val BLACK_JACK_CARD_HAND_SIZE = 2
    }
}
