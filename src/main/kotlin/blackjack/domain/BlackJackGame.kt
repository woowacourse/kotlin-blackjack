package blackjack.domain

class BlackJackGame {
    private lateinit var input: (String) -> String
    private lateinit var output: (User) -> Unit
    private lateinit var dealerOutput: () -> Unit

    fun input(func: (String) -> String) { input = func }

    fun output(func: (User) -> Unit) { output = func }

    fun dealerOutput(func: () -> Unit) { dealerOutput = func }

    fun BlackJack.run() {
        this.usersTurn()
        this.dealerTurn()
    }

    private fun BlackJack.usersTurn() =
        users.forEach { user -> command(user, this.cardDeck) }

    private fun BlackJack.dealerTurn() {
        while (dealer.maxScore < DEALER_MIN_NUMBER) {
            dealer.draw(cardDeck.nextCard())
            dealerOutput()
        }
    }

    private fun command(user: User, cardDeck: CardDeck) {
        if (user.name.isDrawCommand()) return

        user.draw(cardDeck.nextCard())
        output(user)

        if (user.isBust) { command(user, cardDeck) }
    }

    private fun String.isDrawCommand() = input(this) !in DRAW_COMMANDS

    companion object {
        const val BLACKJACK_NUMBER = 21
        private const val DEALER_MIN_NUMBER = 17
        private val DRAW_COMMANDS = listOf("Y", "y")
    }
}
