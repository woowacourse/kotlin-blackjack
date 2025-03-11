package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.gameResult.GameResults
import blackjack.domain.gameResult.PlayerResult
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player
import blackjack.view.blackjackView.format
import blackjack.view.blackjackView.toDisplayName

object OutputView {
    fun printFinalCards(game: BlackJackGame) {
        println(printDealerCard(game.dealer) + printTotalSum(game.dealer))

        game.players.forEach { player ->
            println(printPlayerCard(player) + printTotalSum(player))
        }
    }

    fun printGameResult(gameResults: GameResults) {
        println(FINAL_RESULT_NOTICE)
        val dealerLose = gameResults.countDealerLose()
        val dealerWin = gameResults.countDealerWin()
        val draw = gameResults.countDealerDraw()

        println(printDealerResult(dealerWin, dealerLose, draw))

        gameResults.playerResults.forEach {
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
