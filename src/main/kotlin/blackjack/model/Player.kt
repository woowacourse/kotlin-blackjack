package blackjack.model

class Player(
    val name: String,
    scoreCalculator: ScoreCalculator,
) : Participant(scoreCalculator)
