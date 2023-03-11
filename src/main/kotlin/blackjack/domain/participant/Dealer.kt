package blackjack.domain.participant

import blackjack.domain.card.Card

class Dealer : Participant() {
    val name: String = "딜러"

    override fun getFirstOpenCards(): List<Card> = cards.getFirstCard()

    override fun canDraw(): Boolean = cards.calculateTotalScore() < STAY_SCORE

    companion object {
        private const val STAY_SCORE = 17
    }
}
