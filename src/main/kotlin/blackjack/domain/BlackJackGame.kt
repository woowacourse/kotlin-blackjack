package blackjack.domain

class BlackJackGame() {
    lateinit var input: (String) -> String
    lateinit var output: (User) -> Unit

    fun input(func: (String) -> String) {
        input = func
    }

    fun output(func: (User) -> Unit) {
        output = func
    }

    fun run(blackJack: BlackJack) {
        blackJack.run {
            users.forEach { user -> command(user, blackJack.cardDeck) }
            while (dealer.maxScore < 17) { dealer.draw(cardDeck.draw()) }
        }
    }

    fun command(user: User, cardDeck: CardDeck) {
        if (input(user.name) == "y") {
            user.draw(cardDeck.draw())
            output(user)
            if (user.minScore < 21) {
                command(user, cardDeck)
            }
        }
    }
}
