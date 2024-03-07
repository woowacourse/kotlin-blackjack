package blackjack.view

class InputView {
    fun readPlayersName(): List<String> {
        println(READ_PLAYERS_NAME_MESSAGE)
        return readln().split(PLAYERS_NAME_DELIMITER)
    }

    companion object {
        private const val PLAYERS_NAME_DELIMITER = ","
        private const val READ_PLAYERS_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    }
}
