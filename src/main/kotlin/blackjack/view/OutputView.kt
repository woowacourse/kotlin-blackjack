package blackjack.view

import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants
import blackjack.domain.player.Player
import blackjack.domain.result.PlayerProfit

class OutputView {

    fun printSetupCards(dealer: Dealer, participants: Participants) {
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

    fun printScoreResult(dealer: Dealer, participants: Participants) {
        println()
        printPlayerCardsSumResult(dealer)
        println(SUM_RESULT_MENT.format(dealer.cards.calculateScore()))
        participants.values.forEach {
            printPlayerCardsSumResult(it)
            print(SUM_RESULT_MENT.format(it.cards.calculateScore()))
            println()
        }
    }

    fun printParticipantCards(participant: Participant) {
        val cardsWord: String = participant.cards.values.joinToString(", ") {
            it.number.word + it.shape.word
        }
        println("${participant.name} 카드: $cardsWord")
    }

    fun printPlayersProfit(
        dealerProfit: PlayerProfit,
        participantsProfit: List<PlayerProfit>
    ) {
        println()
        println(FINAL_RESULT_MENT)
        println(FINAL_PARTICIPANT_RESULT_MENT.format(dealerProfit.player.name, dealerProfit.profit))
        participantsProfit.forEach {
            println(FINAL_PARTICIPANT_RESULT_MENT.format(it.player.name, it.profit))
        }
    }

    private fun printFirstRoundDealerCard(dealer: Dealer) {
        val dealerCard = dealer.cards.values[0]
        print(DEALER_CARD_MENT.format(dealerCard.number.word + dealerCard.shape.word))
    }

    private fun printPlayerCardsSumResult(player: Player) {
        val cardsWord: String = player.cards.values.joinToString(", ") {
            it.number.word + it.shape.word
        }
        print("${player.name} 카드: $cardsWord")
    }

    companion object {
        private const val SETTING_CARD_MENT = "딜러와 %s에게 2장의 카드를 나누었습니다."
        private const val DEALER_CARD_MENT = "딜러 카드: %s"
        private const val DEALER_HIT_CARD_MENT = "딜러는 16 이하라 한장의 카드를 더 받았습니다."
        private const val SUM_RESULT_MENT = " - 결과: %d"
        private const val FINAL_RESULT_MENT = "## 최종 수익"
        private const val FINAL_PARTICIPANT_RESULT_MENT = "%s: %d"
    }
}
