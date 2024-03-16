package model.participants

@JvmInline
value class Profit(val amount: Double = 0.0) {
    operator fun plus(other: Profit): Profit {
        return Profit(this.amount + other.amount)
    }

    operator fun unaryMinus(): Profit {
        return Profit(-amount)
    }
}
