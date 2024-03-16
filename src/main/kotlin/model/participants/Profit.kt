package model.participants

@JvmInline
value class Profit(val amount: Double = ZERO) {
    operator fun plus(other: Profit): Profit {
        return Profit(this.amount + other.amount)
    }

    operator fun unaryMinus(): Profit {
        return Profit(-amount)
    }
    companion object {
        const val ZERO = 0.0
        const val ONE_TIMES = 1.0
        const val MINUS_ONE_TIMES = -1.0
        const val ONE_AND_HALF_TIMES = 1.5
    }
}
