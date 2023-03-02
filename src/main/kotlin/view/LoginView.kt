package view

class LoginView {

    fun requestPlayerName(): List<String> {
        printRequestPlayerName()

        return readLine()?.split(SEPARATOR)?.map { name ->
            name.trim()
        } ?: emptyList()
    }

    private fun printRequestPlayerName() {
        println(REQUEST_PLAYER_NAME)
    }

    companion object {
        private const val REQUEST_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val SEPARATOR = ","
    }
}
