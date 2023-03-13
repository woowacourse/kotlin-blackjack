package blackjack.domain.result

enum class GameResult(val payout: Double) {
    BLACKJACK(1.5), WIN(1.0), DRAW(0.0), LOSE(-1.0);
}
