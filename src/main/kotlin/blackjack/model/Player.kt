package blackjack.model

import blackjack.state.State
import blackjack.view.user.PLAYER_HIT_MESSAGE

class Player(name: String) : Participant(name) {
    private var balance: BettingMoney = BettingMoney(DEFAULT_BETTING_MONEY)

    init {
        require(name.length <= MAX_NAME_LENGTH) {
            ERROR_NAME_LENGTH
        }
    }

    fun setMoney(money: BettingMoney) {
        balance = money
    }

    fun getMoney(): BettingMoney {
        return balance
    }

    fun drawAdditionalCard(
        drawFunction: () -> Card,
        inputDecision: (String) -> String,
        outputAction: ((Player) -> Unit),
    ) {
        while (checkHitState() && inputDecision(getName()) == PLAYER_HIT_MESSAGE) {
            draw(drawFunction())
            outputAction(this)
        }
    }

    fun calculateProfit(
        result: Result,
        state: State,
    ): Double {
        return when (result) {
            Result.WIN -> {
                if (state == State.Finish.BlackJack) {
                    balance * BLACK_JACK_EARNING_RATE
                } else {
                    balance * WIN_EARNING_RATE
                }
            }

            Result.DRAW -> DRAW_EARNING_RATE
            Result.LOSE -> -balance
        }
    }

    companion object {
        fun checkDuplication(playerNames: List<String>) {
            require(playerNames.size == playerNames.toSet().size) {
                ERROR_DUPLICATION_NAME
            }
        }

        private const val DEFAULT_BETTING_MONEY: Double = 0.0
        const val BLACK_JACK_EARNING_RATE: Double = 1.5
        const val WIN_EARNING_RATE: Double = 1.0
        const val DRAW_EARNING_RATE: Double = 0.0
        private const val MAX_NAME_LENGTH = 8
        private const val ERROR_NAME_LENGTH = "사용자 이름은 최대 ${MAX_NAME_LENGTH}자 입니다."
        private const val ERROR_DUPLICATION_NAME = "사용자 이름은 중복이 불가능 합니다."
    }
}
