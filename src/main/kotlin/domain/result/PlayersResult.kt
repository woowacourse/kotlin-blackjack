package domain.result

import domain.Dealer
import domain.Players
import domain.ProfitMoney

class PlayersResult(players: Players, dealer: Dealer) {
    val list: List<ParticipantResultInfo>
    val sum: Int

    init {
        list = players.list.map { player ->
            ParticipantResultInfo(
                player.name,
                ProfitMoney.of(player.betMoney, dealer.getProfit(player)),
                player.getCards()
            )
        }
        sum = list.sumOf { it.profitMoney.value }
    }
}
