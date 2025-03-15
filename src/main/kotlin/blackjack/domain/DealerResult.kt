package blackjack.domain

class DealerResult {
    var revenue: Int = 0

    var win: Int = 0
        private set
    var lose: Int = 0
        private set
    var draw: Int = 0
        private set

    fun updateRevenueWhenPlayerLose(amount: Int) {
        revenue += amount
    }

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
