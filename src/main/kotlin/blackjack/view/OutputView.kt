package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players

class OutputView {
    fun printInitialCardsState(
        dealer: Dealer,
        players: Players,
    ) {
        println("딜러와 ${players.players.joinToString { it.name }}에게 2장을 나누었습니다.")
        val dealerCard = dealer.getVisibleCard()
        println("딜러: ${dealerCard.rank.displayName} ${dealerCard.suit.displayName}")

        players.players.forEach { player ->
            println("${player.name}카드: ${player.state.hand.cards.joinToString { "${it.rank.displayName}${it.suit.displayName}" }}")
        }

        println()
    }

    fun printPlayerResult(player: Player) {
        println(
            "${player.name}카드: ${
                player.state.hand.cards.joinToString {
                    "${it.rank.displayName} ${it.suit.displayName}"
                }
            }",
        )
    }

    fun printDealerDrawCard() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printDealerResult(dealer: Dealer) {
        println(
            "딜러 카드: ${
                dealer.state.hand.cards.joinToString {
                    "${it.rank.displayName} ${it.suit.displayName}"
                }
            } - 결과: ${dealer.state.hand.getTotalScore()}",
        )
    }

    fun printFinalResults(
        dealer: Dealer,
        players: Players,
    ) {
        println("\n## 최종 결과")

        printDealerResult(dealer)

        players.players.forEach { player ->
            println(
                "${player.name}카드: ${
                    player.state.hand.cards.joinToString {
                        "${it.rank.displayName} ${it.suit.displayName}"
                    }
                } - 결과: ${player.state.hand.getTotalScore()}",
            )
        }
    }

    fun printFinalProfit(
        dealer: Dealer,
        players: Players,
    ) {
        println("\n## 최종 수익")

        val dealerProfit = -players.players.sumOf { it.profit.toInt() }
        println("딜러: $dealerProfit")

        players.players.forEach { player ->
            println("${player.name}: ${player.profit.toInt()}")
        }
    }
}
