package blackjack.view

import blackjack.domain.model.Dealer
import blackjack.domain.model.Deck.Companion.START_CARD_COUNT
import blackjack.domain.model.Participant
import blackjack.domain.model.Player
import blackjack.domain.model.Result

class OutputView {
    fun printInitialDeals(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println(
            MESSAGE_INITIAL_HAND_DISTRIBUTED.format(
                dealer.name,
                players.map(Player::name).joinToString(PLAYER_CARDS_DELIMITER),
                START_CARD_COUNT,
            ),
        )
    }

    fun printParticipantStatus(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println(renderDealerStatus(dealer))
        players.forEach { player ->
            println(renderParticipantStatus(player))
        }
    }

    fun printPlayerStatus(player: Player) {
        println(renderParticipantStatus(player))
    }

    fun printPlayerResult(participant: Participant) {
        print(renderParticipantStatus(participant))
        println(PLAYER_RESULT_DELIMITER + participant.computePoint())
    }

    private fun renderDealerStatus(dealer: Dealer): String {
        return dealer.name + PLAYER_NAME_STATUS_DELIMITER +
            dealer.showHand(DEALER_VISIBLE_CARD_COUNT)
                .joinToString { it.rank.value + it.suit.value }
    }

    private fun renderParticipantStatus(participant: Participant): String {
        return participant.name + PLAYER_NAME_STATUS_DELIMITER +
            participant.showHand()
                .joinToString { it.rank.value + it.suit.value }
    }

    fun printDealerHitsState() {
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
        results.filter { it.value > 0 }.forEach { (result, count) ->
            print("${count}${resultToString(result)} ")
        }
        println()
    }

    fun printPlayerResult(
        player: Player,
        result: Result,
    ) {
        println(player.name + NAME_RESULT_DELIMITER + resultToString(result))
    }

    fun printErrorMessage(message: String) {
        println(message)
    }

    private fun resultToString(result: Result): String {
        return when (result) {
            Result.WIN -> RESULT_WIN
            Result.LOSE -> RESULT_LOSE
            Result.DRAW -> RESULT_DRAW
        }
    }

    companion object {
        private const val MESSAGE_INITIAL_HAND_DISTRIBUTED = "%s와(과) %s에게 %s장의 카드를 나누었습니다."
        private const val MESSAGE_DEALER_HITS_STATE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val MESSAGE_RESULTS_HEADER = "## 최종 승패"
        private const val DEALER_VISIBLE_CARD_COUNT = 1
        private const val PLAYER_CARDS_DELIMITER = ", "
        private const val PLAYER_NAME_STATUS_DELIMITER = " 카드: "
        private const val PLAYER_RESULT_DELIMITER = " - 결과: "
        private const val NAME_RESULT_DELIMITER = ": "
        private const val RESULT_WIN = "승"
        private const val RESULT_LOSE = "패"
        private const val RESULT_DRAW = "무"
    }
}
