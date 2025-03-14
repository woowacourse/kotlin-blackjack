package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.gameResult.PlayerResult
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player
import blackjack.domain.score.Score
import blackjack.view.blackjackView.format

object OutputView {
    fun printFinalCards(game: BlackJackGame) {
        println(printDealerCard(game.dealer) + printTotalSum(game.dealer))

        game.players.forEach { player ->
            println(printPlayerCard(player) + printTotalSum(player))
        }
    }

    fun printGameResult(playerResults: List<PlayerResult>) {
        println(FINAL_RESULT_NOTICE)

        val dealerProfit = playerResults.sumOf { it.getProfit() } * -1
        println(printDealerResult(dealerProfit))

        playerResults.forEach {
            println(printPlayerResult(it))
        }
    }

    fun printOnGlobalExceptionOccur(msg: String?) {
        println(ON_GLOBAL_EXCEPTION_OCCUR)
        println(msg)
    }

    fun printOnException(throwable: Throwable) {
        println(throwable.message)
    }

    private const val FINAL_RESULT_NOTICE = "\n##최종 수익"

    private const val ON_GLOBAL_EXCEPTION_OCCUR = "오류입니다 게임을 다시 시도해 주세요"

    private fun printDealerCard(dealer: Dealer): String = "딜러: ${dealer.getCards().format()}"

    private fun printPlayerCard(player: Player): String = "${player.name}카드: ${player.getCards().format()}"

    private fun printTotalSum(participant: Participant): String = " - 결과: ${Score(participant)}\""

    private fun printDealerResult(dealerProfit: Int): String = "딜러: $dealerProfit"

    private fun printPlayerResult(playerResult: PlayerResult): String =
        "${playerResult.state.participant.name}: ${playerResult.getProfit()}"
}
