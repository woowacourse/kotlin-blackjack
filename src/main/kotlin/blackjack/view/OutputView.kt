package blackjack.view

import blackjack.domain.model.Dealer
import blackjack.domain.model.Participant
import blackjack.domain.model.Player
import blackjack.domain.model.Rank
import blackjack.domain.model.Result
import blackjack.domain.model.Suit

class OutputView {
    fun printInitialDeals(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println(
            MESSAGE_INITIAL_HAND_DISTRIBUTED.format(
                dealer.name,
                players.map(Player::name).joinToString(PLAYER_CARDS_DELIMITER),
                Participant.INITIAL_DRAW_COUNT,
            ),
        )
        println()
    }

    fun printParticipantStatus(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println(renderDealerStatus(dealer))
        players.forEach { player ->
            println(renderParticipantStatus(player))
        }
        println()
    }

    fun printPlayerStatus(player: Player) {
        println(renderParticipantStatus(player))
        println()
    }

    fun printPlayerResult(participant: Participant) {
        print(renderParticipantStatus(participant))
        println(PLAYER_RESULT_DELIMITER + participant.computePoint())
    }

    private fun renderDealerStatus(dealer: Dealer): String {
        return dealer.name + PLAYER_NAME_STATUS_DELIMITER +
            dealer.showHand()
                .joinToString { card -> card.rank.stringRepresentation() + card.suit.stringRepresentation() }
    }

    private fun renderParticipantStatus(participant: Participant): String {
        return participant.name + PLAYER_NAME_STATUS_DELIMITER +
            participant.showHand()
                .joinToString { card -> card.rank.stringRepresentation() + card.suit.stringRepresentation() }
    }

    fun printDealerHit() {
        println(MESSAGE_DEALER_HITS_STATE)
    }

    fun printResultsHeader() {
        println(MESSAGE_RESULTS_HEADER)
    }

    fun printDealerResults(
        dealer: Dealer,
        results: Map<Result, Int>,
    ) {
        print(dealer.name + NAME_RESULT_DELIMITER)
        results.filter { result -> result.value > 0 }.forEach { (result, count) ->
            print("${count}${result.stringRepresentation()} ")
        }
        println()
    }

    fun printPlayerResult(
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

    private companion object {
        private const val MESSAGE_INITIAL_HAND_DISTRIBUTED = "%s와(과) %s에게 %s장의 카드를 나누었습니다."
        private const val MESSAGE_DEALER_HITS_STATE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
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
