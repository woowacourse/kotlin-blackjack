package view

import domain.BettingMoney
import domain.Name
import domain.Names
import domain.Player

class InputView {
    fun readNames(): Names {
        println(INPUT_PLAYERS_NAME)
        val input = readlnOrNull() ?: return readNames()
        return Names(input.split(NAME_DELIMITERS).map { Name(it.trim()) })
    }

    fun readChoiceOfAddCard(player: Player): Boolean {
        println("${player.name.value}" + INPUT_ADDITIONAL_CARD)
        val input = readlnOrNull() ?: return readChoiceOfAddCard(player)
        when (input) {
            YES -> return true
            NO -> return false
        }
        return readChoiceOfAddCard(player)
    }

    fun readBettingMoney(name: Name): BettingMoney {
        println("${name.value}" + INPUT_BETTING_MONEY)
        val input = readln().toIntOrNull() ?: return readBettingMoney(name)
        return BettingMoney(input)
    }

    companion object {
        private const val INPUT_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val NAME_DELIMITERS = ","
        private const val INPUT_ADDITIONAL_CARD = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val INPUT_BETTING_MONEY = "의 베팅 금액은?"
        private const val YES = "y"
        private const val NO = "n"
    }
}
