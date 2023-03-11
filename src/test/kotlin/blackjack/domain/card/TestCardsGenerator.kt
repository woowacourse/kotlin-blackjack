package blackjack.domain.card

class TestCardsGenerator : CardsGenerator {
    override fun generate(): Cards =
        Cards(
            listOf(
                // dealer
                Card(CardNumber.FOUR, CardShape.HEART),
                Card(CardNumber.FIVE, CardShape.HEART),

                // player1
                Card(CardNumber.SIX, CardShape.DIAMOND),
                Card(CardNumber.EIGHT, CardShape.DIAMOND),

                // player2
                Card(CardNumber.THREE, CardShape.CLOVER),
                Card(CardNumber.FOUR, CardShape.CLOVER),

                // 추가 발급
                Card(CardNumber.FOUR, CardShape.SPADE),
                Card(CardNumber.FIVE, CardShape.SPADE),
                Card(CardNumber.SIX, CardShape.SPADE)
            )
        )
}
