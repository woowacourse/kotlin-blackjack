package blackjack

class Dealer(val totalSum: Int) : Participant {
    fun isWinner(
        player: Player,
    ): Result {
        return when {
            totalSum > player.totalSum -> Result.WIN
            player.totalSum > totalSum -> Result.LOSE
            else -> Result.DRAW
        }
    }
}
