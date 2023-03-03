package view

object InputView {
    fun inputPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readln().trim()
        return input.split(",")
    }

    fun inputRepeatGetCard(name: String): String? {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val input = readln().lowercase()

        return if (validateAnswer(input)) input else null
    }

    private fun validateAnswer(answer: String) = (answer == "y" || answer == "n")
}
