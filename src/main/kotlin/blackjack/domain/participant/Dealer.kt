package blackjack.domain.participant

import blackjack.domain.card.Card

class Dealer : Participant("딜러") {
    override fun getFirstOpenCards(): List<Card> = listOf(getFirstCard())

    override fun canDraw(): Boolean {
        if (getTotalScore() >= STAY_SCORE) {
            stay()
        }
        return !isFinished
    }

    companion object {
        private const val STAY_SCORE = 17
    }
}
