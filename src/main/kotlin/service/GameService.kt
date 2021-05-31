package service

import controller.PlayerResponse
import domain.BlackJackGame
import domain.player.Player

class GameService(players: List<Player>) {

    private val blackJackGame = BlackJackGame(players)

    fun distributeInitialCards(): List<PlayerResponse> {
        blackJackGame.giveInitialCards()
        val players = blackJackGame.players

        return PlayerResponse.parseList(players)
    }

    fun distributeCard(name: String): PlayerResponse {
        val player = blackJackGame.findGamblerByName(name)
        blackJackGame.givePlayerCard(player)

        return PlayerResponse(player)
    }

    fun giveDealerAdditionalCards(): PlayerResponse {
        blackJackGame.giveDealerAdditionalCards()

        return PlayerResponse(blackJackGame.dealer)
    }

    fun calculateResult(): List<PlayerResponse> {
        blackJackGame.endGame()
        val players = blackJackGame.players

        return PlayerResponse.parseList(players)
    }
}