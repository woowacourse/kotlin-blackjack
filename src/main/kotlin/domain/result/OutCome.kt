package domain.result

enum class OutCome {
    BLACKJACK {
        override fun convertOutCome() = LOSE
    },
    WIN {
        override fun convertOutCome() = LOSE
    },
    DRAW {
        override fun convertOutCome() = DRAW
    },
    LOSE {
        override fun convertOutCome() = WIN
    }, ;

    abstract fun convertOutCome(): OutCome
}
