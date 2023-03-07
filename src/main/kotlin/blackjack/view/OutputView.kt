package blackjack.view

import blackjack.domain.Result
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participants
import blackjack.domain.player.Player

class OutputView {

    fun printCurrentPlayerCards(player: Player, sumResultMessage: String = "") {
        val cardsWord: String = player.cards.values.joinToString(", ") {
            it.number.word + it.shape.word
        }
        println("${player.name} 카드: $cardsWord $sumResultMessage")
    }

    fun printDealerHitMessage() {
        println()
        println(DEALER_HIT_MESSAGE)
        println()
    }

    fun printDealerNotHitMessage() {
        println()
        println(DEALER_NOT_HIT_MESSAGE)
        println()
    }

    fun printInitialSettingCard(dealer: Dealer, participants: Participants) {
        val participantsNames: List<String> = participants.values.map { it.name }
        println(INITIAL_SETTING_CARD_MESSAGE.format(participantsNames.joinToString(", ")))
        printFirstRoundDealerCards(dealer)
        printParticipantsCards(participants)
    }

    fun printPlayersResults(dealer: Dealer, participants: Participants) {
        println()
        println(FINAL_RESULT_MESSAGE)

        var dealerResultMessage: String = "${dealer.name}: "
        if (dealer.results[Result.WIN] != 0) dealerResultMessage += "${dealer.results[Result.WIN]}${Result.WIN.word} "
        if (dealer.results[Result.LOSE] != 0) dealerResultMessage += "${dealer.results[Result.LOSE]}${Result.LOSE.word} "
        if (dealer.results[Result.DRAW] != 0) dealerResultMessage += "${dealer.results[Result.DRAW]}${Result.DRAW.word} "

        println(dealerResultMessage)
        participants.values.forEach { println("${it.name}: ${it.result.word}") }
    }

    fun printSumResult(dealer: Dealer, participants: Participants) {
        printCurrentPlayerCards(dealer, "- 결과: ${dealer.cards.sum()}")
        participants.values.forEach {
            printCurrentPlayerCards(it, "- 결과: ${it.cards.sum()}")
        }
    }

    private fun printParticipantsCards(participants: Participants) {
        participants.values.forEach { printCurrentPlayerCards(it) }
        println()
    }

    private fun printFirstRoundDealerCards(dealer: Dealer) {
        val dealerFirstCard = dealer.cards.values[0]
        println("${dealer.name} 카드: ${dealerFirstCard.number.word}${dealerFirstCard.shape.word}")
    }

    companion object {
        private const val INITIAL_SETTING_CARD_MESSAGE = "딜러와 %s에게 각각 2장의 카드를 나누었습니다."
        private const val DEALER_HIT_MESSAGE = "딜러는 16 이하라 한장의 카드를 더 받았습니다."
        private const val DEALER_NOT_HIT_MESSAGE = "딜러는 16 초과라 한장의 카드를 더 받지않았습니다."
        private const val FINAL_RESULT_MESSAGE = "## 최종 승패"
    }
}
