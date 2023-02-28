package domain.card

class Card(val shape: Shape, val value: String) {
    fun valueOf(): Int {
        return when (value) {
            "A" -> 11
            "J", "Q", "K" -> 10
            else -> value.toInt()
        }
    }
}
