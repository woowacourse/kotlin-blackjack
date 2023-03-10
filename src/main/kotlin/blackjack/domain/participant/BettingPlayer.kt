package blackjack.domain.participant

import blackjack.domain.card.CardDeck
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantScore

class BettingPlayer(val user: Player, val money: Int) {
    fun draw(deck: CardDeck) {
        user.addCard(deck.draw())
    }

    fun getCards(): ParticipantCards = ParticipantCards(user.name, user.getCards())

    fun getParticipantScore(): ParticipantScore = ParticipantScore(user.name, user.getTotalScore())

    fun getTotalScore(): Int = user.getTotalScore()

    fun getName(): String = user.name

    fun isBlackJack(): Boolean = user.isBlackjack()

    fun isBust(): Boolean = user.isBust()
}
