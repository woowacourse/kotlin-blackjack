package blackjack.model

enum class GameOutcome(val scoreRecord: ScoreRecord, val payoutMultiplier: Double) {
    WinWhenBlackjack(ScoreRecord(1, 0), 1.5),
    Win(ScoreRecord(1, 0), 1.0),
    Tie(ScoreRecord(0, 0), 0.0),
    Lose(ScoreRecord(0, 1), -1.0),
}
