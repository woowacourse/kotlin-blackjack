package blackjack.view

import blackjack.model.game.UserCommand
import blackjack.model.game.UserCommand.HIT
import blackjack.model.game.UserCommand.STAY
import blackjack.model.game.UserCommand.UNKNOWN
import blackjack.model.participant.Name

class InputView {
    fun getPlayers(): List<Name> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",").map { name -> Name(name.trim()) }
    }

    fun getIsRecieveMore(name: Name): UserCommand {
        println("\n${name.value}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln().lowercase()) {
            "y" -> HIT
            "n" -> STAY
            else -> UNKNOWN
        }
    }
}
