package blackjack.view

class InputView {
    fun readPlayersName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readln()
        require(input.isNotBlank()) { "이름은 공백일 수 없습니다." }

        return input.split(",").map { it.trim() }
    }
}
