package blackjack.state

sealed interface State {
    fun isDrawState(): Boolean

    sealed interface Action : State {
        data object Hit : Action {
            override fun isDrawState() = true
        }
    }

    sealed interface Finish : State {
        data object Bust : Finish {
            override fun isDrawState() = false
        }

        data object Stay : Finish {
            override fun isDrawState() = false
        }

        data object BlackJack : Finish {
            override fun isDrawState() = false
        }
    }
}
