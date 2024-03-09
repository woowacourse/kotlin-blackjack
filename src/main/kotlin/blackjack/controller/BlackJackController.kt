package blackjack.controller

import blackjack.model.Name
import blackjack.model.Player
import blackjack.view.InputView

object BlackJackController {
    fun run() {
        val players = registerPlayers()
    }

    private fun getPlayerNames(): List<Name> {
        return runCatching {
            InputView.inputPlayerNames().map { name -> Name(name) }
        }.onFailure { error ->
            println(error.message)
            return getPlayerNames()
        }.getOrThrow()
    }

    private fun registerPlayers(): List<Player> {
        val playerNames = getPlayerNames()
        return playerNames.map { name -> Player(name) }
    }
}