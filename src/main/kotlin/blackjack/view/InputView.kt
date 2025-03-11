package blackjack.view

class InputView {
    fun getPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",").map { name -> name.trim() }
    }

    fun getIsDrawMore(name: String): Boolean {
        println("\n${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln().lowercase()) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("[ERROR] 올바르지 않은 입력입니다.")
        }
    }
}
