package blackjack.model.participant

import blackjack.model.card.Card

class Dealer private constructor(
    name: Name,
    money: Money,
    hand: Hand,
) : Participant(name, money, hand) {
    override fun showInitialCards(): List<Card> = cards.take(FIRST_SHOWN_COUNT)

    override fun isDrawable(): Boolean = score() <= DRAW_CRITERIA

    fun additionalDrawCount(): Int = cards.size - INITIAL_DRAW_COUNT

    companion object {
        const val DEFAULT_DEALER_NAME = "딜러"
        private const val DRAW_CRITERIA = 16
        private const val FIRST_SHOWN_COUNT = 1
        private const val DEALER_DEFAULT_MONEY = Double.MAX_VALUE

        fun create(
            name: String = DEFAULT_DEALER_NAME,
            money: Money = Money(DEALER_DEFAULT_MONEY),
            hand: Hand = Hand(),
        ): Dealer = Dealer(Name(name), money, hand)
    }
}
