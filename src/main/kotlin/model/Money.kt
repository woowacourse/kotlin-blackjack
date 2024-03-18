package model

data class Money(var amount: Int) {
    init {
        require(amount >= 0) { ERROR_INVALID_AMOUNT }
    }

    fun changeAmount(newAmount: Int) {
        this.amount = newAmount
    }

    fun add(other: Money) {
        amount += other.amount
    }

    fun add(other: Int) {
        amount += other
    }

    companion object {
        private const val ERROR_INVALID_AMOUNT = "1 이상의 값을 입력해주세요."
    }
}
