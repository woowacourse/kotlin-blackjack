package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.toDisplayName

object OutputView {
    fun showInitialCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("딜러와 ${players.joinToString { it.name }}에게 2장을 나누었습니다.\n")
        println("딜러: ${dealer.cards.first()}")
        players.forEach { player ->
            printPlayerCards(player)
        }
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString()}")
    }

    fun printDealerHaveAdditionalCard() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
    }

    fun printFinalCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("딜러 카드: ${dealer.cards.joinToString()} - 결과: ${dealer.totalSum}")

        players.forEach { player ->
            println("${player.name}카드: ${player.cards.joinToString()} - 결과: ${player.totalSum}")
        }
    }

    fun printGameResult(result: GameResult) {
        println("\n##최종 승패")
        println("딜러: ${result.dealerResult.win}승 ${result.dealerResult.lose}패 ${result.dealerResult.draw}무")

        result.getAllPlayerResult().forEach {
            println("${it.player.name}: ${it.status.toDisplayName()}")
        }
    }
}
