package blackjack.model.domain

enum class GameResult(val rate: Float) {
    BlackjackWin(1.5f),
    Win(1f),
    Lose(-1f),
    Draw(0f),
}
