package blackjack.domain.model

enum class GameResult(val earningRate: Double) {
    Win(1.0),
    Lose(-1.0),
    Draw(0.0),
    BlackjackWin(1.5),
}
