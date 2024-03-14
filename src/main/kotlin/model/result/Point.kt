package model.result

@JvmInline
value class Point(val amount: Int) {
    operator fun plus(other: Int): Point {
        return Point(amount + other)
    }

    companion object {
        operator fun Point.compareTo(other: Point): Int {
            return amount.compareTo(other.amount)
        }

        operator fun Point.compareTo(other: Int): Int {
            return amount.compareTo(other)
        }
    }
}
