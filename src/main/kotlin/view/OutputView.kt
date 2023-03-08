package view

import model.Cards
import model.Dealer
import model.Participant
import model.Participants
import model.Player
import model.Players
import model.Rank
import model.Result
import model.Suit

class OutputView {

    fun printNoticeDistributeCards(participants: Participants) {
        println()
        println(noticeDistributeCardsNameFormat(participants))
        participants.participants.forEach { printPlayerStatus(it) }
        println()
    }

    private fun noticeDistributeCardsNameFormat(participants: Participants): String {
        return MESSAGE_DISTRIBUTE_CARD.format(
            participants.participants.filter { !it.isDealer() }
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

    fun printAllPlayerStatusResult(participants: List<Participant>) {
        println()
        participants.forEach {
            print(MESSAGE_PARTICIPANT_STATUS.format(it.name.value, it.cards.getCardsFormat()))
            println(MESSAGE_POINT_RESULT.format(it.cards.sum()))
        }
    }

    fun printGameResult(participants: Participants) {
        val dealerResult = (participants.dealer as Dealer).getFinalResult(Participants(participants.players))
        val playerResult = Players(participants.players.map { it as Player }).getGameResult(participants.dealer as Dealer)
        println()
        println(MESSAGE_RESULT_TITLE)
        println(MESSAGE_DEALER_RESULT.format(dealerResult[Result.WIN], dealerResult[Result.LOSE]))
        participants.players.forEach {
            println(MESSAGE_PLAYER_RESULT.format(it.name.value, if (playerResult[it.name] == Result.WIN) "승" else "패"))
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
        private const val MESSAGE_RESULT_TITLE = "## 최종 승패"
        private const val MESSAGE_DEALER_RESULT = "딜러: %d승 %d패"
        private const val MESSAGE_PLAYER_RESULT = "%s: %s"
    }
}
