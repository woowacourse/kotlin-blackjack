package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.domain.PlayerResult
import blackjack.domain.PlayerResults

object OutputView {
    fun showInitialCards(game: BlackJackGame) {
        println(printProvidedCard(game))
        println(printDealerCard(game.dealer))
        game.players.forEach { player ->
            printPlayerCards(player)
        }
    }

    fun printPlayerCards(player: Player) {
        println(printPlayerCard(player))
    }

    fun printDealerHaveAdditionalCard() {
        println(DEALER_GET_ADDITIONAL_CARD)
    }

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

    private const val DEALER_GET_ADDITIONAL_CARD = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n"
    private const val FINAL_RESULT_NOTICE = "\n##최종 승패"

    private fun printProvidedCard(game: BlackJackGame): String = "딜러와 ${game.players.joinToString { it.name }}에게 2장을 나누었습니다.\n"

    private fun printDealerCard(dealer: Dealer): String = "딜러: ${dealer.cards.getCards().first().format()}"

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
