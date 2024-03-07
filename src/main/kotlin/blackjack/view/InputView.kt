package blackjack.view

const val GAME_START = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
const val DRAW_ASK = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

fun showPlayersNameReadMessage() {
    println(GAME_START)
}

fun showPlayerDrawMessage() {
    println(DRAW_ASK)
}
