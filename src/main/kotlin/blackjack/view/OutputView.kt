package blackjack.view

import blackjack.domain.model.Dealer
import blackjack.domain.model.Hands.Companion.START_CARD_COUNT
import blackjack.domain.model.Participant
import blackjack.domain.model.Participants
import blackjack.domain.model.Player
import blackjack.domain.model.Rank
import blackjack.domain.model.Suit
import blackjack.domain.model.VerdictResult

class OutputView {
    fun printInitialDeals(participants: Participants) {
        println(
            MESSAGE_INITIAL_HAND_DISTRIBUTED.format(
                participants.findDealer().name,
                participants.filterPlayers().map(Participant::name).joinToString(PLAYER_CARDS_DELIMITER),
                START_CARD_COUNT,
            ),
        )
    }

    fun printParticipantsStatus(participants: Participants) {
        participants.participants.forEach { participant ->
            println(renderParticipantsInitStatus(participant))
        }
    }

    private fun renderParticipantsInitStatus(participant: Participant): String {
        return participant.name + PLAYER_NAME_STATUS_DELIMITER +
            participant.showInitCards()
                .joinToString { convertKoreanRank(it.rank) + convertKoreanSuit(it.suit) }
    }

    fun printPlayerStatus(player: Participant) {
        println(renderParticipantsStatus(player))
    }

    fun printParticipantsResult(participants: Participants) {
        participants.participants.forEach { participant ->
            println(renderParticipantsStatus(participant) + PLAYER_RESULT_DELIMITER + participant.getScore())
        }
    }

    private fun renderParticipantsStatus(participant: Participant): String {
        return participant.name + PLAYER_NAME_STATUS_DELIMITER +
            participant.showCards()
                .joinToString { convertKoreanRank(it.rank) + convertKoreanSuit(it.suit) }
    }

    fun printDealerHitsState() {
        println(MESSAGE_DEALER_HITS_STATE)
    }

    fun printResultsHeader() {
        println(MESSAGE_RESULTS_HEADER)
    }

    fun printDealerVerdicts(dealer: Dealer) {
        print(dealer.name + NAME_RESULT_DELIMITER)
        dealer.getRecord().filter { it.value > 0 }.forEach { (verdict, count) ->
            print("${count}${convertKoreanVerdict(verdict)} ")
        }
        println()
    }

    fun printPlayersVerdict(players: List<Player>) {
        players.forEach { player ->
            println(player.name + NAME_RESULT_DELIMITER + convertKoreanVerdict(player.getCurrentVerdict()))
        }
    }

    fun printErrorMessage(message: String) {
        println(message)
    }

    private fun convertKoreanSuit(suit: Suit): String {
        return when (suit) {
            Suit.HEART -> "하트"
            Suit.SPADE -> "스페이드"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLUB -> "클로버"
        }
    }

    private fun convertKoreanRank(rank: Rank): String {
        return when (rank) {
            Rank.ACE -> "A"
            Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN -> rank.score.toString()
            Rank.JACK, Rank.QUEEN, Rank.KING -> Rank.TEN.score.toString()
        }
    }

    private fun convertKoreanVerdict(verdict: VerdictResult): String {
        return when (verdict) {
            VerdictResult.WIN -> "승"
            VerdictResult.LOSE -> "패"
            VerdictResult.DRAW -> "무"
        }
    }

    companion object {
        private const val MESSAGE_INITIAL_HAND_DISTRIBUTED = "%s와(과) %s에게 %s장의 카드를 나누었습니다."
        private const val MESSAGE_DEALER_HITS_STATE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val MESSAGE_RESULTS_HEADER = "## 최종 승패"
        private const val PLAYER_CARDS_DELIMITER = ", "
        private const val PLAYER_NAME_STATUS_DELIMITER = " 카드: "
        private const val PLAYER_RESULT_DELIMITER = " - 결과: "
        private const val NAME_RESULT_DELIMITER = ": "
    }
}
