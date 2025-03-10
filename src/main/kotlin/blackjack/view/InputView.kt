package blackjack.view

import blackjack.domain.UserChoice

class InputView {
    fun readPlayerNames(): List<String> {
        while (true) {
            println(MESSAGE_INPUT_PLAYER_NAME)
            val input = readln().trim()

            if (input.isBlank()) {
                println(MESSAGE_EMPTY_INPUT)
                continue
            }

            val names = input.split(",").map { it.trim() }
            if (names.any { it.isBlank() }) {
                println(MESSAGE_EMPTY_NAME)
                continue
            }
            return names
        }
    }

    fun readHitOrStay(name: String): UserChoice {
        while (true) {
            println(MESSAGE_ASK_PLAYER_HIT_OR_STAY.format(name))
            val input = readln().trim()

            if (input.isBlank()) {
                println(MESSAGE_EMPTY_INPUT)
                continue
            }
            return UserChoice.from(input)
        }
    }

    companion object {
        const val MESSAGE_INPUT_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        const val MESSAGE_ASK_PLAYER_HIT_OR_STAY = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

        const val MESSAGE_EMPTY_INPUT = "빈 값이 입력 되었습니다."
        const val MESSAGE_EMPTY_NAME = "빈 이름이 포함되어 있습니다."
    }
}
