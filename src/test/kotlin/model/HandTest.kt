package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HandTest {
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
                ),
            )
        cardDistributor = CardDistributor(deck)
    }

    @Test
    fun `플레이어나 딜러가 카드를 뽑을 때 마다 추가할 수 있다`() {
        val initialCards: List<Card> =
            listOf(
                Card.of(CardRank.ACE, Shape.CLUB),
                Card.of(CardRank.TWO, Shape.DIAMOND),
            )
        val drawnCard = Card.of(CardRank.QUEEN, Shape.DIAMOND)

        val hand = Hand(initialCards)
        hand.addCard(drawnCard)

        assertThat(hand.handCards).containsExactly(
            Card.of(CardRank.ACE, Shape.CLUB),
            Card.of(CardRank.TWO, Shape.DIAMOND),
            Card.of(CardRank.QUEEN, Shape.DIAMOND),
        )
    }

    @Test
    fun `플레이어나 딜러가 가진 카드의 점수를 확인할 수 있다`() {
        val initialCards: List<Card> =
            listOf(
                Card.of(CardRank.THREE, Shape.CLUB),
                Card.of(CardRank.TWO, Shape.DIAMOND),
            )
        val updatedHandScore = Hand(initialCards).getScore()

        assertThat(updatedHandScore).isEqualTo(5)
    }

    @Test
    fun `플레이어나 딜러가 가진 카드의 개수를 확인할 수 있다`() {
        val initialCards: List<Card> =
            listOf(
                Card.of(CardRank.THREE, Shape.CLUB),
                Card.of(CardRank.TWO, Shape.DIAMOND),
            )
        val updatedHandCount = Hand(initialCards).getCardsCount()

        assertThat(updatedHandCount).isEqualTo(2)
    }
}
