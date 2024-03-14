package blackjack.model

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    init {
        require(players.size <= MAX_PLAYER_SIZE) { PLAYER_SIZE_ERROR_MESSAGE }
    }

    fun getAllParticipants(): List<Participant> = listOf(dealer) + players

    fun getParticipantsProfits(): Map<Participant, Double> {
        val playersProfits = getPlayerProfits()
        val dealerProfit = getDealerProfits(playersProfits)
        return mapOf(dealer to dealerProfit).plus(playersProfits)
    }

    private fun getPlayerProfits(): Map<Player, Double> {
        return players.associateWith { player ->
            val winningResult = player.getWinningResult(dealer)
            player.calculateProfit(winningResult)
        }
    }

    private fun getDealerProfits(playersProfits: Map<Player, Double>): Double = playersProfits.entries.sumOf { it.value } * (-1)

    companion object {
        private const val MAX_PLAYER_SIZE = 5
        private const val PLAYER_SIZE_ERROR_MESSAGE = "플레이어의 수는 5명을 넘을 수 없습니다."
    }
}
