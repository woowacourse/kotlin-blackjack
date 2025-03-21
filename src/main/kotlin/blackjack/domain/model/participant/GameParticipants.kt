package blackjack.domain.model.participant

import blackjack.domain.model.participant.bet.ProfitInfo

data class GameParticipants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    val gameParticipants: List<GameParticipant>
        get() = listOf(dealer) + players
    val profitInfos: List<ProfitInfo>
        get() {
            val dealerProfitInfo = ProfitInfo(dealer.name, dealer.allPlayersMatchProfit(players))
            val playersProfitInfos =
                players.map { player ->
                    ProfitInfo(player.name, player.dealerMatchProfit(dealer))
                }
            return listOf(dealerProfitInfo) + playersProfitInfos
        }
}
