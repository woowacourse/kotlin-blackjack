package domain.gamer

import domain.gamer.cards.Cards

class Players(private var players: List<Player>) {
    fun addPlayers(names: List<String>) {
        names.forEach {
            players = players.plus(Player(it, Cards(listOf())))
        }
    }

    fun getPlayers(): List<Player> {
        return players.toList()
    }
}
