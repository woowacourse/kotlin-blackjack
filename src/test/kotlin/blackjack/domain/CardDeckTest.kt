package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardDeck
import blackjack.model.CardRank
import blackjack.model.ShuffleStrategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.LinkedList
import kotlin.collections.sorted

class TestShuffle: ShuffleStrategy{
    override fun shuffle(cardList : List<Card>) = LinkedList(cardList.sortedBy{it.rank})
}

class CardDeckTest {
    @Test
    fun `카드는 6덱 이상 반환될 수 없다`() {
        // given
        val cardDeck = CardDeck()
        val exceedCount = 52*6+1

        // when & then
        assertThrows<IllegalArgumentException> {
            repeat(exceedCount){cardDeck.draw()}
        }
    }

    @Test
    fun `카드는 총 6덱 까지 뽑을 수 있다`() {
        // given
        val cardDeck = CardDeck()
        val cardCount = 52*6

        // when
        val cards: List<Card> = List(cardCount){cardDeck.draw()}

        // then
        assertThat(cards.size).isEqualTo(cardCount)
    }

    @Test
    fun `카드는 랜덤으로 섞여서 나온다`() {
        // given
        val cardDeck = CardDeck(TestShuffle())
        // when
        val card: Card = cardDeck.draw()

        // then
        assertThat(card.rank).isEqualTo(CardRank.ACE)
    }
}
