package view

class InitView {
    fun readPlayerNames(): List<String> {
        println(MESSAGE_READ_PLAYER_NAME)
        val input = readln().trim()
        val names = input.split(",")
        return names
    }

    companion object {
        private const val MESSAGE_READ_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    }
}
