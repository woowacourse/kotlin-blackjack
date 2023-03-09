package blackjack.domain.participant

import blackjack.domain.BlackJack.Companion.blackjackScore
import blackjack.domain.card.Card

class Player(name: String) : Participant(name) {
    override fun getFirstOpenCards(): List<Card> = getCards()

    override fun canDraw(): Boolean = cards.calculateTotalScore() > blackjackScore()

    fun isBlackJack(): Boolean = cards.isBlackJack()
}
