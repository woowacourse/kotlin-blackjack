package model

data class Point(val amount: Int) : Comparable<Point> {
    fun isLessOrEqualTo(number: Int) = amount <= number

    fun plus(number: Int): Point {
        return Point(amount + number)
    }

    override fun compareTo(other: Point): Int = this.amount.compareTo(other.amount)

    fun isLessThan(number: Int) = amount < number

    fun isEqualTo(number: Int): Boolean = amount == number
}
