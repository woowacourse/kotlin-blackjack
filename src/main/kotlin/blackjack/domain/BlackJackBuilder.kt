package blackjack.domain

class BlackJackBuilder {
    private lateinit var cardDeck: CardDeck
    private lateinit var dealer: User
    private lateinit var users: List<User>

    fun cardDeck(cards: List<Card>) {
        cardDeck = CardDeck(cards)
    }

    fun dealer(name: String) {
        dealer = User(name)
        dealer.draw(cardDeck.draw())
        dealer.draw(cardDeck.draw())
    }

    fun users(names: List<String>) {
        users = names.map { User(it) }
        users.map {
            it.draw(cardDeck.draw())
            it.draw(cardDeck.draw())
        }
    }

    fun build(): BlackJack {
        return BlackJack(dealer, users, cardDeck)
    }

    companion object {
        fun init(block: BlackJackBuilder.() -> Unit): BlackJack =
            BlackJackBuilder().apply(block).build()
    }
}
