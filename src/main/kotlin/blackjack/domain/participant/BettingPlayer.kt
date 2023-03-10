package blackjack.domain.participant

import blackjack.domain.card.CardDeck
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantScore

class BettingPlayer(val player: Player, val money: Int) {
    fun draw(deck: CardDeck) {
        player.addCard(deck.draw())
    }

    fun canDraw(): Boolean = player.canDraw()

    fun getCards(): ParticipantCards = ParticipantCards(player.name, player.getCards())

    fun getParticipantScore(): ParticipantScore = ParticipantScore(player.name, player.getTotalScore())

    fun getTotalScore(): Int = player.getTotalScore()

    fun getName(): String = player.name

    fun isBlackJack(): Boolean = player.isBlackjack()

    fun isBust(): Boolean = player.isBust()
}
