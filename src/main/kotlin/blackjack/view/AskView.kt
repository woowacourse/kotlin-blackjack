package blackjack.view

import blackjack.view.model.PlayerConfig

class AskView {
    fun readPlayers(): List<PlayerConfig> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names: List<String> =
            readln()
                .splitToSequence(",")
                .filter { it.isNotBlank() }
                .map { name -> name.trim() }
                .toList()
        return names.map { name ->
            println("${name}의 베팅 금액은?")
            val bettingAmount = readln().toDouble()
            PlayerConfig(name, bettingAmount)
        }
    }

    fun askWantToHit(name: String): Boolean {
        println("${name}는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val input = readln()
        return input == "y"
    }
}
