package view

class InputView {
    fun inputPlayers(): List<String> {
        println(GAME_PLAYERS_NAME_INPUT_MESSAGE)
        return readlnOrNull()?.split(",")?.map { it.trim() } ?: emptyList()
    }

    fun inputBettingAmount(playerNames: List<String>): Map<String, Int> {
        return playerNames.associateWith { playerName ->
            println("${playerName}의 베팅 금액은?")
            readln().toIntOrNull() ?: 0
        }
    }

    fun readHitOrStand(playerName: String): Boolean {
        println("${playerName}는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val answer = readln().lowercase()
        return when (answer) {
            YES -> true
            NO -> false
            else -> throw IllegalArgumentException("[ERROR] y나 n만 입력해주세요.")
        }
    }

    companion object {
        private const val GAME_PLAYERS_NAME_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val YES = "y"
        private const val NO = "n"
    }
}
