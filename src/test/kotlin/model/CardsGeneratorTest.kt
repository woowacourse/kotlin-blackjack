package model

import model.card.CardName
import model.card.CardRank
import model.card.CardsGenerator
import model.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class CardsGeneratorTest {
    private val cards = CardsGenerator().generateCards()

    @Test
    fun `게임에 사용될 52장의 카드를 생성한다`() {
        val cardsLength = cards.totalCount
        assertThat(cardsLength).isEqualTo(52)
    }

    @ParameterizedTest
    @MethodSource("testDeck")
    fun `카드 목록에 생성된 카드 이름이 있는지 검증한다`(cardName: CardName) {
        val result = cards.names.contains(cardName)
        Assertions.assertTrue(result)
    }

    companion object {
        @JvmStatic
        private fun testDeck(): List<CardName> =
            listOf(
                CardName(CardRank.ACE.name, Shape.HEART.name),
                CardName(CardRank.ACE.name, Shape.SPADE.name),
                CardName(CardRank.ACE.name, Shape.CLUB.name),
                CardName(CardRank.ACE.name, Shape.DIAMOND.name),
                CardName(CardRank.KING.name, Shape.HEART.name),
                CardName(CardRank.KING.name, Shape.SPADE.name),
                CardName(CardRank.KING.name, Shape.CLUB.name),
                CardName(CardRank.KING.name, Shape.DIAMOND.name),
                CardName(CardRank.QUEEN.name, Shape.HEART.name),
                CardName(CardRank.QUEEN.name, Shape.SPADE.name),
                CardName(CardRank.QUEEN.name, Shape.CLUB.name),
                CardName(CardRank.QUEEN.name, Shape.DIAMOND.name),
                CardName(CardRank.JACK.name, Shape.HEART.name),
                CardName(CardRank.JACK.name, Shape.SPADE.name),
                CardName(CardRank.JACK.name, Shape.CLUB.name),
                CardName(CardRank.JACK.name, Shape.DIAMOND.name),
                CardName(CardRank.TEN.name, Shape.HEART.name),
                CardName(CardRank.TEN.name, Shape.SPADE.name),
                CardName(CardRank.TEN.name, Shape.CLUB.name),
                CardName(CardRank.TEN.name, Shape.DIAMOND.name),
                CardName(CardRank.NINE.name, Shape.HEART.name),
                CardName(CardRank.NINE.name, Shape.SPADE.name),
                CardName(CardRank.NINE.name, Shape.CLUB.name),
                CardName(CardRank.NINE.name, Shape.DIAMOND.name),
                CardName(CardRank.EIGHT.name, Shape.HEART.name),
                CardName(CardRank.EIGHT.name, Shape.SPADE.name),
                CardName(CardRank.EIGHT.name, Shape.CLUB.name),
                CardName(CardRank.EIGHT.name, Shape.DIAMOND.name),
                CardName(CardRank.SEVEN.name, Shape.HEART.name),
                CardName(CardRank.SEVEN.name, Shape.SPADE.name),
                CardName(CardRank.SEVEN.name, Shape.CLUB.name),
                CardName(CardRank.SEVEN.name, Shape.DIAMOND.name),
                CardName(CardRank.SIX.name, Shape.HEART.name),
                CardName(CardRank.SIX.name, Shape.SPADE.name),
                CardName(CardRank.SIX.name, Shape.CLUB.name),
                CardName(CardRank.SIX.name, Shape.DIAMOND.name),
                CardName(CardRank.FIVE.name, Shape.HEART.name),
                CardName(CardRank.FIVE.name, Shape.SPADE.name),
                CardName(CardRank.FIVE.name, Shape.CLUB.name),
                CardName(CardRank.FIVE.name, Shape.DIAMOND.name),
                CardName(CardRank.FOUR.name, Shape.HEART.name),
                CardName(CardRank.FOUR.name, Shape.SPADE.name),
                CardName(CardRank.FOUR.name, Shape.CLUB.name),
                CardName(CardRank.FOUR.name, Shape.DIAMOND.name),
                CardName(CardRank.THREE.name, Shape.HEART.name),
                CardName(CardRank.THREE.name, Shape.SPADE.name),
                CardName(CardRank.THREE.name, Shape.CLUB.name),
                CardName(CardRank.THREE.name, Shape.DIAMOND.name),
                CardName(CardRank.TWO.name, Shape.HEART.name),
                CardName(CardRank.TWO.name, Shape.SPADE.name),
                CardName(CardRank.TWO.name, Shape.CLUB.name),
                CardName(CardRank.TWO.name, Shape.DIAMOND.name)
            )
    }
}
