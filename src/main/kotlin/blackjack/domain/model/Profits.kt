package blackjack.domain.model

class Profits(val value: List<Profit>) {
    fun calculateTotalLosses(name: String): Profit {
        return Profit(name, value.sumOf { it.value } * -1)
    }
}
