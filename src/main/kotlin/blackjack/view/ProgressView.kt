package blackjack.view

const val GAME_SETTING = "딜러와 %s에게 2장을 나누었습니다."
const val DEALER_CARD = "딜러: %s"
const val PLAYER_CARD = "%s카드: %s"

fun setGame(names: String) {
    println(GAME_SETTING.format(names))
}
