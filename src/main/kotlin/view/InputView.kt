package view

class InputView {
    fun inputPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readlnOrNull()?.split(",")?.map { it.trim() } ?: emptyList()
    }

    fun readHitOrStand(): String {
        return readlnOrNull()?.trim()?.lowercase() ?: ""
    }
}
