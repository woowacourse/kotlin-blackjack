package blackjack.view

import blackjack.model.BettingMoney
import blackjack.model.Player
import blackjack.view.user.UserDecision
import blackjack.view.user.UserInputValidator

object InputView {
    private const val INPUT_MESSAGE_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INPUT_MESSAGE_PLAYER_DRAW = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val INPUT_MESSAGE_BETTING_MONEY = "%s의 배팅 금액은?"
    private const val COMMA = ","

    fun inputPlayers(): List<Player> {
        println(INPUT_MESSAGE_PLAYER_NAMES)
        val input = readlnOrNull().orEmpty()
        val result = UserInputValidator.checkPlayers(input.split(COMMA))

        return result.getOrElse { throwable ->
            println("${throwable.message}")
            inputPlayers()
        }
    }

    fun inputPlayerDecision(playerName: String): UserDecision {
        println(INPUT_MESSAGE_PLAYER_DRAW.format(playerName))
        val input = readlnOrNull().orEmpty()
        val result = UserInputValidator.checkUserDecision(input)

        return result.getOrElse { throwable ->
            println("${throwable.message}")
            inputPlayerDecision(playerName)
        }
    }

    fun inputPlayerBettingMoney(playerName: String): BettingMoney {
        println(INPUT_MESSAGE_BETTING_MONEY.format(playerName))
        val input = readlnOrNull().orEmpty().toDoubleOrNull() ?: 0.0
        val result = UserInputValidator.checkBettingMoney(input)

        return result.getOrElse { throwable ->
            println("${throwable.message}")
            inputPlayerBettingMoney(playerName)
        }
    }
}
