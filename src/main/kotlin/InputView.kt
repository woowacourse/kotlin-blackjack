object InputView {

    fun requestPlayersName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.")

        return readln().split(",")
    }

    fun requestAdditionalDraw(player: Player): String {
        println("%s는 한장의 카드를 더 받겠습니까?".format(player.name))

        return readln()
    }
}
