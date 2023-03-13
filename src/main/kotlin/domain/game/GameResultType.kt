package domain.game

enum class GameResultType(val value: String, val profitRate: Double) {
    WIN("승", 1.0),
    DRAW("무", 0.0),
    LOSE("패", -1.0),
    BLACKJACK("블랙잭", 1.5),
}