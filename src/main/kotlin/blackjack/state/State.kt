package blackjack.state

sealed interface State {
    fun checkDrawState(): Boolean

    sealed interface Action : State {
        data object Hit : Action {
            override fun checkDrawState() = true
        }
    }

    sealed interface Finish : State {
        data object Bust : Finish {
            override fun checkDrawState() = false
        }

        data object Stay : Finish {
            override fun checkDrawState() = false
        }

        data object BlackJack : Finish {
            override fun checkDrawState() = false
        }
    }

    companion object
}
