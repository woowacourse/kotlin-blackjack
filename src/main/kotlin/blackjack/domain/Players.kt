package blackjack.domain

class Players(private val players: List<Player>) {
    fun drawAll(deck: CardDeck) {
        players.forEach { it.addCard(deck.draw()) }
    }

    operator fun get(index: Int): Player = players[index]
}
