package blackjack.view

import blackjack.domain.Player

object InputView {

    private const val REQUEST_PLAYERS_NAME_MSG = "게임에 참여할 사람의 이름을 입력하세요."
    private const val TOKENIZER = ","
    private const val REQUEST_ADDITIONAL_CARDS_MSG = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    fun requestPlayersName(): List<String> {
        println(REQUEST_PLAYERS_NAME_MSG)

        return readln().split(TOKENIZER).map(String::trim)
    }

    fun requestAdditionalDraw(player: Player): String {
        println(REQUEST_ADDITIONAL_CARDS_MSG.format(player.name))

        return readln()
    }
}
