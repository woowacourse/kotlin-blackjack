package domain.gamer

import domain.card.Deck

class Players(private val players: List<Player>) : List<Player> by players {
    fun initStage(deck: Deck) {
        repeat(2) { players.forEach { it.draw(deck.pop()) } }
    }
}
