package blackjack.domain

class BlackJackGame(names: List<String>) {
    val dealer = User("딜러")
    private val cardDeck = CardDeck(Card.all())
    val users: List<User> = names.map { User(it) }

    fun setUp() {
        cardDeck.shuffle()
        dealer.draw(cardDeck.draw())
        dealer.draw(cardDeck.draw())

        users.map {
            it.draw(cardDeck.draw())
            it.draw(cardDeck.draw())
        }
    }
}
