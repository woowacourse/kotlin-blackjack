package blackjack.model

class Player(val name: String, hand: Hand) {
    private val _hand: MutableList<Card> = hand.cards.toMutableList()
    val hand: Hand get() = Hand(_hand.toList())

    constructor(pair: Pair<String, Hand>) : this(pair.first, pair.second)

    fun hit(card: Card) {
        _hand.add(card)
    }

    companion object {
        fun createPlayers(
            names: List<String>,
            hands: List<Hand>,
        ): List<Player> {
            return names.zip(hands).map(::Player)
        }
    }
}
