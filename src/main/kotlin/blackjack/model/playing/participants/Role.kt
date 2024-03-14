package blackjack.model.playing.participants

import blackjack.model.card.generator.CardGenerator
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import blackjack.model.playing.participants.player.PlayerName

abstract class Role(open val name: PlayerName, open val cardHand: CardHand) {
    abstract fun getState(): CardHandState

    abstract fun canDraw(): Boolean

    fun addInitialCards(cardGenerator: CardGenerator) {
        repeat(INITIAL_CARDS_COUNT) {
            cardHand.addNewCard(cardGenerator)
        }
    }

    fun runPhase(cardGenerator: CardGenerator) {
        cardHand.addNewCard(cardGenerator)
    }

    companion object {
        private const val INITIAL_CARDS_COUNT = 2
    }
}
