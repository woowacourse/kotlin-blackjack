package blackjack.model

class Player(
    val name: String,
    hand: Hand
) : Participant(hand)
