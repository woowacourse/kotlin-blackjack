package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var cardDistributor: CardDistributor

    @BeforeEach
    fun setUp() {
        val deck =
            Cards(
                listOf(
                    Card.of(CardRank.ACE, Shape.CLUB),
                    Card.of(CardRank.TWO, Shape.DIAMOND),
                    Card.of(CardRank.THREE, Shape.HEART),
                    Card.of(CardRank.FOUR, Shape.SPADE),
                    Card.of(CardRank.FIVE, Shape.CLUB),
                    Card.of(CardRank.SIX, Shape.DIAMOND),
                ),
            )
        cardDistributor = CardDistributor(deck)
    }

    @Test
    fun `딜러는 게임을 시작하면 2장의 카드를 갖는다`() {
        val cards =
            listOf(
                Card.of(CardRank.ACE, Shape.CLUB),
                Card.of(CardRank.SIX, Shape.CLUB),
            )

        assertThat(Hand(cards).getCardsCount()).isEqualTo(2)
    }

    @Test
    fun `딜러는 보유한 카드가 16이하일 경우 게임을 진행할 수 있다`() {
        val cards =
            listOf(
                Card.of(CardRank.FIVE, Shape.CLUB),
                Card.of(CardRank.SIX, Shape.SPADE),
            )

        val dealer = Dealer(Hand(cards))
        Assertions.assertTrue(dealer.decideToHit())
    }

    @Test
    fun `딜러는 보유한 카드가 16이하인 경우 카드를 뽑아야한다`() {
        val cards =
            listOf(
                Card.of(CardRank.FIVE, Shape.CLUB),
                Card.of(CardRank.SIX, Shape.SPADE),
            )

        val dealer = Dealer(Hand(cards))
        Assertions.assertTrue(dealer.performTurn(cardDistributor))
    }
}
