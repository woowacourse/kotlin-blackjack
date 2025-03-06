package blackjack.view

class OutputView {
    fun requestPlayerNames() {
        println(MESSAGE_ENTER_PLAYER_NAMES)
    }

    companion object {
        private const val MESSAGE_ENTER_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    }
}
