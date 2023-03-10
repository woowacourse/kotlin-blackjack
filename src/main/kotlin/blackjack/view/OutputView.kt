package blackjack.view

import blackjack.domain.Result
import blackjack.domain.player.Dealer
import blackjack.domain.player.DealerResult
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants
import blackjack.domain.player.ParticipantsResults
import blackjack.domain.player.Player

class OutputView {

    fun printSettingCard(dealer: Dealer, participants: Participants) {
        val participantsNames: List<String> = participants.values.map { it.name }
        println(SETTING_CARD_MENT.format(participantsNames.joinToString(", ")))
        printFirstRoundDealerCard(dealer)
        println()
        participants.values.forEach {
            printParticipantCards(it)
        }
    }

    fun printDealerHitCardMent() {
        println(DEALER_HIT_CARD_MENT)
    }

    fun printSumResult(dealer: Dealer, participants: Participants) {
        println()
        printPlayerCardsSumResult(dealer)
        println(SUM_RESULT_MENT.format(dealer.cards.sumCardsNumber()))
        participants.values.forEach {
            printPlayerCardsSumResult(it)
            print(SUM_RESULT_MENT.format(it.cards.sumCardsNumber()))
            println()
        }
    }

    fun printFinalResult(participantsResults: ParticipantsResults, dealerResult: DealerResult) {
        println()
        println(FINAL_RESULT_MENT)
        val dealerResultMessage = FINAL_DEALER_RESULT_MENT + printDealerFinalResut(dealerResult)
        println(dealerResultMessage)
        participantsResults.results.forEach {
            println(FINAL_PARTICIPANT_RESULT_MENT.format(it.result.first, it.result.second.word))
        }
    }

    private fun printDealerFinalResut(dealerResult: DealerResult): String {
        var dealerResultMessage = ""
        val dealerWinCount = dealerResult.results.count { it == Result.WIN }
        val dealerLoseCount = dealerResult.results.count { it == Result.LOSE }
        val dealerDrawCount = dealerResult.results.count { it == Result.DRAW }
        if (dealerWinCount != 0) dealerResultMessage += "${dealerWinCount}${Result.WIN.word} "
        if (dealerLoseCount != 0) dealerResultMessage += "${dealerLoseCount}${Result.LOSE.word} "
        if (dealerDrawCount != 0) dealerResultMessage += "${dealerDrawCount}${Result.DRAW.word} "
        return dealerResultMessage
    }

    fun printParticipantCards(participant: Participant) {
        val cardsWord: String = participant.cards.values.map {
            it.number.word + it.shape.word
        }.joinToString(", ")
        println("${participant.name} 카드: $cardsWord")
    }

    fun printNoCardMessage() {
        println(NO_CARD_MESSAGE)
    }

    private fun printFirstRoundDealerCard(dealer: Dealer) {
        val dealerCard = dealer.cards.values[0]
        print(DEALER_CARD_MENT.format(dealerCard.number.word + dealerCard.shape.word))
    }

    private fun printPlayerCardsSumResult(player: Player) {
        val cardsWord: String = player.cards.values.map {
            it.number.word + it.shape.word
        }.joinToString(", ")
        print("${player.name} 카드: $cardsWord")
    }

    companion object {
        private const val SETTING_CARD_MENT = "딜러와 %s에게 2장의 카드를 나누었습니다."
        private const val DEALER_CARD_MENT = "딜러 카드: %s"
        private const val DEALER_HIT_CARD_MENT = "딜러는 16 이하라 한장의 카드를 더 받았습니다."
        private const val SUM_RESULT_MENT = " - 결과: %d"
        private const val FINAL_RESULT_MENT = "## 최종 승패"
        private const val FINAL_DEALER_RESULT_MENT = "딜러: "
        private const val FINAL_PARTICIPANT_RESULT_MENT = "%s: %s"
        private const val NO_CARD_MESSAGE = "더 이상 발급할 수 있는 카드가 없습니다."
    }
}
