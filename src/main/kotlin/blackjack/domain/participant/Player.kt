package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.money.Money

class Player(name: String, private val money: Money, private val needToDraw: () -> Boolean = { true }) :
    Participant(name) {
    override fun getFirstOpenCards(): List<Card> = getCards()

    override fun canDraw(): Boolean = !isBust() && needToDraw()
}
