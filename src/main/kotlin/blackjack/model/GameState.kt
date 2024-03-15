package blackjack.model

enum class GameState(val winningState: WinningState, val payoutMultiplier: Double) {
    WinWhenBlackjack(WinningState(1, 0), 1.5),
    Win(WinningState(1, 0), 1.0),
    Tie(WinningState(0, 0), 0.0),
    Lose(WinningState(0, 1), -1.0),
}
