package service

import domain.BlackJackGame
import domain.player.Player
import dto.PlayerInfo
import dto.PlayerResponse

class GameService(playerInfos: List<PlayerInfo>) {

    private val blackJackGame = BlackJackGame(playerInfos.map {
        Player(it.name, it.money)
    })

    fun distributeInitialCards(): List<PlayerResponse> {
        blackJackGame.giveInitialCards()
        return playersAsResponses(blackJackGame.players)
    }

    fun distributeCard(name: String): PlayerResponse {
        val player = blackJackGame.findGamblerByName(name)
        blackJackGame.giveCard(player)
        return PlayerResponse(player)
    }

    fun giveDealerAdditionalCards(): PlayerResponse {
        blackJackGame.giveDealerAdditionalCards()
        return PlayerResponse(blackJackGame.dealer)
    }

    fun calculateResult(): List<PlayerResponse> {
        blackJackGame.endGame()
        return playersAsResponses(blackJackGame.players)
    }

    private fun playersAsResponses(players: List<Player>): List<PlayerResponse> {
        return players.map {
            PlayerResponse(it)
        }
    }
}