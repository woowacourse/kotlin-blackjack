package model

data class Money(var amount: Int) {
    init {
        require(amount >= 0) { "1 이상의 값을 입력해주세요." }
    }

    fun exchange(
        other: Money,
        rate: Double,
    ) {
        this.amount -= (other.amount * rate).toInt()
        other.amount = (other.amount * rate).toInt()
    }

    fun changeAmount(newAmount: Int) {
        this.amount = newAmount
    }
}
