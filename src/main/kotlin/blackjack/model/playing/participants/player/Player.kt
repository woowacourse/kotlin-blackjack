package blackjack.model.playing.participants.player

import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import blackjack.model.playing.participants.Role

data class Player(override val name: PlayerName, override val cardHand: CardHand) : Role(name, cardHand) {
    override fun getState(): CardHandState {
        val sum = cardHand.sum()

        return when {
            sum > CardHandState.BLACKJACK.precondition -> CardHandState.BUST
            sum == CardHandState.BLACKJACK.precondition && cardHand.hand.size == BLACK_JACK_CARD_HAND_SIZE -> CardHandState.BLACKJACK
            else -> CardHandState.DRAW_POSSIBILITY
        }
    }

    override fun canDraw() = this.getState() == CardHandState.DRAW_POSSIBILITY

    companion object {
        private const val BLACK_JACK_CARD_HAND_SIZE = 2
    }
}
