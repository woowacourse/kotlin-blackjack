package blackjack.view

import blackjack.domain.Player

class InputView {
    fun readPlayers(): List<Player> {
        val players: List<Player> = readln().split(",").map { name: String -> Player(name.trim()) }
        return players
    }

    fun readWantToHit(): Boolean {
        val input = readln()
        return input == "y"
    }
}
