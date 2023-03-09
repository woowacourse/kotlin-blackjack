package domain.result

import domain.constant.GameState
import domain.money.Money
import domain.person.Participants
import domain.person.Player
import domain.result.OutCome.BLACKJACK
import domain.result.OutCome.DRAW
import domain.result.OutCome.LOSE
import domain.result.OutCome.WIN

class GameResult(private val participants: Participants) {
    fun getPlayerResult(bettingMoneys: Map<String, Money>): Map<String, Double> {
        val compareResult = participants.players.associate { compareTotalNumbers(it) }
        return compareResult.keys.associateWith { name ->
            compareResultToPrice(
                compareResult[name],
                bettingMoneys[name],
            )
        }
    }

    fun getDealerResult(playerResult: Map<String, Double>): Double {
        return playerResult.values.sumOf { it * -1 }
    }

    private fun compareTotalNumbers(player: Player): Pair<String, OutCome> {
        val differenceCardNumber =
            CardsScore.getTotalWithOneBigAce(player.cards) - CardsScore.getTotalWithOneBigAce(participants.dealer.cards)
        return when {
            player.isState(GameState.BLACKJACK) -> player.name to BLACKJACK
            player.isState(GameState.BUST) -> player.name to LOSE
            participants.dealer.isState(GameState.BUST) -> player.name to WIN
            differenceCardNumber < 0 -> player.name to LOSE
            differenceCardNumber > 0 -> player.name to WIN
            else -> player.name to DRAW
        }
    }

    private fun compareResultToPrice(outCome: OutCome?, bettingMoney: Money?): Double {
        require(outCome != null && bettingMoney != null)

        return when (outCome) {
            DRAW -> 0.0
            BLACKJACK -> bettingMoney.value * 1.5
            WIN -> bettingMoney.value.toDouble()
            LOSE -> bettingMoney.value.toDouble() * -1
        }
    }
}
