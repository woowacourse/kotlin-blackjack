package blackjack.view

import blackjack.model.role.Player
import blackjack.model.role.PlayerName

class InputView {
    fun readPlayersName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readln()
        require(input.isNotBlank()) { "이름은 공백일 수 없습니다." }

        return input.split(",").map { it.trim() }
    }

    fun readMoney(playerName: PlayerName): Long {
        println("$playerName 의 배팅 금액은?")
        val amount = readln().toLongOrNull()
        requireNotNull(amount) { "제대로 된 금액 형식을 입력하세요" }
        return amount
    }

    fun readIsHit(player: Player): Boolean {
        println()
        println(
            player.name.name + "는 한장의 카드를 더 받겠습니까?" +
                "(${Decision.YES.output}는 ${Decision.YES.input}, " +
                "${Decision.NO.output}는 ${Decision.NO.input})",
        )
        return when (readln()) {
            Decision.YES.input -> true
            Decision.NO.input -> false
            else -> throw IllegalArgumentException("잘못된 형식을 입력했습니다.")
        }
    }
}
