package blackjack.model

object CardBundle {
    private val denominations = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "K", "Q", "J")
    private val suites = listOf("하트", "다이아몬드", "스페이드", "클로버")
    private val bundle =
        List(denominations.size * suites.size) {
            Card(denominations[it / suites.size], suites[it % suites.size])
        }

    fun provide(): Card {
        return bundle.shuffled().first()
    }
}
