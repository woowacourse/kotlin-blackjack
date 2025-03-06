package blackjack.model

class Player(
    private val name: String,
    private val cardDeck: CardDeck,
) : Participant(cardDeck)
