package blackjack.model.playing.participants.player

import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import blackjack.model.playing.participants.Role

data class Player(override val name: PlayerName, override val cardHand: CardHand) : Role(name, cardHand) {
    override fun canDraw(): Boolean = cardHand.getPlayerState() == CardHandState.DRAW_POSSIBILITY
}
