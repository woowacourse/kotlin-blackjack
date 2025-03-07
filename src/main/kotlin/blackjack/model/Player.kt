package blackjack.model

open class Player(
    open val name: String,
    open val cards: Cards = Cards(emptyList()),
) {
    lateinit var result: GameResult

    open fun appendCard(card: Card) {
        cards.add(card)
    }

    fun isBlackjack(firstTurn: Boolean): Boolean = cards.isBlackjack(firstTurn)

    fun isBust(): Boolean = cards.isBust()

    fun updateResult(dealerGameResult: GameResult) {
        result = GameResult.reversed(dealerGameResult)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        return name == other.name
    }

    override fun hashCode(): Int = name.hashCode()

    companion object {
        fun from(name: String): Player {
            require(name != "딜러") { "플레이어는 딜러라는 이름을 가질 수 없습니다." }
            require(name.length in 1..5) { "플레이어는 1에서 5사이 길이의 이름만 가질 수 있습니다." }
            return Player(name)
        }
    }
}
