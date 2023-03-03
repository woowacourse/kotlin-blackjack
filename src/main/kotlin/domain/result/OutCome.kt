package domain.result

enum class OutCome(val text: String) {
    WIN("승") {
        override fun convertOutCome() = LOSE
    },
    DRAW("무") {
        override fun convertOutCome() = DRAW
    },
    LOSE("패") {
        override fun convertOutCome() = WIN
    }, ;

    abstract fun convertOutCome(): OutCome
}
