package blackjack.view

import Player

object InputView {
    private const val INVALID_PLAYER_NAME = "[ERROR] 공백이 아닌 플레이어의 이름을 입력해주세요."
    private const val INVALID_DRAW_DECISION = "[ERROR] 카드를 더 받을지 말지는 y 또는 n으로 입력해주세요."
    private const val GAME_START = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val DRAW_ASK = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val STAY_DECISION = "n"
    const val DRAW_DECISION = "y"

    private fun showPlayersNameReadMessage() {
        println(GAME_START)
    }

    private fun showPlayerDrawDecision(player: Player) {
        println(DRAW_ASK.format(player.name))
    }

    fun judgePlayersDraw(player: Player): String? {
        showPlayerDrawDecision(player)
        return try {
            val drawDecision = readlnOrNull()?.trim()?.lowercase()
            require(drawDecision == DRAW_DECISION || drawDecision == STAY_DECISION) { INVALID_DRAW_DECISION }
            drawDecision
        } catch (e: IllegalArgumentException) {
            println(e.message)
            judgePlayersDraw(player)
        }
    }

    fun readPlayersName(): List<String> {
        showPlayersNameReadMessage()
        return try {
            val playersName = readln().split(',').map { it.trim() }
            require(playersName.all { it.isNotBlank() }) { INVALID_PLAYER_NAME }
            playersName
        } catch (e: IllegalArgumentException) {
            println(e.message)
            readPlayersName()
        }
    }
}
