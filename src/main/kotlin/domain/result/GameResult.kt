package domain.result

import domain.person.Persons
import domain.person.Player
import domain.result.CompareResult.DealerBigger
import domain.result.CompareResult.DealerBust
import domain.result.CompareResult.PlayerBigger
import domain.result.CompareResult.PlayerBust
import domain.result.CompareResult.Same

class GameResult(private val persons: Persons) {
    fun getPlayerResult(): Map<String, OutCome> = persons.players
        .associate { compareTotalNumbers(it) }

    fun getDealerResult(): Map<OutCome, Int> = getPlayerResult().values
        .groupingBy { it.convertOutCome() }
        .eachCount()

    private fun compareTotalNumbers(player: Player): Pair<String, OutCome> {
        return when (CompareResult.compare(persons.dealer, player)) {
            PlayerBust -> PlayerBust.result(player.name)
            DealerBust -> DealerBust.result(player.name)
            DealerBigger -> DealerBigger.result(player.name)
            PlayerBigger -> PlayerBigger.result(player.name)
            Same -> Same.result(player.name)
        }
    }
}
