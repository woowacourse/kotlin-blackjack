package blackjack.view

import blackjack.model.ParticipantName

object InputView {
    private const val INPUT_MESSAGE_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INPUT_MESSAGE_DRAW_DECISION = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val NO_INPUT = ""
    private const val DELIMITER_NAMES = ","

    fun inputPlayersName(): List<ParticipantName> {
        println(INPUT_MESSAGE_PLAYER_NAMES)
        return try {
            val playersName = (readlnOrNull() ?: NO_INPUT).split(DELIMITER_NAMES).map { name -> name.trim() }
            playersName.map { name -> ParticipantName(name) }
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            inputPlayersName()
        }
    }

    fun inputDrawDecision(name: ParticipantName): String {
        println(INPUT_MESSAGE_DRAW_DECISION.format(name))
        return readlnOrNull() ?: NO_INPUT
    }
}
