package blackjack.view

object InputView {
    private const val INPUT_MESSAGE_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val NO_NAME_INPUT = ""
    private const val DELIMITER_NAMES = ","

    fun inputPlayerNames(): List<String> {
        println(INPUT_MESSAGE_PLAYER_NAMES)
        return (readlnOrNull() ?: NO_NAME_INPUT).split(DELIMITER_NAMES).map { name -> name.trim() }
    }
}