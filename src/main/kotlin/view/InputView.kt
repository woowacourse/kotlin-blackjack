package view

import controller.BlackjackController

object InputView {
    private const val PRINT_PARTICIPANT_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val PRINT_QUESTION_CARD_PICK_FORM = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val SEPARATOR = ","

    fun inputPlayerNames(): List<String> {
        println(PRINT_PARTICIPANT_NAMES)
        val input = readln().trim()
        return input.split(SEPARATOR)
    }

    fun inputRepeatGetCard(name: String): String? {
        println(PRINT_QUESTION_CARD_PICK_FORM.format(name))
        val input = readln().lowercase()

        return if (validateAnswer(input)) input else null
    }

    private fun validateAnswer(answer: String) = (answer == BlackjackController.YES_ANSWER || answer == BlackjackController.NO_ANSWER)
}
