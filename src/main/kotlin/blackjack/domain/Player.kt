package blackjack.domain

import blackjack.domain.BlackJack.Companion.blackjackScore

class Player(name: String) : Participant(name) {
    override fun canDraw(): Boolean = hand.calculateTotalScore() > blackjackScore()
}
