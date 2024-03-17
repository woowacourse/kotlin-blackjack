package blackjack.model

class Player(
    val name: String,
    hand: Hand,
    private var budget: Amount = Amount(INITIAL_BUDGET),
    profit: Amount = Amount(INITIAL_PROFIT),
) : Participant(hand, profit) {
    override fun hitWhileConditionTrue(
        deck: Deck,
        condition: () -> Boolean,
        view: () -> Unit,
    ) {
        while (condition()) {
            deck.refillIfDeckEmpty()
            hit(deck.pull())
            view()
        }
    }

    fun changeBudget(amount: Amount) {
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
            players.map { it.setInitialHand(deck) }
            return players
        }

        const val INITIAL_BUDGET = 0
    }
}
