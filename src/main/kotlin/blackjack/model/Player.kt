package blackjack.model

class Player(val name: String, val hand: Hand, val budget: Int) : Participant() {
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
            budget: List<Int>,
        ): List<Player> {
            val namesAndBudgets = names.zip(budget)
            val players =
                namesAndBudgets.map { (name, budget) ->
                    Player(name, Hand(listOf()), budget)
                }
            players.map { it.initialSetHand(deck) }
            return players
        }
    }
}
