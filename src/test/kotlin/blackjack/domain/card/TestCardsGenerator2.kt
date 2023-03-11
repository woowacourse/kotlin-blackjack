package blackjack.domain.card

class TestCardsGenerator2 : CardsGenerator {
    override fun generate(): Cards =
        Cards(
            listOf(
                // dealer
                Card(CardNumber.SIX, CardShape.HEART),
                Card(CardNumber.EIGHT, CardShape.HEART),

                // player1
                Card(CardNumber.SIX, CardShape.DIAMOND),
                Card(CardNumber.EIGHT, CardShape.DIAMOND),

                // player2
                Card(CardNumber.ACE, CardShape.CLOVER),
                Card(CardNumber.KING, CardShape.CLOVER),

                // 추가 발급
                Card(CardNumber.FOUR, CardShape.SPADE),
                Card(CardNumber.FIVE, CardShape.SPADE),
                Card(CardNumber.SIX, CardShape.SPADE)
            )
        )
}
