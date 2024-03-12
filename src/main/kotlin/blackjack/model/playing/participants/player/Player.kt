package blackjack.model.playing.participants.player

import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import blackjack.model.playing.participants.Role

data class Player(override val name: PlayerName, override val cardHand: CardHand) : Role(name, cardHand) {
    override fun getState(): CardHandState {
        val sum = cardHand.sum()

        return when {
            sum > CardHandState.BLACKJACK.precondition -> CardHandState.BUST
            sum == CardHandState.BLACKJACK.precondition -> CardHandState.BLACKJACK
            else -> CardHandState.STAY
        }
    }
}
