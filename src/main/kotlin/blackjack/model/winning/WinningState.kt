package blackjack.model.winning

enum class WinningState(
    val profitRate: Double,
) {
    WIN_BY_BLACKJACK(1.5),
    WIN_DEFAULT(1.0),
    PUSH(0.0),
    LOSE(-1.0),
}
