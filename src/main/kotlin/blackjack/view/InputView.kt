package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Identification
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.model.Wallet
import blackjack.view.user.UserDecision

object InputView {
    fun makeParticipants(): Participants {
        val dealer = Dealer()
        val players = inputPlayers()
        return checkParticipants(dealer, players) ?: makeParticipants()
    }

    fun inputPlayerDecision(playerName: String): UserDecision {
        println(INPUT_MESSAGE_PLAYER_DRAW.format(playerName))
        val input = readlnOrNull().orEmpty()
        val validatorDecision = checkUserDecision(input)
        return validatorDecision ?: inputPlayerDecision(playerName)
    }

    private fun checkParticipants(
        dealer: Dealer,
        players: List<Player>,
    ): Participants? {
        return try {
            Participants(
                dealer = dealer,
                players = players,
            )
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }

    private fun checkPlayers(input: List<String>): List<String>? {
        return try {
            checkPlayerNameDuplication(input)
            return input
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }

    private fun checkUserDecision(input: String): UserDecision? {
        return try {
            UserDecision.getUserDecision(input)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }

    private fun checkPlayerNameDuplication(playerNames: List<String>) {
        require(playerNames.size == playerNames.toSet().size) {
            Player.ERROR_DUPLICATION_NAME
        }
    }

    private fun inputPlayers(): List<Player> {
        val playerNames = inputPlayerNames()
        val bettingMoneys =
            playerNames.map { playerName ->
                inputPlayerMoney(playerName)
            }
        return playerNames.zip(bettingMoneys).map { (name, money) ->
            Player(
                Wallet(
                    identification = Identification(name),
                    money = money,
                ),
            )
        }
    }

    private fun inputPlayerNames(): List<String> {
        println(INPUT_MESSAGE_PLAYER_NAMES)
        val input = readlnOrNull().orEmpty()
        val validatorPlayers = checkPlayers(input.split(COMMA))
        return validatorPlayers ?: inputPlayerNames()
    }

    private fun inputPlayerMoney(playerName: String): Int {
        println(INPUT_MESSAGE_MONEY.format(playerName))
        return readlnOrNull()?.toIntOrNull().also {
            if (it == null) println(ERROR_INACCURATE_MONEY)
        } ?: inputPlayerMoney(playerName)
    }

    private const val ERROR_INACCURATE_MONEY = "잘못 된 베팅 금액입니다."
    private const val INPUT_MESSAGE_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INPUT_MESSAGE_PLAYER_DRAW = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val INPUT_MESSAGE_MONEY = "%s의 배팅 금액은?"
    private const val COMMA = ","
}
