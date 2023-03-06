package domain.result

import domain.constant.GameState
import domain.person.Dealer
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
        val differenceCardNumber = CardsScore.getTotalCardNumber(player.cards) - CardsScore.getTotalCardNumber(dealer.cards)
        return when {
            player.isState(GameState.BUST) -> player.name to LOSE
            dealer.isState(GameState.BUST) -> player.name to WIN
            differenceCardNumber < 0 -> player.name to LOSE
            differenceCardNumber > 0 -> player.name to WIN
            else -> player.name to DRAW
        }
    }
}
