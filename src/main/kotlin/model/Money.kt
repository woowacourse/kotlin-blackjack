package model

data class Money(var amount: Int) {
    init {
        require(amount >= 0) { ERROR_INVALID_AMOUNT }
    }

    fun applyProfitRate(
        other: Money,
        rate: Double,
    ) {
        this.amount -= (other.amount * rate).toInt()
        other.amount = (other.amount * rate).toInt()
    }

    fun changeAmount(newAmount: Int) {
        this.amount = newAmount
    }

    companion object {
        private const val ERROR_INVALID_AMOUNT = "1 이상의 값을 입력해주세요."
    }
}
