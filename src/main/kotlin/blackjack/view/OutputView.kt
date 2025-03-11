package blackjack.view

import blackjack.model.domain.GameResult
import blackjack.model.domain.card.Card
import blackjack.model.domain.card.Shape
import blackjack.model.domain.participant.Dealer
import blackjack.model.domain.participant.Participants
import blackjack.model.domain.participant.Player
import blackjack.model.domain.participant.PlayerGroup

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

    fun playerResult(statusCount: Map<Player, GameResult>) {
        statusCount.forEach { result ->
            println(PLAYER_STATUS.format(result.key.name, result.value.determineStatus()))
        }
        println()
    }

    fun dealerResult(
        dealer: Dealer,
        statusCount: Map<Player, GameResult>,
    ) {
        println(FINAL_RESULT)

        val winningCount = statusCount.count { it.value == GameResult.Lose }
        val losingCount = statusCount.count { it.value == GameResult.Win }
        val drawCount = statusCount.count { it.value == GameResult.Draw }

        val resultFormat: String = OUTPUT_DEALER_RESULT.format(dealer.name, winningCount, losingCount)

        if (drawCount != 0) {
            println(resultFormat + OUTPUT_DEALER_RESULT_DRAW.format(drawCount))
        } else {
            println(resultFormat)
        }
    }

    private fun GameResult.determineStatus(): String {
        return when (this) {
            GameResult.Win -> "승"
            GameResult.Draw -> "무"
            else -> "패"
        }
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
        private const val FINAL_RESULT: String = "\n## 최종 승패"
        private const val OUTPUT_DEALER_RESULT: String = "%s: %d승 %d패"
        private const val OUTPUT_DEALER_RESULT_DRAW: String = " %d무"
        private const val PLAYER_STATUS: String = "%s: %s"
        private const val CARD: String = "카드"
        private const val CARD_FORMAT: String = "%s%s"
    }
}
