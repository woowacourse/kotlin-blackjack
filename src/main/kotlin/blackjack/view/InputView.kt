package blackjack.view

class InputView {
    fun readPlayersName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val players = readln().split(",").map { name: String -> name.trim() }
        return players
    }

    fun readPlayersBattingAmount(playerName: String): Int {
        println("\n${playerName}의 배팅 금액은?")
        return readln().toInt()
    }

    fun askDrawCard(playerName: String): Boolean {
        println("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)".format(playerName))
        return readln().trim().lowercase() == "y"
    }
}
