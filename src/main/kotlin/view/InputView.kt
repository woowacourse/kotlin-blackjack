package view

class InputView {
    fun inputPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readlnOrNull()?.split(",")?.map { it.trim() } ?: emptyList()
    }

    fun readHitOrStand(playerName: String): Boolean {
        println("${playerName}는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val answer = readln().lowercase()
        return when (answer) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("[ERROR] y나 n만 입력해주세요.")
        }
    }
}
