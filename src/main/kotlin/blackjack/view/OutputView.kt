package blackjack.view

import blackjack.dto.HandsDTO

object OutputView {

    // 딜러와 pobi, jason에게 2장의 나누었습니다.
    // 딜러: 3다이아몬드
    // pobi카드: 2하트, 8스페이드
    // jason카드: 7클로버, K스페이드

    private const val GAME_SET_UP = "%s와 %s에게 2장의 카드를 나누었습니다."

    fun printSetUp(dto: HandsDTO) {
        println(GAME_SET_UP.format(dto.dealerName, dto.playerHands.keys.joinToString(", ")))
        printSetUpCards(dto)
        printInterval()
    }

    private fun printSetUpCards(dto: HandsDTO) {
        printHand(dto.dealerName, dto.dealerHand)
        dto.playerHands.forEach { (name, hand) -> printHand(name, hand) }
    }

    private fun printHand(name: String, hands: List<String>) {
        println("$name: ${hands.joinToString(", ")}")
    }

    private fun printInterval() = println()
}
