package domain.result

import domain.person.Persons
import domain.person.Player

class GameResult(private val persons: Persons) {
    fun getPlayerResult(): Map<String, OutCome> = persons.players
        .associate { compareTotalNumbers(it) }

    fun getDealerResult(): Map<OutCome, Int> = getPlayerResult().values
        .groupingBy { it.convertOutCome() }
        .eachCount()

    private fun compareTotalNumbers(player: Player): Pair<String, OutCome> {
        return OutCome.getOutCome(persons.dealer, player)
    }
}
