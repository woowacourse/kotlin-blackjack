package domain

class Money(val value: Int = 0) {

    operator fun plus(value: Int): Money {
        return Money(this.value + value)
    }

    operator fun times(value: Int): Money {
        return Money(this.value * value)
    }

}
