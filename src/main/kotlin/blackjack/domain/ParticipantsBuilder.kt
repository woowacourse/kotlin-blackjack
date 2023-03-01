package blackjack.domain

class ParticipantsBuilder {

    private lateinit var dealer: User
    private lateinit var users: List<User>
    fun dealer(name: String, cardDeck: CardDeck) {
        dealer = User(name)
        dealer.draw(cardDeck.nextCard())
        dealer.draw(cardDeck.nextCard())
    }

    fun users(names: List<String>, cardDeck: CardDeck) {
        users = names.map { User(it) }
        users.map {
            it.draw(cardDeck.nextCard())
            it.draw(cardDeck.nextCard())
        }
    }

    fun build(): Participants {
        return Participants(dealer, users)
    }
}
