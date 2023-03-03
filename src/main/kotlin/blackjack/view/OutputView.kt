package blackjack.view

import blackjack.dto.HandsDTO

object OutputView {
    private const val GAME_SET_UP = "%s와 %s에게 2장의 카드를 나누었습니다."
    private const val DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."

    fun printSetUp(dto: HandsDTO) {
        println(GAME_SET_UP.format(dto.dealerHand.name, dto.playerHands.joinToString(", ") { it.name }))
        printSetUpCards(dto)
        printInterval()
    }

    private fun printSetUpCards(dto: HandsDTO) {
        printHand(dto.dealerHand.name, dto.dealerHand.hand)
        dto.playerHands.forEach { (name, hand) -> printHand(name, hand) }
    }

    private fun printHand(name: String, hands: List<String>) {
        println("$name: ${hands.joinToString(", ")}")
    }

    fun printDealerHit() {
        println(DEALER_HIT_MESSAGE)
    }

    private fun printInterval() = println()
}
