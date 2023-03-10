package blackjack.domain.participant

import blackjack.domain.BlackJack.Companion.blackjackScore
import blackjack.domain.card.Card

class Player(name: String, private val askDraw: (String) -> Boolean = { true }) : Participant(name) {
    override fun getFirstOpenCards(): List<Card> = getCards()

    override fun canDraw(): Boolean = !isBlackjack() && cards.calculateTotalScore() <= blackjackScore() && askDraw(name)
}
