package blackjack.domain.model

class Verdict(private val dealer: Dealer) {
    fun determine(player: Player): VerdictResult {
        return when {
            dealer.isBust() && player.isBust() -> VerdictResult.LOSE
            dealer.isBust() -> VerdictResult.WIN
            dealer.getScore() > player.getScore() || player.isBust() -> VerdictResult.LOSE
            dealer.getScore() < player.getScore() && !player.isBust() -> VerdictResult.WIN
            else -> VerdictResult.DRAW
        }
    }
}
