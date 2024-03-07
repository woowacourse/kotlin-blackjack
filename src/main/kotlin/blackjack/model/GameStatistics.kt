package blackjack.model

class GameStatistics(
    dealer: Dealer,
    players: List<Player>
) {
    val playerStatistics: Map<String, GameResult> by lazy {
        players.associate { player ->
            player.name to judge(player, dealer)
        }
    }

    val dealerStatistics: Map<GameResult, Int> by lazy {
        playerStatistics.values.map {
            when (it) {
                GameResult.`패` -> GameResult.`승`
                GameResult.`승` -> GameResult.`패`
                GameResult.`무` -> GameResult.`무`
            }
        }.groupingBy { it }.eachCount()
    }

    private fun judge(player: Player, dealer: Dealer): GameResult {
        val playerScore = player.getCardSum()
        val dealerScore = dealer.getCardSum()

        return when {
            player.isBusted() ||
                    (playerScore < dealerScore && !dealer.isBusted()) ||
                    !player.isBlackJack() && dealer.isBlackJack()
            -> {
                GameResult.`패`
            }

            (dealer.isBlackJack() && player.isBlackJack()) -> {
                GameResult.`무`
            }

            dealer.isBusted() || (dealerScore < playerScore) || player.isBlackJack() -> {
                GameResult.`승`
            }

            else -> GameResult.무
        }
    }
}
