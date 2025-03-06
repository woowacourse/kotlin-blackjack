package blackjack.model

class Player(
    name: String,
    cardDeck: CardDeck,
) : Participant(cardDeck)
