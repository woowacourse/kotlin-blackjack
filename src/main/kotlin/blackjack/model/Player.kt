package blackjack.model

class Player(
    val name: String,
    val firstCard : List<Card>,
) : Participant(firstCard)
