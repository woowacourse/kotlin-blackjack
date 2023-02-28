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
                if (user.score >= 21) {
                    userIndex++
                }
                if (userIndex >= users.size) {
                    status = GameStatus.END
                    return User("")
                }
            }
            "n" -> userIndex++
        }
        return users[userIndex]
    }
}
