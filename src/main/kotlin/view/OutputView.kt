package view

import model.Card
import model.Cards
import model.Dealer.Companion.DEALER
import model.GameResult
import model.Participant
import model.Participants
import model.Player
import model.Rank
import model.Suit

class OutputView {

    fun printNoticeDistributeCards(participants: Participants) {
        println()
        println(noticeDistributeCardsNameFormat(participants))
        printPlayersStatus(participants)
    }

    private fun noticeDistributeCardsNameFormat(participants: Participants): String {
        return MESSAGE_DISTRIBUTE_CARD.format(participants.participants.filter { it.name.value != DEALER }.joinToString(", ") { it.name.value })
    }

    fun printPlayersStatus(players: Participants) {
        players.participants.forEach { printPlayerStatus(it) }
        println()
    }

    fun printDealerGetCard() {
        println()
        println(MESSAGE_DEALER_GET_CARD)
    }

    fun printPlayerStatus(participant: Participant) {
        if (participant.name.value == DEALER) {
            println(MESSAGE_DEALER_STATUS.format(participant.cards.cards[0].getCardFormat()))
            return
        }
        println(MESSAGE_PARTICIPANT_STATUS.format(participant.name.value, (participant as Player).cards.getCardsFormat()))
    }

    fun printAllPlayerStatusResult(participants: List<Participant>) {
        println()
        participants.forEach {
            print(MESSAGE_PARTICIPANT_STATUS.format(it.name.value, it.cards.getCardsFormat()))
            println(MESSAGE_POINT_RESULT.format(it.cards.sum()))
        }
    }

    fun printFinalResult(gameResult: GameResult) {
        println()
        println(MESSAGE_RESULT_TITLE)
        println(MESSAGE_DEALER_RESULT.format(gameResult.getDealerWinResult(), gameResult.getDealerLoseResult()))
        gameResult.playersResult.forEach {
            println(MESSAGE_PLAYER_RESULT.format(it.key, if (it.value) "승" else "패"))
        }
    }

    private fun Card.getCardFormat(): String {
        return rankFormat(this.rank) + suitFormat(this.suit)
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
            else -> rank.getScore().toString()
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
        private const val MESSAGE_DEALER_STATUS = "딜러: %s"
        private const val MESSAGE_PARTICIPANT_STATUS = "%s카드: %s"
        private const val MESSAGE_POINT_RESULT = " - 결과: %d"
        private const val MESSAGE_DEALER_GET_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val MESSAGE_RESULT_TITLE = "## 최종 승패"
        private const val MESSAGE_DEALER_RESULT = "딜러: %d승 %d패"
        private const val MESSAGE_PLAYER_RESULT = "%s: %s"
    }
}
