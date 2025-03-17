package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardCount
import blackjack.model.hand.Hand
import blackjack.model.hand.Score

class Dealer private constructor(
    name: Name,
    money: Money,
    hand: Hand,
) : Participant(name, money, hand) {
    override val isDrawable: Boolean get() = score <= DRAW_CRITERIA

    val additionalDrawCount: CardCount get() = CardCount(cards.size) - INITIAL_DRAW_COUNT

    override fun showInitialCards(): List<Card> = cards.take(FIRST_SHOWN_COUNT)

    fun draw(cards: (CardCount) -> List<Card>) {
        while (isDrawable) {
            receiveCards(cards)
        }
    }

    companion object {
        val DEFAULT_DEALER_NAME = Name("딜러")
        private const val FIRST_SHOWN_COUNT = 1
        private val DRAW_CRITERIA = Score(16)
        private val DEALER_DEFAULT_MONEY = Money(Double.MAX_VALUE)

        fun create(
            name: Name = DEFAULT_DEALER_NAME,
            money: Money = DEALER_DEFAULT_MONEY,
            hand: Hand = Hand(),
        ): Dealer = Dealer(name, money, hand)
    }
}
