package blackjack.view

import blackjack.model.Player
import blackjack.view.user.UserDecision
import blackjack.view.user.UserInputValidator

object InputView {
    private const val INPUT_MESSAGE_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INPUT_MESSAGE_PLAYER_DRAW = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val COMMA = ","

    fun inputPlayers(): List<Player> {
        println(INPUT_MESSAGE_PLAYER_NAMES)
        val input = readlnOrNull().orEmpty()
        val validatorPlayers = UserInputValidator.checkPlayers(input.split(COMMA))
        return validatorPlayers ?: inputPlayers()
    }

    fun inputPlayerDecision(playerName: String): UserDecision {
        println(INPUT_MESSAGE_PLAYER_DRAW.format(playerName))
        val input = readlnOrNull().orEmpty()
        val validatorDecision = UserInputValidator.checkUserDecision(input)
        return validatorDecision ?: inputPlayerDecision(playerName)
    }
}
