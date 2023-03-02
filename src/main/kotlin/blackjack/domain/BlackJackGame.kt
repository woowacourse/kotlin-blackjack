package blackjack.domain

class BlackJackGame {
    private lateinit var input: (String) -> String

    fun input(func: (String) -> String) { input = func }

    fun BlackJack.usersTurn(output: (User) -> Unit) {
        users.forEach { user ->
            command(user, this.cardDeck)
            output(user)
        }
    }

    fun BlackJack.dealerTurn(output: () -> Unit) {
        while (dealer.maxScore < DEALER_MIN_NUMBER) {
            dealer.draw(cardDeck.nextCard())
            output()
        }
    }

    private fun command(user: User, cardDeck: CardDeck) {
        if (user.isDrawCommand()) return

        user.draw(cardDeck.nextCard())

        if (user.isNotBust) { command(user, cardDeck) }
    }

    private fun User.isDrawCommand() = input(this.name.toString()) !in DRAW_COMMANDS

    companion object {
        const val BLACKJACK_NUMBER = 21
        private const val DEALER_MIN_NUMBER = 17
        private val DRAW_COMMANDS = listOf("Y", "y")
    }
}
