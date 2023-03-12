package domain.result

import domain.money.Money
import domain.money.Profit
import domain.person.Participants
import domain.person.Player

class GameProfit(
    private val bettingMoneys: Map<Player, Money>,
) {

    fun getPlayersProfit(participants: Participants) =
        participants.players.associateWith { player ->
            val playerBettingMoney = requireNotNull(bettingMoneys[player]) { BETTING_MONEY_NOT_FOUND_ERROR }
            player.getPlayerProfit(participants.dealer.state, playerBettingMoney)
        }

    fun getDealersProfit(participants: Participants): Profit {
        val result = participants.players.sumOf { player ->
            val playerBettingMoney = requireNotNull(bettingMoneys[player]) { BETTING_MONEY_NOT_FOUND_ERROR }
            participants.dealer.getPlayerProfit(player.state, playerBettingMoney).value
        }
        return Profit(result)
    }

    companion object {
        const val BETTING_MONEY_NOT_FOUND_ERROR = "해당 플레이어의 배팅 금액을 찾을 수 없습니다."
        fun from(participants: Participants, getBettingMoney: (String) -> Int): GameProfit {
            val bettingMoneys =
                participants.players.associateWith { player -> Money(getBettingMoney(player.name)) }
            return GameProfit(bettingMoneys)
        }
    }
}
