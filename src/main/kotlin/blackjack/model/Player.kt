package blackjack.model

class Player(val name: String, val hand: Hand) : Participant() {
    override fun hit(card: Card) {
        hand.add(card)
    }

    override fun initialSetHand(deck: Deck) {
        repeat(INITIAL_HAND_COUNT) {
            hit(deck.pull())
        }
    }

    override fun hitWhileConditionTrue(
        deck: Deck,
        condition: () -> Boolean,
        view: () -> Unit,
    ) {
        while (condition()) {
            hit(deck.pull())
            view()
        }
    }

    companion object {
        fun createPlayers(
            names: List<String>,
            deck: Deck,
        ): List<Player> {
            val players = names.map { Player(it, Hand(listOf())) }
            players.map { it.initialSetHand(deck) }
            return players
        }
    }
}
