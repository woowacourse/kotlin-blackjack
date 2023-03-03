package view

import domain.Answer
import domain.Name
import domain.Names

class InputView {
    fun readNames(): Names {
        println(INPUT_PLAYERS_NAME)
        val input = readln()
        return Names(input.split(NAME_DELIMITERS).map { Name(it.trim()) })
    }

    fun readChoiceOfAddCard(name: Name): Answer {
        println("${name.name}" + INPUT_ADDITIONAL_CARD)
        val input = readln()
        return Answer.of(input)
    }

    companion object {
        private const val INPUT_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val NAME_DELIMITERS = ","
        private const val INPUT_ADDITIONAL_CARD = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    }
}
