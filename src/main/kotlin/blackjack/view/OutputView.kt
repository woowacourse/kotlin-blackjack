package blackjack.view

import blackjack.domain.GameResult
import blackjack.dto.HandDTO
import blackjack.dto.HandsDTO
import blackjack.dto.ResultDTO
import blackjack.dto.ResultsDTO
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

    fun printResults(dto: ResultsDTO) {
        println("## 최종 승패")
        printDealerResult(dto)
        dto.results.forEach(::printPlayerResult)
    }

    private fun printDealerResult(dto: ResultsDTO) {
        val win = dto.results.count { it.result == GameResult.패.name }
        val draw = dto.results.count { it.result == GameResult.무.name }
        val lose = dto.results.count { it.result == GameResult.승.name }
        println("딜러: ${win}승 ${draw}무 ${lose}패")
    }

    private fun printPlayerResult(dto: ResultDTO) {
        println("${dto.name}: ${dto.result}")
    }

    fun printInterval() = println()
}
