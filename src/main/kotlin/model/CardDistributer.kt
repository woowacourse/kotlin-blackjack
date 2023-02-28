package model

class CardDistributer(private val cardFactory: CardFactory) {
    fun distribute(player: Player, count: Int) {
        repeat(count) { player.cards.value.add(cardFactory.generate()) }
    }
}
