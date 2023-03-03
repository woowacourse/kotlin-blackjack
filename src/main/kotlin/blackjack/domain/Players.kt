package blackjack.domain

import blackjack.dto.HandDTO

class Players(private val players: List<Player>) {
    fun drawAll(deck: CardDeck) {
        players.forEach { it.addCard(deck.draw()) }
    }

    fun getHands(): List<HandDTO> = players.map(Player::getHand)

    fun toList(): List<Player> = players.toList()

    operator fun get(index: Int): Player = players[index]
}
