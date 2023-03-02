package blackjack.view

class InputView {
    fun getPlayerNames(): List<String> {
        return runCatching {
            println(GET_PLAYER_NAME_SCRIPT)
            readln().split(",").map { it.trim() }
        }.getOrElse {
            println(it.message)
            getPlayerNames()
        }
    }

    companion object {
        private const val GET_PLAYER_NAME_SCRIPT = "게임에 참가할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    }
}
