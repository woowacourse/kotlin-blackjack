package blackjack.domain

class ParticipantsBuilder {

    private lateinit var dealer: User
    private lateinit var users: List<User>
    fun dealer(name: String, cardDeck: CardDeck) {
        dealer = User(name)
        dealer.draw(cardDeck.draw())
        dealer.draw(cardDeck.draw())
    }

    fun users(names: List<String>, cardDeck: CardDeck) {
        users = names.map { User(it) }
        users.map {
            it.draw(cardDeck.draw())
            it.draw(cardDeck.draw())
        }
    }

    fun build(): Participants {
        return Participants(dealer, users)
    }
}
