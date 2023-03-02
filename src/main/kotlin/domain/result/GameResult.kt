package domain.result

import domain.person.Dealer
import domain.person.GameState.BUST
import domain.person.Player
import domain.result.OutCome.DRAW
import domain.result.OutCome.LOSE
import domain.result.OutCome.WIN

class GameResult(val players: List<Player>, val dealer: Dealer) {
    val playersResult = mutableMapOf<String, OutCome>()
    val dealerResult = mutableMapOf<OutCome, Int>()

    init {
        judgeResult()
    }

    private fun judgeResult() {
        players.forEach { compareTotalNumbers(it) }
    }

    private fun compareTotalNumbers(player: Player) {
        val differenceCardNumber = player.getTotalCardNumber() - dealer.getTotalCardNumber()
        when {
            player.gameState == BUST -> setPlayerResult(LOSE, player.name)
            differenceCardNumber < 0 -> setPlayerResult(LOSE, player.name)
            differenceCardNumber > 0 -> setPlayerResult(WIN, player.name)
            else -> setPlayerResult(DRAW, player.name)
        }
    }

    private fun setPlayerResult(playerOutCome: OutCome, name: String) {
        playersResult[name] = playerOutCome
        addDealerResult(playerOutCome.convertOutCome())
    }

    private fun addDealerResult(outCome: OutCome) {
        dealerResult[outCome] = dealerResult[outCome]?.plus(1) ?: 1
    }
}
