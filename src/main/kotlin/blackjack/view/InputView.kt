package blackjack.view

import blackjack.model.participant.Money
import blackjack.model.participant.Name
import blackjack.model.participant.PlayerAction
import blackjack.model.participant.PlayerAction.HIT
import blackjack.model.participant.PlayerAction.STAY
import blackjack.model.participant.PlayerAction.UNKNOWN

class InputView {
    fun getPlayers(): List<Name> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",").map { Name(it.trim()) }
    }

    fun getBettingMoney(name: Name): Money {
        println("\n${name}의 배팅 금액은?")
        return Money(readln().toDouble())
    }

    fun getIsReceiveMore(name: Name): PlayerAction {
        println("\n${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln().lowercase()) {
            "y" -> HIT
            "n" -> STAY
            else -> UNKNOWN
        }
    }
}
