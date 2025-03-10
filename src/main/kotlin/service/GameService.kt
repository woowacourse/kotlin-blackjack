package service

import model.CardDistributor
import model.Dealer
import model.GameResultDecider
import model.Player
import model.Players

class GameService(private val cardDistributor: CardDistributor) {
    fun startGame(playerNames: List<String>): Pair<Dealer, Players> {
        val dealer = Dealer(cardDistributor.distributeInitialCards())
        val players = Players(getPlayers(playerNames))
        return Pair(dealer, players)
    }

    private fun getPlayers(playerNames: List<String>): List<Player> =
        playerNames.map { name -> Player(name, cardDistributor.distributeInitialCards()) }

    fun playPlayerTurns(
        players: Players,
        input: (String) -> Boolean,
    ) {
        players.forEach { player ->
            while (player.decideToHit() && input(player.name)) {
                player.performTurn(cardDistributor)
            }
        }
    }

    fun playDealerTurn(dealer: Dealer): Int {
        return dealer.getDrawCount(cardDistributor)
    }

    fun getGameResult(
        dealer: Dealer,
        players: Players,
    ): GameResultDecider {
        return GameResultDecider(dealer, players)
    }
}
