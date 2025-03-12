package blackjack.model.participant

import blackjack.model.card.Card

class Player(
    name: String,
    hand: Hand,
) : Participant(name, hand) {
    override fun showInitialCards(): List<Card> = cards.take(FIRST_SHOWN_COUNT)

    override fun isDrawable(): Boolean = !isBust()

    companion object {
        private const val FIRST_SHOWN_COUNT = 2
    }
}
