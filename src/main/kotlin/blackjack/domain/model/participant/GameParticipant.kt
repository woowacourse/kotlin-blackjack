package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Deck

abstract class GameParticipant(
    val name: String,
) {
    private val cards = mutableListOf<Card>()

    init {
        repeat(2) { drawCard() }
    }

    fun showCards(): List<Card> = cards.toList()

    fun drawCard() {
        cards += Deck.giveCard()
    }

    abstract fun play()

    abstract fun isDrawFinish(): Boolean
}
