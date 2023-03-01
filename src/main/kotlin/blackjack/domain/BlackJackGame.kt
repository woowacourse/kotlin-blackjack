package blackjack.domain

class BlackJackGame(names: List<String>) {
    val dealer = User("딜러")
    private val cardDeck = CardDeck(Card.all())
    val users: List<User> = names.map { User(it) }
    var userIndex: Int = 0
    private var status: GameStatus = GameStatus.START

    val isRunning
        get() = status == GameStatus.RUNNING
    fun setUp(): User {
        cardDeck.shuffle()
        dealer.draw(cardDeck.draw())
        dealer.draw(cardDeck.draw())

        users.map {
            it.draw(cardDeck.draw())
            it.draw(cardDeck.draw())
        }
        status = GameStatus.RUNNING
        return users[userIndex]
    }

    fun progress(user: User, command: String): User {
        when (command) {
            "y" -> {
                user.draw(cardDeck.draw())
                if (user.minScore >= 21) { userIndex++ }
            }
            "n" -> userIndex++
        }
        if (userIndex >= users.size) {
            status = GameStatus.END
            while (dealer.maxScore < 17) { dealer.draw(cardDeck.draw()) }
            return User("")
        }
        return users[userIndex]
    }

    fun getResult(): List<Outcome> {
        return users.map { user -> Outcome.of(dealer, user) }
    }
}
