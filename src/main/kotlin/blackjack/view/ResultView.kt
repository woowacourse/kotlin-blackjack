package blackjack.view

import blackjack.view.model.DealerSummary
import blackjack.view.model.PlayerResult
import blackjack.view.model.PlayerSummary

class ResultView {
    fun showDealing(
        playersName: List<String>,
        dealerCards: List<String>,
        playersCards: List<List<String>>,
    ) {
        println("\n${playersName.joinToString()}에게 2장씩 나누었습니다.")
        println("딜러가 한 장을 오픈했습니다.")
        println("딜러: ${dealerCards.joinToString()}")
        playersName.zip(playersCards).forEach { (name, cardsContent) ->
            println("${name}카드: ${cardsContent.joinToString()}")
        }
        println()
    }

    fun showPlayerCard(
        name: String,
        cards: List<String>,
        score: Int,
    ) {
        println("${name}카드: ${cards.joinToString()}, 점수 : $score")
    }

    fun showDealerHit() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun showParticipantsSummary(
        dealerSummary: DealerSummary,
        playerSummaries: List<PlayerSummary>,
    ) {
        println("\n$dealerSummary")
        playerSummaries.forEach { playerSummary -> println("$playerSummary") }
        println()
    }

    fun showProfit(playersResult: List<PlayerResult>) {
        println("## 최종 수익")
        println("딜러: ${playersResult.sumOf { -it.profit }}")
        playersResult.forEach { playerResult ->
            println("${playerResult.name}: ${playerResult.profit}")
        }
    }
}
