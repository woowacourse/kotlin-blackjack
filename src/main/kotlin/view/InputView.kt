package view

class InputView {
    fun inputPlayerNames(): List<String> {
        println(GAME_PLAYERS_NAME_INPUT_MESSAGE)
        return readlnOrNull()?.split(",")?.map { it.trim() } ?: emptyList()
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

    fun inputBetAmount(playerNames: List<String>): List<Float> {
        return playerNames.map { name ->
            println("${name}의 배팅 금액은?")
            readln().toFloat()
        }
    }

    companion object {
        private const val GAME_PLAYERS_NAME_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val YES = "y"
        private const val NO = "n"
    }
}
