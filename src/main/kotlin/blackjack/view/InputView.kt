package blackjack.view

import blackjack.domain.model.participant.Player

class InputView {
    fun askForPlayersName(): List<String> {
        println(INPUT_PLAYERS_NAME)
        return validateBlankList(
            readln()
                .split(DELIMITER)
                .map { it.trim() },
        ) ?: askForPlayersName()
    }

    fun askForPlayerBetAmount(player: Player): Int {
        println(INPUT_PLAYERS_BET_AMOUNT.format(player.name))
        return toInteger(readln()) ?: askForPlayerBetAmount(player)
    }

    fun askForHitOrStay(player: Player): String {
        println(INPUT_HIT_OR_STAY.format(player.name))
        return changeInput(readln()) ?: askForHitOrStay(player)
    }

    companion object {
        private const val INPUT_PLAYERS_NAME: String = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val INPUT_PLAYERS_BET_AMOUNT: String = "\n%s의 배팅 금액은?"
        private const val DELIMITER: String = ","
        private const val INPUT_HIT_OR_STAY: String = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

        private val yesValidInput: List<String> = listOf("y", "Y")
        private val noValidInput: List<String> = listOf("n", "N")

        private fun toInteger(input: String): Int? {
            return input.toIntOrNull()
        }

        private fun changeInput(input: String): String? {
            if (input !in yesValidInput + noValidInput) return null
            return if (input in yesValidInput) {
                yesValidInput.first()
            } else {
                noValidInput.first()
            }
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
