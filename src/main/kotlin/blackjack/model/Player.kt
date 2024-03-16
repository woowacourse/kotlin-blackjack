package blackjack.model

class Player(
    val name: String,
    val hand: Hand,
    private var budget: Amount = Amount(INITIAL_BUDGET),
    profit: Amount = Amount(INITIAL_PROFIT),
) : Participant() {
    var profit = profit
        private set

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

    fun getMoney(amount: Amount) {
        budget += amount
        updateProfit(amount)
    }

    private fun updateProfit(amount: Amount) {
        profit += amount
    }

    companion object {
        fun createPlayers(
            names: List<String>,
            deck: Deck,
        ): List<Player> {
            val players =
                names.map { name ->
                    Player(name, Hand(listOf()))
                }
            players.map { it.initialSetHand(deck) }
            return players
        }

        const val INITIAL_BUDGET = 0
    }
}
