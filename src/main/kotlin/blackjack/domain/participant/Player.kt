package blackjack.domain.participant

import blackjack.domain.card.Card

class Player(name: String, private val needToDraw: () -> Boolean = { true }) : Participant(name) {
    override fun getFirstOpenCards(): List<Card> = getCards()

    override fun canDraw(): Boolean = !isBust() && needToDraw()
}