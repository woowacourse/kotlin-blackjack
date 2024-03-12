package blackjack.view

class PlayersNameInputView {
    fun read(): List<String> {
        println(READ_MESSAGE)
        return readln().split(DELIMITER)
    }

    companion object {
        private const val READ_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val DELIMITER = ","
    }
}
