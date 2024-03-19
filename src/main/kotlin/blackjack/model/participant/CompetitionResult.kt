package blackjack.model.participant

enum class CompetitionResult(val profit: Double) {
    WIN(1.0),
    LOSE(-1.0),
    SAME(0.0),
    BLACKJACK(1.5),
}
