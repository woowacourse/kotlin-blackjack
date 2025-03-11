package blackjack.view

import blackjack.model.domain.participant.Player

class InputView {
    fun askForPlayersName(): List<String> {
        println(INPUT_PLAYERS_NAME)
        return validateBlankList(
            readln()
                .split(DELIMITER)
                .map { it.trim() },
        ) ?: askForPlayersName()
    }

    fun askForHitOrStay(player: Player): Boolean {
        println(INPUT_HIT_OR_STAY.format(player.name))
        return changeInput(readln()) ?: askForHitOrStay(player)
    }

    companion object {
        private const val INPUT_PLAYERS_NAME: String = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val DELIMITER: String = ","
        private const val INPUT_HIT_OR_STAY: String = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

        private val yesValidInput: List<String> = listOf("y", "Y")
        private val noValidInput: List<String> = listOf("n", "N")

        private fun changeInput(input: String): Boolean? {
            if (input !in yesValidInput + noValidInput) return null
            return input in yesValidInput
        }

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
