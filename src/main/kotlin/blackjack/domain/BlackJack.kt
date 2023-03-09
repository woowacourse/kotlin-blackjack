package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantScore
import blackjack.domain.participant.BettingPlayer
import blackjack.domain.participant.Participants
import blackjack.domain.result.PlayerResults

class BlackJack(private val deck: CardDeck, private val participants: Participants) {
    fun drawAll() {
        participants.drawAll(deck)
    }

    fun getFirstOpenCards(): List<ParticipantCards> = participants.getFirstOpenCards()

    fun getPlayers(): List<BettingPlayer> = participants.getPlayers()

    fun drawPlayer(player: BettingPlayer) {
        player.draw(deck)
    }

    fun drawDealer(block: (Boolean) -> Unit) = participants.drawDealerCard(deck, block)

    fun getCards(): List<ParticipantCards> = participants.getCards()

    fun getTotalScores(): List<ParticipantScore> = participants.getTotalScores()

    fun getGameResults(): PlayerResults = participants.judgePlayers()

    companion object {
        private const val BLACKJACK_SCORE = 21
        fun blackjackScore(): Int = BLACKJACK_SCORE
    }
}
