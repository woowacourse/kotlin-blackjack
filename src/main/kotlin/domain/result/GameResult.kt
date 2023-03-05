package domain.result

import domain.person.Dealer
import domain.person.GameState
import domain.person.Player
import domain.result.OutCome.DRAW
import domain.result.OutCome.LOSE
import domain.result.OutCome.WIN

class GameResult(private val dealer: Dealer, private val players: List<Player>) {
    fun getPlayerResult(): Map<String, OutCome> =
        players.associate { compareTotalNumbers(it) }

    fun getDealerResult(): Map<OutCome, Int> {
        return getPlayerResult().values.groupingBy { it.convertOutCome() }.eachCount()
    }

    private fun compareTotalNumbers(player: Player): Pair<String, OutCome> {
        val differenceCardNumber = player.getTotalCardNumber() - dealer.getTotalCardNumber()
        return when {
            player.isState(GameState.BUST) -> player.name to LOSE
            dealer.isState(GameState.BUST) -> player.name to WIN
            differenceCardNumber < 0 -> player.name to LOSE
            differenceCardNumber > 0 -> player.name to WIN
            else -> player.name to DRAW
        }
    }
}
