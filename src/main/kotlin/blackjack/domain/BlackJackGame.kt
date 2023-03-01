package blackjack.domain

class BlackJackGame {
    private lateinit var input: (String) -> String
    private lateinit var output: (User) -> Unit

    fun input(func: (String) -> String) {
        input = func
    }

    fun output(func: (User) -> Unit) {
        output = func
    }

    fun run(blackJack: BlackJack) {
        blackJack.run {
            users.forEach { user -> command(user, blackJack.cardDeck) }
            while (dealer.maxScore < DEALER_MIN_NUMBER) { dealer.draw(cardDeck.nextCard()) }
        }
    }

    private fun command(user: User, cardDeck: CardDeck) {
        if (input(user.name) in DRAW_COMMANDS) {
            user.draw(cardDeck.nextCard())
            output(user)
            if (user.minScore < BLACKJACK_NUMBER) {
                command(user, cardDeck)
            }
        }
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
        private const val DEALER_MIN_NUMBER = 17
        private val DRAW_COMMANDS = listOf("Y", "y")
    }
}
