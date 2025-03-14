package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.hand.Hand
import blackjack.model.hand.HandState

class Player private constructor(
    name: Name,
    money: Money,
    hand: Hand,
) : Participant(name, money, hand) {
    override fun showInitialCards(): List<Card> = cards.take(FIRST_SHOWN_COUNT)

    override fun isDrawable(): Boolean = handState == HandState.ALIVE

    companion object {
        const val PLAYER_DEFAULT_MONEY = 1_000_000.0
        private const val FIRST_SHOWN_COUNT = 2

        fun create(
            name: String,
            money: Double = PLAYER_DEFAULT_MONEY,
            hand: Hand = Hand(),
        ): Player = Player(Name(name), Money(money), hand)
    }
}
