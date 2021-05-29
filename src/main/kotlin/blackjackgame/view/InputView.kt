package blackjackgame.view

import java.lang.IllegalArgumentException

fun inputPlayerNames() : List<String> {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    val names = readLine() ?: throw IllegalArgumentException()
    return names.split(",")
}