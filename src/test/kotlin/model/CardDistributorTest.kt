package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardDistributorTest {
    lateinit var cardDistributor: CardDistributor

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
                ),
            )
        cardDistributor = CardDistributor(deck)
    }

    @Test
    fun `게임 시작 시에 처음 두 장의 카드를 분배한다`() {
        val hand = cardDistributor.distributeInitialCards()

        assertThat(hand.handCards).hasSize(2)
        assertThat(hand.handCards).containsExactly(
            Card.of(CardRank.ACE, Shape.CLUB),
            Card.of(CardRank.TWO, Shape.DIAMOND),
        )
    }

    @Test
    fun `카드를 한 장 뽑는다`() {
        cardDistributor.distributeInitialCards()
        val drawnCard = cardDistributor.drawCard()

        assertThat(drawnCard).isEqualTo(Card.of(CardRank.THREE, Shape.HEART))
    }

    @Test
    fun `카드를 여러 장 뽑을 수 있다`() {
        cardDistributor.distributeInitialCards()
        val firstDraw = cardDistributor.drawCard()
        val secondDraw = cardDistributor.drawCard()

        assertThat(firstDraw).isEqualTo(Card.of(CardRank.THREE, Shape.HEART))
        assertThat(secondDraw).isEqualTo(Card.of(CardRank.FOUR, Shape.SPADE))
    }
}
