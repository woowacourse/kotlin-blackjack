package blackjack.model

import blackjack.model.user.UserDecision

class Player(name: String) : Participant(name) {
    init {
        require(name.length <= MAX_NAME_LENGTH) {
            ERROR_NAME_LENGTH
        }
    }

    fun drawAdditionalCard(
        drawFunction: () -> Card,
        inputDecision: (String) -> UserDecision,
        outputAction: ((Player) -> Unit),
    ) {
        while (checkHitState() && inputDecision(getName()) == UserDecision.YES) {
            draw(drawFunction())
            outputAction(this)
        }
    }

    companion object {
        fun checkDuplication(playerNames: List<String>) {
            require(playerNames.size == playerNames.toSet().size) {
                ERROR_DUPLICATION_NAME
            }
        }

        private const val MAX_NAME_LENGTH = 8
        private const val ERROR_NAME_LENGTH = "사용자 이름은 최대 ${MAX_NAME_LENGTH}자 입니다."
        private const val ERROR_DUPLICATION_NAME = "사용자 이름은 중복이 불가능 합니다."
    }
}
