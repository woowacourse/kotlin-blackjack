package blackjack.model

class Player(
    val name: String,
    cardDeck: CardDeck,
) : Participant(cardDeck)
