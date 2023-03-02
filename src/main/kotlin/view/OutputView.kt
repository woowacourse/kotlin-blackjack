package view

import model.GameResult
import model.Name
import model.Participant
import model.Player

class OutputView {
    fun printInputPlayerNames() {
        println(MESSAGE_INPUT_NAME)
    }

    fun printDistributeCards(players: List<Player>) {
        println()
        println(MESSAGE_DISTRIBUTE_CARD.format(players.joinToString(", ") { it.name.value }))
    }

    fun printGetCardMore(name: Name) {
        println(MESSAGE_INPUT_YES_OR_NO.format(name.value))
    }

    fun printPlayersStatus(players: List<Participant>) {
        for (player in players) {
            printPlayerStatus(player)
        }
        println()
    }

    fun printDealerGetCard() {
        println()
        println(MESSAGE_DEALER_GET_CARD)
    }

    fun printPlayerStatus(participant: Participant) {
        if (participant.name.value == "딜러") {
            println(MESSAGE_DEALER_STATUS.format(participant.cards.cards[0].toString()))
            return
        }
        println(MESSAGE_PARTICIPANT_STATUS.format(participant.name.value, (participant as Player).cards.toString()))
    }

    fun printAllPlayerStatusResult(participants: List<Participant>) {
        println()
        for (participant in participants) {
            print(MESSAGE_PARTICIPANT_STATUS.format(participant.name.value, participant.cards.toString()))
            println(MESSAGE_POINT_RESULT.format(participant.cards.sum()))
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

    companion object {
        private const val MESSAGE_INPUT_NAME = "게임에 참여할 플레이어의 이름을 입력하세요. (쉼표 기준으로 분리)"
        private const val MESSAGE_DISTRIBUTE_CARD = "딜러와 %s에게 2장의 나누었습니다."
        private const val MESSAGE_INPUT_YES_OR_NO = "%s는/은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val MESSAGE_DEALER_STATUS = "딜러: %s"
        private const val MESSAGE_PARTICIPANT_STATUS = "%s카드: %s"
        private const val MESSAGE_POINT_RESULT = " - 결과: %d"
        private const val MESSAGE_DEALER_GET_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val MESSAGE_RESULT_TITLE = "## 최종 승패"
        private const val MESSAGE_DEALER_RESULT = "딜러: %d승 %d패"
        private const val MESSAGE_PLAYER_RESULT = "%s: %s"
    }
}
