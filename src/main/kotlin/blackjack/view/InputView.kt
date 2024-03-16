package blackjack.view

object InputView {
    tailrec fun getNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readln().split(",").map { it.trim() }
        val isNotDuplicated = input.toSet().size == input.size
        val isNotEmpty = input.all { it.isNotEmpty() }
        return if (isNotDuplicated && isNotEmpty) input else getNames()
    }

    tailrec fun getBetAmount(name: String): Long {
        println("\n${name}의 배팅 금액은?")
        val input = readln().toLong()
        return if (input > 0) {
            input
        } else {
            println("배팅 금액은 0 이상이어야 합니다")
            getBetAmount(name)
        }
    }

    tailrec fun askPickAgain(name: String): Boolean {
        println("${name}은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln()) {
            "y" -> true
            "n" -> false
            else -> askPickAgain(name)
        }
    }
}
