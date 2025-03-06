package blackjack.view

import blackjack.domain.model.Card
import blackjack.domain.model.Dealer
import blackjack.domain.model.Player
import blackjack.domain.model.Verdict

class OutputView {
    fun requestPlayerNames() {
        println(MESSAGE_ENTER_PLAYER_NAMES)
    }

    fun requestPlayerAction(player: Player) {
        println(MESSAGE_ENTER_PLAYER_YES_OR_NO.format(player.name))
    }

    fun printInitialDeals(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println(
            MESSAGE_INITIAL_HAND_DISTRIBUTED.format(
                dealer.name,
                players.map(Player::name).joinToString(PLAYER_CARDS_DELIMITER),
                2,
            ),
        )
    }

    fun printPlayerStatus(player: Player) {
        println(buildPlayerStatus(player))
    }

    fun printPlayerResult(player: Player) {
        println(buildPlayerStatus(player) + PLAYER_RESULT_DELEIMITER + player.getScore())
    }

    fun printDealerHitsState() {
        println(MESSAGE_DEALER_HITS_STATE)
    }

    fun printResultsHeader() {
        println(MESSAGE_RESULTS_HEADER)
    }

    fun printDealerVerdicts(
        dealer: Dealer,
        verdicts: Map<Verdict, Int>,
    ) {
        print(dealer.name + NAME_RESULT_DELIMITER)
        verdicts.forEach { (verdict, count) ->
            if (count > 0) print("${count}${verdict.value} ")
        }
        println()
    }

    fun printPlayerVerdict(
        player: Player,
        verdict: Verdict,
    ) {
        println(player.name + NAME_RESULT_DELIMITER + verdict.value)
    }

    private fun buildPlayerStatus(player: Player): String {
        var status = ""
        status += player.name + PLAYER_NAME_STATUS_DELIMITER
        status +=
            player.cards.joinToString(PLAYER_CARDS_DELIMITER) { card: Card ->
                card.rank.value + card.suit.value
            }
        return status
    }

    companion object {
        private const val MESSAGE_ENTER_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val MESSAGE_ENTER_PLAYER_YES_OR_NO = "%s은(는) 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"
        private const val MESSAGE_INITIAL_HAND_DISTRIBUTED = "%s와(과) %s에게 %s장의 카드를 나누었습니다."
        private const val MESSAGE_DEALER_HITS_STATE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val MESSAGE_RESULTS_HEADER = "## 최종 승패"

        private const val PLAYER_CARDS_DELIMITER = ", "
        private const val PLAYER_NAME_STATUS_DELIMITER = " 카드: "
        private const val PLAYER_RESULT_DELEIMITER = " - 결과: "
        private const val NAME_RESULT_DELIMITER = ": "
    }
}
