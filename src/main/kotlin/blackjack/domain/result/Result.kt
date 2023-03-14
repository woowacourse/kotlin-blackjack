package blackjack.domain.result

enum class Result(val rate: Double) {
    DRAW(0.0),
    LOSE(-1.0),
    WIN(1.0);
}
