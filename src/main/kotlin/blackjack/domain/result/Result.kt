package blackjack.domain.result

enum class Result(val rate: Double) {
    BLACKJACK_WIN(1.5),
    DRAW(0.0),
    LOSE(-1.0),
    WIN(1.0);
}
