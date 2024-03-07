package blackjack.controller

import blackjack.view.showPlayersNameReadMessage

object BlackJackController {
    fun run() {
        val playersName = readPlayersName()
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
}
