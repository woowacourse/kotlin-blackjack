package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.DealerResult
import blackjack.domain.Player
import blackjack.domain.PlayerResult
import blackjack.domain.toDisplayName

object OutputView {
    fun showInitialCards(game: BlackJackGame) {
        println("딜러와 ${game.players.joinToString { it.name }}에게 2장을 나누었습니다.\n")
        println("딜러: ${game.dealer.cards.first()}")
        game.players.forEach { player ->
            printPlayerCards(player)
        }
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString()}")
    }

    fun printDealerHaveAdditionalCard() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
    }

    fun printFinalCards(game: BlackJackGame) {
        println("딜러 카드: ${game.dealer.cards.joinToString()} - 결과: ${game.dealer.totalSum}")

        game.players.forEach { player ->
            println("${player.name}카드: ${player.cards.joinToString()} - 결과: ${player.totalSum}")
        }
    }

    fun printGameResult(
        playerResults: List<PlayerResult>,
        dealerResult: DealerResult,
    ) {
        println("\n##최종 승패")
        println("딜러: ${dealerResult.win}승 ${dealerResult.lose}패 ${dealerResult.draw}무")

        playerResults.forEach {
            println("${it.player.name}: ${it.status.toDisplayName()}")
        }
    }
}
