package domain.result

import domain.person.Persons
import domain.person.Player

class Casino(val persons: Persons, bets: List<Double>) {
    private val playerBets: Map<Player, Double> = persons.players.zip(bets).toMap()

    private fun getPlayerProfit(player: Player): Double {
        val playerBet = playerBets[player] ?: throw NoSuchElementException("Casino에서 발생한 예상치 못한 오류입니다.")
        return player.state.playerProfit(persons.dealer.state, playerBet)
    }

    fun getPlayersProfit(): Map<String, Double> {
        return persons.players
            .map { Pair(it.name, getPlayerProfit(it)) }
            .associate { it }
    }

    fun getDealerProfit(): Double {
        val playersProfit = persons.players.sumOf { getPlayerProfit(it) }
        return playersProfit * -1
    }
}
