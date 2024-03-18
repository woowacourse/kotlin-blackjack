package blackjack.model

@JvmInline
value class Amount(val amount: Int) {
    operator fun plus(other: Amount): Amount {
        return Amount(this.amount + other.amount)
    }

    operator fun minus(other: Amount): Amount {
        return Amount(this.amount - other.amount)
    }

    operator fun times(num: Int): Amount {
        return Amount(this.amount * num)
    }
}
