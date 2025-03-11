package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.domain.gameResult.PlayerResult
import blackjack.domain.gameResult.PlayerResults
import blackjack.view.blackjackView.format
import blackjack.view.blackjackView.toDisplayName

object OutputView {
    fun printFinalCards(game: BlackJackGame) {
        println(printDealerCard(game.dealer) + printTotalSum(game.dealer))

        game.players.forEach { player ->
            println(printPlayerCard(player) + printTotalSum(player))
        }
    }

    fun printGameResult(playerResults: PlayerResults) {
        println(FINAL_RESULT_NOTICE)
        val dealerLose = playerResults.countDealerLose()
        val dealerWin = playerResults.countDealerWin()
        val draw = playerResults.countDealerDraw()

        println(printDealerResult(dealerWin, dealerLose, draw))

        playerResults.toList().forEach {
            println(printPlayerResult(it))
        }
    }

    private const val FINAL_RESULT_NOTICE = "\n##최종 승패"

    private fun printDealerCard(dealer: Dealer): String = "딜러: ${dealer.cards.toList().first().format()}"

    private fun printPlayerCard(player: Player): String = "${player.name}카드: ${player.cards.format()}"

    private fun printTotalSum(participant: Participant): String = " - 결과: ${participant.totalSum}\""

    private fun printDealerResult(
        dealerWin: Int,
        dealerLose: Int,
        draw: Int,
    ): String = "딜러: ${dealerWin}승 ${dealerLose}패 ${draw}무"

    private fun printPlayerResult(playerResult: PlayerResult): String =
        "${playerResult.player.name}: ${playerResult.status.toDisplayName()}"
}
