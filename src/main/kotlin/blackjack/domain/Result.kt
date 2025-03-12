package blackjack.domain

class Result {
    var win: Int = 0
        private set
    var lose: Int = 0
        private set
    var draw: Int = 0
        private set

    fun addWin() {
        win++
    }

    fun addLose() {
        lose++
    }

    fun addDraw() {
        draw++
    }
}
