package domain.result

import domain.money.Money
import domain.money.Profit
import domain.person.Participants
import domain.person.Person
import domain.person.Player

class GameProfit(
    private val bettingMoneys: Map<Player, Money>,
) {

    fun getPersonsProfit(persons: List<Person>, other: Person) =
        persons.associateWith { person ->
            val playerBettingMoney = requireNotNull(bettingMoneys[person]) { BETTING_MONEY_NOT_FOUND_ERROR }
            person.state.profit(other.state, playerBettingMoney)
        }

    fun getPersonProfitTotal(person: Person, others: List<Person>): Profit {
        val result = others.sumOf { other ->
            val playerBettingMoney = requireNotNull(bettingMoneys[other]) { BETTING_MONEY_NOT_FOUND_ERROR }
            person.state.profit(other.state, playerBettingMoney).value
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
