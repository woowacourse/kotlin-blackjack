package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.progress.Rule

class Dealer(
    name: String = DEFAULT_NAME,
) : GameParticipant(name = name) {
    override fun play() {
        // todo
    }

    fun showFirstCard(): Card = showCards().first()

    override fun isDrawFinish(): Boolean = !Rule.calculateShouldDrawByCards(showCards())

    companion object {
        private const val DEFAULT_NAME = "딜러"
    }
}
