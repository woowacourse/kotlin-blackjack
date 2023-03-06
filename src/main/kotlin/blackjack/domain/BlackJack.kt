package blackjack.domain

import blackjack.dto.ResultsDTO
import blackjack.dto.ScoresDTO

class BlackJack(private val deck: CardDeck, private val participants: Participants) {
    fun drawAll() {
        participants.drawAll(deck)
    }

    // fun getInitialHands(): HandsDTO = participants.getInitialHands()

    fun getPlayers(): List<Player> = participants.getPlayers()

    fun drawPlayer(player: Player) {
        player.addCard(deck.draw())
    }

    fun drawDealer(block: (Boolean) -> Unit) = participants.drawDealerCard(deck, block)

    fun getGameScores(): ScoresDTO = participants.getGameScores()

    fun getGameResults(): ResultsDTO = participants.getGameResults()

    companion object {
        private const val BLACKJACK_SCORE = 21
        fun blackjackScore(): Int = BLACKJACK_SCORE
    }
}
