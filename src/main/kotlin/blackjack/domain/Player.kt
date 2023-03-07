package blackjack.domain

import blackjack.domain.BlackJack.Companion.blackjackScore

class Player(name: String) : Participant(name) {
    override fun getFirstOpenCards(): List<Card> = hand.cards

    override fun canDraw(): Boolean = hand.calculateTotalScore() > blackjackScore()
}
