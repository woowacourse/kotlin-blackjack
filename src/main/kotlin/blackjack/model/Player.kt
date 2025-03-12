package blackjack.model

class Player(
    val name: String,
    override val hand: Hand
) : Participant
