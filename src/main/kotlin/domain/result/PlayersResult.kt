package domain.result

import domain.Dealer
import domain.Players

class PlayersResult(players: Players, dealer: Dealer) {
    val list: List<ParticipantResultInfo>
    val sum: Int

    init {
        list = players.list.map { player ->
            ParticipantResultInfo(
                player.name,
                (dealer.getProfit(player).value * player.betMoney).toInt(),
                player.getCards()
            )
        }
        sum = list.sumOf { it.profitMoney }
    }
}
