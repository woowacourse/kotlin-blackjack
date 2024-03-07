package blackjack.controller

import blackjack.view.showPlayerDrawMessage
import blackjack.view.showPlayersNameReadMessage

object BlackJackController {
    fun run() {
        val playersName = readPlayersName()
        drawOrNot(askPlayerDraw())
    }

    private fun drawOrNot(drawOrNot: String?): Boolean {
        if (drawOrNot == "y") return true
        return false
    }

    private fun readPlayersName(): List<String>? {
        showPlayersNameReadMessage()
        return try {
            readlnOrNull()?.split(' ')?.map { it.trim() }
        } catch (e: IllegalArgumentException) {
            println("플레이어는 최소 1명 이상이어야 합니다.")
            readPlayersName()
        }
    }

    private fun askPlayerDraw(): String? {
        showPlayerDrawMessage()
        return try {
            val drawMessage = readlnOrNull()?.trim()?.lowercase()
            require(drawMessage == "y" || drawMessage == "n")
            drawMessage
        } catch (e: IllegalArgumentException) {
            println("플레이어의 이름을 입력해주세요.")
            askPlayerDraw()
        }
    }
}
