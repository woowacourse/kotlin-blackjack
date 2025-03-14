package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.hand.Hand
import blackjack.model.hand.Score

class Dealer private constructor(
    name: Name,
    money: Money,
    hand: Hand,
) : Participant(name, money, hand) {
    override val isDrawable: Boolean get() = score <= DRAW_CRITERIA

    val additionalDrawCount: Int get() = cards.size - INITIAL_DRAW_COUNT

    override fun showInitialCards(): List<Card> = cards.take(FIRST_SHOWN_COUNT)

    companion object {
        const val DEFAULT_DEALER_NAME = "딜러"
        private val DRAW_CRITERIA = Score(16)
        private const val FIRST_SHOWN_COUNT = 1
        private const val DEALER_DEFAULT_MONEY = Double.MAX_VALUE

        fun create(
            name: String = DEFAULT_DEALER_NAME,
            money: Double = DEALER_DEFAULT_MONEY,
            hand: Hand = Hand(),
        ): Dealer = Dealer(Name(name), Money(money), hand)
    }
}
