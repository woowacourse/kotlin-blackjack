package blackjack.view

import blackjack.model.Player

class InputView {
    fun inputPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",").map { it.trim() }
    }

    tailrec fun inputWhetherHit(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val input = readln()
        if (input == "y") {
            return true
        }
        if (input == "n") {
            return false
        }
        return inputWhetherHit(player)
    }
}
