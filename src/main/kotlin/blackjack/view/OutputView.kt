package blackjack.view

import blackjack.domain.Result
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants

class OutputView {

    fun printSettingCard(dealer: Dealer, participants: Participants) {
        val participantsNames: List<String> = participants.values.map { it.name }
        println(SETTING_CARD_MENT.format(participantsNames.joinToString(", ")))
        printFirstRoundDealerCard(dealer)
        println()
        participants.values.forEach {
            printParticipantCards(it)
            println()
        }
    }

    fun printDealerHitCardMent() {
        println(DEALER_HIT_CARD_MENT)
    }

    fun printDealerNotHitCardMent() {
        println(DEALER_NOT_HIT_CARD_MENT)
    }

    fun printSumResult(dealer: Dealer, participants: Participants) {
        printFirstRoundDealerCard(dealer)
        println(SUM_RESULT_MENT.format(dealer.cards.sumCardsNumber()))
        participants.values.forEach {
            printParticipantCards(it)
            println(SUM_RESULT_MENT.format(it.cards.sumCardsNumber()))
        }
    }

    fun printFinalResult(dealer: Dealer, participants: Participants) {
        println(FINAL_RESULT_MENT)
        var dealerResultMessage = FINAL_DEALER_RESULT_MENT
        if (dealer.results[Result.WIN] != 0) dealerResultMessage += "${dealer.results[Result.WIN]} ${Result.WIN.word}"
        if (dealer.results[Result.LOSE] != 0) dealerResultMessage += "${dealer.results[Result.LOSE]} ${Result.LOSE.word}"
        if (dealer.results[Result.DRAW] != 0) dealerResultMessage += "${dealer.results[Result.DRAW]} ${Result.DRAW.word}"
        println(dealerResultMessage)
        participants.values.forEach {
            FINAL_PARTICIPANT_RESULT_MENT.format(it.name, it.result.word)
        }
    }

    private fun printFirstRoundDealerCard(dealer: Dealer) {
        val dealerCard = dealer.cards.values[0]
        print(DEALER_CARD_MENT.format(dealerCard.number.word + dealerCard.shape.word))
    }

    private fun printParticipantCards(participant: Participant) {
        val cardsWord: String = participant.cards.values.map {
            it.number.word + it.shape.word
        }.joinToString(", ")
        print("${participant.name} 카드: $cardsWord")
    }

    companion object {
        private const val SETTING_CARD_MENT = "딜러와 %s에게 2장의 나누었습니다."
        private const val DEALER_CARD_MENT = "딜러 카드: %s"
        private const val DEALER_HIT_CARD_MENT = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val DEALER_NOT_HIT_CARD_MENT = "딜러는 16 초과라 한장의 카드를 더 받지않았습니다."
        private const val SUM_RESULT_MENT = " - 결과: %d"
        private const val FINAL_RESULT_MENT = "## 최종 승패"
        private const val FINAL_DEALER_RESULT_MENT = "딜러: "
        private const val FINAL_PARTICIPANT_RESULT_MENT = "%s: %s"
    }
}
