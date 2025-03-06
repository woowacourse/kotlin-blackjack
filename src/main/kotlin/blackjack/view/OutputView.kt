package blackjack.view

class OutputView {
    fun requestPlayerNames() {
        println(MESSAGE_ENTER_PLAYER_NAMES)
    }

    fun requestPlayerAction() {
        println(MESSAGE_ENTER_PLAYER_YES_OR_NO)
    }

    companion object {
        private const val MESSAGE_ENTER_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val MESSAGE_ENTER_PLAYER_YES_OR_NO = "%s은(는) 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"
    }
}
