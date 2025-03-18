package blackjack.model

enum class ResultType(val profit: Double) {
    WIN(1.0),
    TIE(0.0),
    LOSS(-1.0),
    BLACKJACK(1.5),
}
