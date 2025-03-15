package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.hand.Hand
import blackjack.model.hand.HandState

class Player private constructor(
    name: Name,
    money: Money,
    hand: Hand,
) : Participant(name, money, hand) {
    override val isDrawable: Boolean get() = handState == HandState.ALIVE

    override fun showInitialCards(): List<Card> = cards.take(FIRST_SHOWN_COUNT)

    companion object {
        val PLAYER_DEFAULT_MONEY = Money(1_000_000.0)
        private const val FIRST_SHOWN_COUNT = 2

        fun create(
            name: Name,
            money: Money = PLAYER_DEFAULT_MONEY,
            hand: Hand = Hand(),
        ): Player = Player(name, money, hand)
    }
}
