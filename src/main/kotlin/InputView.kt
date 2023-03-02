object InputView {

    fun requestPlayersName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.")

        return readln().split(",")
    }
}
