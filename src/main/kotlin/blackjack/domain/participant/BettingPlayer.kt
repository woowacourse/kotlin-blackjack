package blackjack.domain.participant

import blackjack.domain.card.CardDeck

class BettingPlayer(val user: Player, val money: Int) {
    fun draw(deck: CardDeck) {
        user.addCard(deck.draw())
    }

    fun isBlackJack(): Boolean = user.isBlackjack()

    fun isBust(): Boolean = user.isBust()
}
