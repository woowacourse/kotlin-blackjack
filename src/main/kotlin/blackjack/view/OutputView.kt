package blackjack.view

import blackjack.domain.model.Dealer
import blackjack.domain.model.Participant
import blackjack.domain.model.Participants
import blackjack.domain.model.Player
import blackjack.domain.model.Rank
import blackjack.domain.model.Result
import blackjack.domain.model.Suit

class OutputView {
    fun printInitialDeals(participants: Participants) {
        println(
            MESSAGE_INITIAL_HAND_DISTRIBUTED.format(
                participants.dealer.name,
                participants.players.map(Player::name).joinToString(PLAYER_CARDS_DELIMITER),
            ),
        )
        println()
    }

    fun printParticipantStatus(participant: Participant) {
        println(renderParticipantStatus(participant))
        println()
    }

    fun printDealerHit() {
        println(MESSAGE_DEALER_HITS_STATE)
    }

    fun printResults(participants: Participants) {
        participants.list.forEach { participant -> printParticipantResult(participant) }
        println(MESSAGE_RESULTS_HEADER)
        val playerResults = participants.dealer.getPlayerResults(participants.players)
        val dealerResults = participants.dealer.getDealerResults(playerResults)
        printDealerResults(participants.dealer, dealerResults)
        playerResults.forEach { (player, result) -> printParticipantResult(player, result) }
    }

    private fun printParticipantResult(participant: Participant) {
        print(renderParticipantStatus(participant))
        println(PLAYER_RESULT_DELIMITER + participant.computePoint())
    }

    private fun renderParticipantStatus(participant: Participant): String {
        return participant.name + PLAYER_NAME_STATUS_DELIMITER +
            participant.showHand()
                .joinToString { card -> card.rank.stringRepresentation() + card.suit.stringRepresentation() }
    }

    private fun printDealerResults(
        dealer: Dealer,
        results: Map<Result, Int>,
    ) {
        print(dealer.name + NAME_RESULT_DELIMITER)
        results.filter { result -> result.value > 0 }.forEach { (result, count) ->
            print("${count}${result.stringRepresentation()} ")
        }
        println()
    }

    private fun printParticipantResult(
        player: Player,
        result: Result,
    ) {
        println(player.name + NAME_RESULT_DELIMITER + result.stringRepresentation())
    }

    private fun Result.stringRepresentation(): String {
        return when (this) {
            Result.WIN -> RESULT_WIN
            Result.LOSE -> RESULT_LOSE
            Result.DRAW -> RESULT_DRAW
        }
    }

    private fun Suit.stringRepresentation(): String {
        return when (this) {
            Suit.HEART -> SUIT_HEART
            Suit.DIAMOND -> SUIT_DIAMOND
            Suit.CLUB -> SUIT_CLUB
            Suit.SPADE -> SUIT_SPADE
        }
    }

    private fun Rank.stringRepresentation(): String {
        return when (this) {
            Rank.ACE -> RANK_ACE
            Rank.JACK -> RANK_JACK
            Rank.QUEEN -> RANK_QUEEN
            Rank.KING -> RANK_KING
            else -> point.toString()
        }
    }

    companion object {
        private const val MESSAGE_INITIAL_HAND_DISTRIBUTED =
            "%s와(과) %s에게 ${Participant.INITIAL_DRAW_COUNT}장의 카드를 나누었습니다."
        private const val MESSAGE_DEALER_HITS_STATE = "딜러는 ${Dealer.HIT_THRESHOLD}점 이하라 한 장의 카드를 더 받았습니다."
        private const val MESSAGE_RESULTS_HEADER = "\n## 최종 승패"
        private const val PLAYER_CARDS_DELIMITER = ", "
        private const val PLAYER_NAME_STATUS_DELIMITER = " 카드: "
        private const val PLAYER_RESULT_DELIMITER = " - 결과: "
        private const val NAME_RESULT_DELIMITER = ": "

        private const val RESULT_WIN = "승"
        private const val RESULT_LOSE = "패"
        private const val RESULT_DRAW = "무"

        private const val SUIT_HEART = "하트"
        private const val SUIT_DIAMOND = "다이아몬드"
        private const val SUIT_SPADE = "스페이드"
        private const val SUIT_CLUB = "클로버"

        private const val RANK_ACE = "A"
        private const val RANK_JACK = "J"
        private const val RANK_QUEEN = "Q"
        private const val RANK_KING = "K"
    }
}
