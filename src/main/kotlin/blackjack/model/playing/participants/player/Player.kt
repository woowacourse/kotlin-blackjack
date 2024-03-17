package blackjack.model.playing.participants.player

import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import blackjack.model.playing.participants.Role
import blackjack.model.winning.Betting

data class Player(
    override val name: PlayerName,
    override val cardHand: CardHand,
    override var betting: Betting =
        Betting(
            0,
        ),
) : Role(name, cardHand, betting) {
    override fun canDraw(): Boolean = cardHand.getPlayerState() == CardHandState.DRAW_POSSIBILITY
}
