package blackjack.domain

import blackjack.domain.GameResult.Companion.judgePlayer
import blackjack.dto.ResultDTO
import blackjack.dto.ResultsDTO
import blackjack.dto.ScoresDTO

class Participants(private val dealer: Dealer, private val players: Players) {
    fun drawAll(deck: CardDeck) {
        dealer.addCard(deck.draw())
        players.drawAll(deck)
    }

    // fun getInitialHands(): HandsDTO = HandsDTO(dealer.getFirstCardHand(), players.getHands())

    fun getPlayers(): List<Player> = players.toList()

    fun drawDealerCard(deck: CardDeck, block: (Boolean) -> Unit) {
        while (dealer.canDraw()) {
            dealer.addCard(deck.draw())
            block(true)
        }
        block(false)
    }

    fun getGameScores(): ScoresDTO {
        val dealerScore = dealer.getScore()
        val playersScore = players.toList().map(Player::getScore)
        return ScoresDTO(dealerScore, playersScore)
    }

    fun getGameResults(): ResultsDTO = ResultsDTO(
        players.toList().map { player ->
            val result = judgePlayer(dealer.getTotalScore(), player.getTotalScore()).name
            ResultDTO(player.name, result)
        }
    )
}
