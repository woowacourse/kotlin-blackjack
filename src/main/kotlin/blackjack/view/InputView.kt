package blackjack.view

object InputView {

    tailrec fun getNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readln().split(",")
        if (input.all { it.isNotEmpty() }) return input
        return getNames()
    }
}
