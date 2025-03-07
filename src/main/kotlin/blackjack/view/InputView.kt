package blackjack.view

import blackjack.model.domain.Player

class InputView {
    fun askForPlayersName(): List<String> {
        println(INPUT_PLAYERS_NAME)
        return validateBlankList(
            readln()
                .split(DELIMITER)
                .map { it.trim() },
        ) ?: askForPlayersName()
    }

    fun askForHitOrStay(player: Player): String {
        println(INPUT_HIT_OR_STAY.format(player.name))
        return validateBlank(readln()) ?: askForHitOrStay(player)
    }

    companion object {
        private const val INPUT_PLAYERS_NAME: String = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val DELIMITER: String = ","
        private const val INPUT_HIT_OR_STAY: String = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

        private fun validateBlank(input: String): String? {
            if (input.isEmpty()) return null
            return input
        }

        private fun validateBlankList(input: List<String>): List<String>? {
            input.forEach {
                validateBlank(it) ?: return null
            }
            return input
        }
    }
}
