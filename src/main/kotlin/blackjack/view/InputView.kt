package blackjack.view

import blackjack.model.HumanName

object InputView {
    fun inputParticipantsNames(): List<String> {
        return readln().split(",").map { it.trim() }
    }

    fun askHitOrStay(humanName: HumanName): String {
        println("${humanName}은(는) 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
        return readln()
    }
}
