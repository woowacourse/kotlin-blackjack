package blackjack.domain

import blackjack.dto.HandsDTO
import blackjack.dto.ScoresDTO

class BlackJack(private val deck: CardDeck, private val participants: Participants) {
    fun drawAll() {
        participants.drawAll(deck)
    }

    fun getInitialHands(): HandsDTO = participants.getInitialHands()

    fun getPlayers(): List<Player> = participants.getPlayers()

    fun drawPlayer(player: Player) {
        player.addCard(deck.draw())
    }

    fun drawDealer(block: (Boolean) -> Unit) = participants.drawDealerCard(deck, block)

    fun getGameScores(): ScoresDTO = participants.getGameScores()

    fun getGameResults(): Map<String, String> = participants.getGameResults()
}
