package blackjack.model

data class UserDecision(val decision: String) {
    init {
        require(decision == PLAYER_HIT_MESSAGE || decision == PLAYER_STAY_MESSAGE) {
            ERROR_DECISION
        }
    }

    companion object {
        const val ERROR_DECISION = "잘못 된 결정입니다."
        const val PLAYER_HIT_MESSAGE = "y"
        const val PLAYER_STAY_MESSAGE = "n"
    }
}
