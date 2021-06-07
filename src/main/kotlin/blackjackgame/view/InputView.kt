package blackjackgame.view

fun inputPlayerNames(): List<String> {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    return readLine()?.split(",") ?: inputPlayerNames()
}

fun inputPlayerBetting(name: String): Int {
    println("${name}의 배팅 금액은?")
    return readLine()?.toInt() ?: inputPlayerBetting(name)
}

fun inputAskDrawCard(name: String): Boolean {
    println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    return readLine()?.convertYesOrNo() ?: inputAskDrawCard(name)

}

private fun String.convertYesOrNo(): Boolean? {
    if (this.toLowerCase() == "y") {
        return true
    }

    if (this.toLowerCase() == "n") {
        return false
    }

    return null
}
