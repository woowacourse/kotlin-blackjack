package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Players

class OutputView {
    fun printInitialCardsState(
        dealer: Dealer,
        players: Players,
    ) {
        println("딜러와 ${players.players.joinToString { it.name }}에게 2장씩 나누었습니다.")

        println("딜러: ${dealer.state.hand.cards.joinToString { "${it.rank} ${it.suit}" }}")
        players.players.forEach { player ->
            println("${player.name}카드: ${player.state.hand.cards.joinToString { "${it.rank} ${it.suit}" }}")
        }
        println()
    }

    fun printPlayerHand(
        playerName: String,
        hand: blackjack.domain.Hand,
    ) {
        println("${playerName}카드: ${hand.cards.joinToString { "${it.rank} ${it.suit}" }}")
    }

    fun printDealerHand(dealer: Dealer) {
        println("딜러 카드: ${dealer.state.hand.cards.joinToString { "${it.rank} ${it.suit}" }}")
    }

    fun printFinalResult(
        dealer: Dealer,
        players: Players,
    ) {
        val dealerTotalScore = dealer.state.hand.getTotalScore()

        println("\n## 최종 수익")
        players.players.forEach { player ->
            val playerTotalScore = player.state.hand.getTotalScore()
            val profit = player.state.profit(dealerTotalScore)

            println("${player.name}: $profit")
        }

        val dealerProfit = -players.players.sumOf { it.state.profit(dealerTotalScore) }
        println("딜러: $dealerProfit")
    }
}
