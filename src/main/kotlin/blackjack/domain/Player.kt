package blackjack.domain

import blackjack.domain.BlackJack.Companion.blackjackScore

class Player(name: String) : Participant(name) {
    override fun getFirstOpenCards(): List<Card> = getCards()

    override fun canDraw(): Boolean = cards.calculateTotalScore() > blackjackScore()
}
