package domain.result

enum class OutCome {
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
