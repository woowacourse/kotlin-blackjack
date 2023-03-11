package view

import domain.GameResult
import model.Cards
import model.Participant
import model.Participants
import model.Rank
import model.Suit

class OutputView {

    fun printNoticeDistributeCards(participants: Participants) {
        println()
        println(noticeDistributeCardsNameFormat(participants))
        participants.all.forEach { printPlayerStatus(it) }
        println()
    }

    private fun noticeDistributeCardsNameFormat(participants: Participants): String {
        return MESSAGE_DISTRIBUTE_CARD.format(
            participants.all.filter { !it.isDealer() }
                .joinToString(", ") { it.name.value }
        )
    }

    fun printDealerGetCard(participant: Participant) {
        println()
        println(MESSAGE_DEALER_GET_CARD.format(participant.name.value))
    }

    fun printPlayerStatus(participant: Participant) {
        println(
            MESSAGE_PARTICIPANT_STATUS.format(
                participant.name.value,
                participant.getFirstOpenCards().getCardsFormat()
            )
        )
    }

    fun printAllPlayerStatusResult(participants: Participants) {
        println()
        participants.forEach {
            print(MESSAGE_PARTICIPANT_STATUS.format(it.name.value, it.cards.getCardsFormat()))
            println(MESSAGE_POINT_RESULT.format(it.cards.sum()))
        }
        printGameResult(participants)
    }

    private fun printGameResult(participants: Participants) {
        val gameResult = GameResult(participants)
        val playerResult = gameResult.playersResult
        val dealerResult = gameResult.dealerResult
        println()
        println(MESSAGE_RESULT_TITLE)
        println(MESSAGE_PROFIT_RESULT.format(participants.dealer.name.value, dealerResult))
        playerResult.forEach {
            println(MESSAGE_PROFIT_RESULT.format(it.key.value, it.value))
        }
    }

    private fun Cards.getCardsFormat(): String {
        return this.cards.joinToString(prefix = "", postfix = "", separator = ", ") {
            rankFormat(it.rank) + suitFormat(it.suit)
        }
    }

    private fun rankFormat(rank: Rank): String =
        when (rank) {
            Rank.ACE -> "A"
            Rank.KING -> "K"
            Rank.QUEEN -> "Q"
            Rank.JACK -> "J"
            else -> rank.score.toString()
        }

    private fun suitFormat(suit: Suit): String =
        when (suit) {
            Suit.CLOVER -> "클로버"
            Suit.DIAMOND -> "다이아몬드"
            Suit.SPADE -> "스페이드"
            Suit.HEART -> "하트"
        }

    companion object {
        private const val MESSAGE_DISTRIBUTE_CARD = "딜러와 %s에게 2장의 나누었습니다."
        private const val MESSAGE_PARTICIPANT_STATUS = "%s카드: %s"
        private const val MESSAGE_POINT_RESULT = " - 결과: %d"
        private const val MESSAGE_DEALER_GET_CARD = "%s는 16이하라 한장의 카드를 더 받았습니다."
        private const val MESSAGE_RESULT_TITLE = "## 최종 수익"
        private const val MESSAGE_PROFIT_RESULT = "%s: %d"
    }
}
