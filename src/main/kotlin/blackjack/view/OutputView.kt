package blackjack.view

import blackjack.domain.Card
import blackjack.domain.GameResult
import blackjack.domain.PlayerResults
import blackjack.dto.ScoreDTO
import blackjack.dto.ScoresDTO

object OutputView {
    private const val SEPARATOR = ", "

    fun printFirstOpenCards(cards: Map<String, List<Card>>) {
        println("${cards.keys.first()}와 ${cards.keys.drop(1).joinToString(SEPARATOR)}에게 2장의 카드를 나누었습니다.")
        printCards(cards)
    }

    fun printCards(cards: Map<String, List<Card>>) {
        cards.forEach { (name, cards) ->
            println("$name 카드: ${cards.joinToString(SEPARATOR)}")
        }
    }

    fun printDealerHit() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printScores(dto: ScoresDTO) {
        printScore(dto.dealerScore)
        dto.playersScore.forEach(::printScore)
        printInterval()
    }

    private fun printScore(dto: ScoreDTO) {
        println("${dto.handDTO.name} 카드: ${dto.handDTO.hand.joinToString(SEPARATOR)} - 결과: ${dto.score}")
    }

    fun printResults(results: PlayerResults) {
        println("## 최종 승패")
        printDealerResult(results.getDealerResult())
        results.get().forEach { (name, result) -> printPlayerResult(name, result) }
    }

    private fun printDealerResult(result: Map<GameResult, Int>) {
        println("딜러: ${result[GameResult.WIN]}승 ${result[GameResult.DRAW]}무 ${result[GameResult.LOSE]}패")
    }

    private fun printPlayerResult(name: String, result: GameResult) {
        when (result) {
            GameResult.WIN -> println("$name: 승")
            GameResult.DRAW -> println("$name: 무")
            GameResult.LOSE -> println("$name: 패")
        }
    }

    fun printInterval() = println()
}
