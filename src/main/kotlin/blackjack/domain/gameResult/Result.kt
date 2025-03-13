package blackjack.domain.gameResult

enum class Result(val sign: Int) {
    WIN(1),
    LOSE(-1),
    DRAW(0),
}
