package blackjack.domain.participant

import blackjack.domain.card.Card

class Player(name: String, val askDraw: (String) -> Boolean = { true }) : Participant(name) {
    override fun getFirstOpenCards(): List<Card> = getCards()

    override fun canDraw(): Boolean {
        if (!askDraw(name)) {
            stay()
        }
        return !isFinished
    }
}
