package blackjack.model

class Player(
    val name: String,
    blackjackCalculator: BlackjackCalculator,
) : Participant(blackjackCalculator)
