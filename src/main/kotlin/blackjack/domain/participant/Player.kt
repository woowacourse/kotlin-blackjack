package blackjack.domain.participant

import blackjack.domain.BlackjackGame
import blackjack.domain.card.Card

class Player(val info: PlayerInfo) : Participant() {
    override fun getFirstOpenCards(): List<Card> = getCards()

    override fun canDraw(): Boolean {
        return !isBlackjack() && cards.calculateTotalScore() <= BlackjackGame.blackjackScore() && info.askDraw(info.name)
    }
}
