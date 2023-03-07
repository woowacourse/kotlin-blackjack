package blackjack.view

import blackjack.domain.GameResult
import blackjack.domain.PlayerResults
import blackjack.dto.HandDTO
import blackjack.dto.HandsDTO
import blackjack.dto.ScoreDTO
import blackjack.dto.ScoresDTO

object OutputView {
    private const val SEPARATOR = ", "

    fun printInitialHands(dto: HandsDTO) {
        println("${dto.dealerHand.name}와 ${dto.playerHands.joinToString(SEPARATOR) { it.name }}에게 2장의 카드를 나누었습니다.")
        printHands(dto)
    }

    private fun printHands(dto: HandsDTO) {
        printHand(dto.dealerHand)
        dto.playerHands.forEach(::printHand)
    }

    fun printHand(dto: HandDTO) {
        println("${dto.name} 카드: ${dto.hand.joinToString(SEPARATOR)}")
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
