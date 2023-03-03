package view

import domain.Name
import domain.Names
import domain.Player

class InputView {
    fun readNames(): Names {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readln()
        return Names(input.split(",").map { Name(it.trim()) })
    }

    fun readChoiceOfAddCard(player: Player): String {
        println("${player.name.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val input = readln()
        return input
    }
}
