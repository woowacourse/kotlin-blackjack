package blackjack.domain.participants

import blackjack.domain.betting.BettingAmount
import blackjack.domain.betting.BettingInfo
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Deck
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `덱에서 카드를 한 장 나눠준다`() {
        // Given
        val card = Card(CardNumber.ACE, CardPattern.CLOVER)
        val dealer = Dealer(Deck.createCustomDeck(listOf(card)))

        // When
        dealer.handOut(dealer)

        // Then
        dealer.hand.cards shouldContain card
    }

    @Test
    fun `초기 상태일 경우 카드를 2장 받는다`() {
        val dealer = Dealer(Deck.createDefaultDeck())
        dealer.getDrawAmount() shouldBe 2
    }

    @Test
    fun `패의 총 합이 16 이하인 경우 카드를 1장 받는다`() {
        // Given
        val cards =
            listOf(
                Card(CardNumber.KING, CardPattern.HEART),
                Card(CardNumber.SIX, CardPattern.CLOVER),
            )
        val dealer = Dealer(Deck.createDefaultDeck(), cards)

        // Then
        assertSoftly(dealer) {
            score shouldBeLessThanOrEqual 16
            canHit() shouldBe true
            getDrawAmount() shouldBe 1
        }
    }

    @Test
    fun `버스트가 된 경우 카드를 받을 수 없다`() {
        // Given
        val cards = List(3) { Card(CardNumber.KING, CardPattern.HEART) }
        val dealer = Dealer(Deck.createDefaultDeck(), cards)

        // Then
        assertSoftly(dealer) {
            isBust() shouldBe true
            canHit() shouldBe false
        }
    }

    @Test
    fun `플레이어의 점수가 더 큰 경우 플레이어는 베팅 금액만큼 받는다`() {
        // Given
        val player = Player("test", listOf(Card(CardNumber.ACE, CardPattern.CLOVER)))
        val bettingInfo = BettingInfo(player, BettingAmount(10000))
        val dealer = Dealer(initialHand = listOf(Card(CardNumber.KING, CardPattern.CLOVER)))

        // When
        val result = dealer.calculatePlayersProfit(bettingInfo)

        // Then
        result.value shouldBe 10000.0
    }

    @Test
    fun `플레이어의 점수가 더 작은 경우 플레이어는 베팅 금액만큼 잃는다`() {
        // Given
        val player = Player("test", listOf(Card(CardNumber.KING, CardPattern.CLOVER)))
        val bettingInfo = BettingInfo(player, BettingAmount(10000))
        val dealer = Dealer(initialHand = listOf(Card(CardNumber.ACE, CardPattern.CLOVER)))

        // When
        val result = dealer.calculatePlayersProfit(bettingInfo)

        // Then
        result.value shouldBe -10000.0
    }

    @Test
    fun `점수가 같은 경우 플레이어는 0원을 받는다`() {
        // Given
        val player = Player("test", listOf(Card(CardNumber.ACE, CardPattern.CLOVER)))
        val bettingInfo = BettingInfo(player, BettingAmount(10000))
        val dealer = Dealer(initialHand = listOf(Card(CardNumber.ACE, CardPattern.SPADE)))

        // When
        val result = dealer.calculatePlayersProfit(bettingInfo)

        // Then
        result.value shouldBe 0.0
    }

    @Test
    fun `플레이어가 블랙잭인 경우 플레이어는 베팅 금액의 1․5배만큼 받는다`() {
        // Given
        val player = Player("test", listOf(Card(CardNumber.ACE, CardPattern.CLOVER), Card(CardNumber.KING, CardPattern.CLOVER)))
        val bettingInfo = BettingInfo(player, BettingAmount(10000))
        val dealer = Dealer(initialHand = listOf(Card(CardNumber.KING, CardPattern.SPADE)))

        // When
        val result = dealer.calculatePlayersProfit(bettingInfo)

        // Then
        result.value shouldBe 15000.0
    }
}
