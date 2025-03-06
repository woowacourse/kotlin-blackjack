package blackjack.view

import blackjack.model.Player

class InputView {
    fun readPlayers(): List<Player> {
        val players =
            readln()
                .split(",")
                .map { Player(name = it.trim()) }
        return players
    }
}
