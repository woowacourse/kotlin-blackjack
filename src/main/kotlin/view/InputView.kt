package view

import model.participants.Money
import model.participants.Participant

object InputView {
    private const val HEADER_READ_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val HEADER_READ_ANSWER = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val HEADER_READ_BET_MONEY = "%s의 배팅 금액은?"
    private const val ERROR_INVALID_FORMAT = "y 또는 n만 입력해주세요"
    private const val YES = "y"
    private const val NO = "n"

    fun readPlayerNames(): List<String> {
        println(HEADER_READ_PLAYER_NAMES)
        return readln().split(",").map(String::trim)
    }

    fun readPlayerMoneyByName(name: String): Money {
        println(HEADER_READ_BET_MONEY.format(name))
        return Money.fromInput(readln())
    }

    fun readHitDecision(participant: Participant): Boolean {
        println(HEADER_READ_ANSWER.format(participant.wallet.idCard.name))
        return when (val decision = readln().lowercase()) {
            YES -> true
            NO -> false
            else -> throw IllegalArgumentException(ERROR_INVALID_FORMAT)
        }
    }
}
