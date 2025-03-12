package blackjack.model.participant

import blackjack.model.card.Card

class Dealer(
    name: Name = Name(""),
    hand: Hand,
) : Participant(name, hand) {
    override fun showInitialCards(): List<Card> = cards.take(FIRST_SHOWN_COUNT)

    override fun isDrawable(): Boolean = score() <= DRAW_CRITERIA && !isBust()

    fun additionalDrawCount(): Int = cards.size - INITIAL_DRAW_COUNT

    companion object {
        private const val DRAW_CRITERIA = 16
        private const val FIRST_SHOWN_COUNT = 1
    }
}
