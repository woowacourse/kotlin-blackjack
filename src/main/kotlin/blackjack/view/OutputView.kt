package blackjack.view

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participants
import blackjack.domain.player.Player
import blackjack.domain.result.GameResult

class OutputView {

    fun printPlayerCards(player: Player, sumResultMessage: String = "") {
        val cardsWord: String = player.cards.values.joinToString(", ") {
            it.number.toText() + it.shape.toEmoji()
        }
        println("${player.name} 카드: $cardsWord $sumResultMessage")
    }

    fun printDealerHitOrNotMessage(hitOrNot: Boolean) {
        println(
            if (hitOrNot) DEALER_HIT_MESSAGE
            else DEALER_NOT_HIT_MESSAGE
        )
        println()
    }

    fun printFirstTurnSettingCards(dealer: Dealer, participants: Participants) {
        val participantsNames: List<String> = participants.values.map { it.name }
        println(INITIAL_SETTING_CARD_MESSAGE.format(participantsNames.joinToString(", ")))
        printFirstRoundDealerCards(dealer)
        printParticipantsCards(participants)
    }

    fun printPlayersResults(dealer: Dealer, participants: Participants) {
        println()
        println(FINAL_RESULT_MESSAGE)

        val dealerResultMessage: StringBuilder = StringBuilder("${dealer.name}: ")
        val dealerGameResults = dealer.matchResult.getResults()
        if (dealerGameResults[GameResult.WIN] != 0) dealerResultMessage.append("${dealerGameResults[GameResult.WIN]}${GameResult.WIN.toText()} ")
        if (dealerGameResults[GameResult.LOSE] != 0) dealerResultMessage.append("${dealerGameResults[GameResult.LOSE]}${GameResult.LOSE.toText()} ")
        if (dealerGameResults[GameResult.DRAW] != 0) dealerResultMessage.append("${dealerGameResults[GameResult.DRAW]}${GameResult.DRAW.toText()} ")
        println(dealerResultMessage.toString())

        participants.values.forEach { println("${it.name}: ${it.matchResult.getResult().toText()}") }
    }

    fun printSumResult(dealer: Dealer, participants: Participants) {
        printPlayerCards(dealer, "- 결과: ${dealer.cards.sum()}")
        participants.values.forEach {
            printPlayerCards(it, "- 결과: ${it.cards.sum()}")
        }
    }

    private fun printParticipantsCards(participants: Participants) {
        participants.values.forEach { printPlayerCards(it) }
        println()
    }

    private fun printFirstRoundDealerCards(dealer: Dealer) {
        val dealerFirstCard = dealer.cards.values[0]
        println("${dealer.name} 카드: ${dealerFirstCard.number.toText()}${dealerFirstCard.shape.toEmoji()}")
    }

    private fun CardShape.toEmoji(): String {
        return when (this) {
            CardShape.HEART -> "♥️"
            CardShape.DIAMOND -> "♦️"
            CardShape.SPADE -> "♠️"
            CardShape.CLOVER -> "♣️"
        }
    }

    private fun CardNumber.toText(): String {
        return when (this) {
            CardNumber.ONE -> "A"
            CardNumber.TWO -> "2"
            CardNumber.THREE -> "3"
            CardNumber.FOUR -> "4"
            CardNumber.FIVE -> "5"
            CardNumber.SIX -> "6"
            CardNumber.SEVEN -> "7"
            CardNumber.EIGHT -> "8"
            CardNumber.NINE -> "9"
            CardNumber.TEN -> "10"
            CardNumber.KING -> "K"
            CardNumber.QUEEN -> "Q"
            CardNumber.JACK -> "J"
        }
    }

    private fun GameResult.toText(): String {
        return when (this) {
            GameResult.WIN -> "승"
            GameResult.BLACKJACK -> "블랙잭"
            GameResult.DRAW -> "무"
            GameResult.LOSE -> "패"
        }
    }

    companion object {
        private const val INITIAL_SETTING_CARD_MESSAGE = "딜러와 %s에게 각각 2장의 카드를 나누었습니다."
        private const val DEALER_HIT_MESSAGE = "딜러는 16 이하라 한장의 카드를 더 받았습니다."
        private const val DEALER_NOT_HIT_MESSAGE = "딜러는 16 초과라 한장의 카드를 더 받지않았습니다."
        private const val FINAL_RESULT_MESSAGE = "## 최종 승패"
    }
}
