package blackjack.model.participant

import blackjack.model.card.Card

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
            name: Name,
            money: Money = Money(PLAYER_DEFAULT_MONEY),
            hand: Hand = Hand(),
        ): Player = Player(name, money, hand)
    }
}
