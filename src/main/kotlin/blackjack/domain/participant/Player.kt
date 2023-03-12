package blackjack.domain.participant

import blackjack.domain.card.Card

class Player(val info: PlayerInfo) : Participant() {
    override fun getFirstOpenCards(): List<Card> = getCards()

    override fun canDraw(): Boolean {
        if (!info.askDraw(info.name)) {
            stay()
        }
        return !isFinished
    }
}
