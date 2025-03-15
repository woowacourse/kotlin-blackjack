package blackjack.view

import blackjack.domain.model.ProceedStatus
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Shape
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Participants
import blackjack.domain.model.participant.PlayerGroup

class OutputView {
    fun printInitCardStatus(playerGroup: PlayerGroup) {
        val playerName = playerGroup.players.joinToString { it.name }
        println(OUTPUT_DISTRIBUTE_CARD.format(playerGroup.dealer.name, playerName))
        printInitCard(playerGroup)
    }

    private fun printInitCard(playerGroup: PlayerGroup) {
        playerGroup.participants.forEach { participant ->
            val initCard = displayCard(participant.getInitCard())
            println(makeFormat(participant.name, initCard))
        }
        println()
    }

    fun printCardStatus(player: Participants) {
        val cards = displayCard(player.cardDeck)
        println(makeFormat(player.name, cards))
    }

    private fun displayCard(cards: List<Card>): String {
        return cards.joinToString { CARD_FORMAT.format(it.cardNumber.display, it.symbol.toKorean()) }
    }

    private fun makeFormat(
        playerName: String,
        cards: String,
    ): String {
        return PLAYER_STATUS.format(playerName + CARD, cards)
    }

    fun printDealerReceiveCard(
        count: Int,
        dealer: Dealer,
    ) {
        println()
        repeat(count) {
            println(OUTPUT_DEALER_RECEIVE_CARD.format(dealer.name))
        }
        println()
    }

    fun participantsCardResult(playerGroup: PlayerGroup) {
        playerGroup.participants.forEach { participant ->
            val cards = displayCard(participant.cardDeck)
            println(makeFormat(participant.name, cards) + OUTPUT_PARTICIPANTS_CARD_RESULT.format(participant.sumCardNumber))
        }
    }

    fun playerResult(proceedStatus: List<ProceedStatus>) {
        proceedStatus.forEach {
            println(getParticipantsResult(it))
        }
        println()
    }

    fun dealerResult(dealerResult: ProceedStatus) {
        println(FINAL_RESULT)
        println(getParticipantsResult(dealerResult))
    }

    private fun getParticipantsResult(proceedStatus: ProceedStatus): String {
        return PLAYER_STATUS.format(proceedStatus.participant.name, proceedStatus.proceed.amount.toString())
    }

    private fun Shape.toKorean(): String {
        return when (this) {
            Shape.Heart -> "하트"
            Shape.Spade -> "스페이드"
            Shape.Diamond -> "다이아몬드"
            Shape.Clover -> "클로버"
        }
    }

    companion object {
        private const val OUTPUT_DISTRIBUTE_CARD: String = "\n%s와 %s에게 2장의 나누었습니다."
        private const val OUTPUT_DEALER_RECEIVE_CARD: String = "%s는 16이하라 한장의 카드를 더 받았습니다."
        private const val OUTPUT_PARTICIPANTS_CARD_RESULT: String = " - 결과: %d"
        private const val FINAL_RESULT: String = "\n## 최종 수익"
        private const val PLAYER_STATUS: String = "%s: %s"
        private const val CARD: String = "카드"
        private const val CARD_FORMAT: String = "%s%s"
    }
}
