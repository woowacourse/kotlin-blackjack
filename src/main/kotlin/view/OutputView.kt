package view

import model.Cards
import model.Dealer
import model.Participant
import model.Participants
import model.ParticipantsProfitResult
import model.Rank
import model.Suit

class OutputView {

    fun printNoticeDistributeCards(participants: Participants) {
        println()
        println(noticeDistributeCardsNameFormat(participants))
        participants.forEach {
            println(
                MESSAGE_PARTICIPANT_STATUS.format(
                    it.name.value,
                    it.getFirstOpenCards().getCardsFormat()
                )
            )
        }
        println()
    }

    private fun noticeDistributeCardsNameFormat(participants: Participants): String {
        return MESSAGE_DISTRIBUTE_CARD.format(
            participants.players.joinToString(", ") { it.name.value }
        )
    }

    fun printParticipantStatus(participant: Participant) {
        println()
        if (participant is Dealer) {
            println(MESSAGE_DEALER_GET_CARD.format(participant.name.value))
            return
        }
        println(
            MESSAGE_PARTICIPANT_STATUS.format(
                participant.name.value,
                participant.getFirstOpenCards().getCardsFormat()
            )
        )
    }

    fun printParticipantsStatus(participants: Participants) {
        println()
        participants.forEach {
            print(MESSAGE_PARTICIPANT_STATUS.format(it.name.value, it.cards.getCardsFormat()))
            println(MESSAGE_POINT_RESULT.format(it.cards.sum()))
        }
    }

    fun printGameResult(participantsProfitResult: ParticipantsProfitResult) {
        println()
        println(MESSAGE_RESULT_TITLE)
        participantsProfitResult.value.forEach {
            println(MESSAGE_PROFIT_RESULT.format(it.participant.name.value, it.profit.value))
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
