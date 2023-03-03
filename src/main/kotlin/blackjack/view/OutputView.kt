package blackjack.view

import blackjack.domain.GameResult
import blackjack.dto.HandDTO
import blackjack.dto.HandsDTO
import blackjack.dto.ResultDTO
import blackjack.dto.ResultsDTO
import blackjack.dto.ScoreDTO
import blackjack.dto.ScoresDTO

object OutputView {
    private const val GAME_SET_UP_MESSAGE = "%s와 %s에게 2장의 카드를 나누었습니다."
    private const val DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val SCORE_BOARD_MESSAGE = "%s 카드: %s - 결과: %d"
    private const val FINAL_RESULT_MESSAGE = "## 최종 승패"

    fun printInitialHands(dto: HandsDTO) {
        println(GAME_SET_UP_MESSAGE.format(dto.dealerHand.name, dto.playerHands.joinToString(", ") { it.name }))
        printHands(dto)
        printInterval()
    }

    private fun printHands(dto: HandsDTO) {
        printHand(dto.dealerHand)
        dto.playerHands.forEach(::printHand)
    }

    private fun printHand(dto: HandDTO) {
        println("${dto.name} 카드: ${dto.hand.joinToString(", ")}")
    }

    fun printDealerHit() {
        println(DEALER_HIT_MESSAGE)
    }

    fun printScores(dto: ScoresDTO) {
        printScore(dto.dealerScore)
        dto.playersScore.forEach(::printScore)
    }

    private fun printScore(dto: ScoreDTO) {
        println(SCORE_BOARD_MESSAGE.format(dto.name, dto.hand.joinToString(", "), dto.score))
    }

    fun printResults(dto: ResultsDTO) {
        println(FINAL_RESULT_MESSAGE)
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

    private fun printInterval() = println()
}
