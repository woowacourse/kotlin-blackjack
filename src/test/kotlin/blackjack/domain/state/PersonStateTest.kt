package blackjack.domain.state

import blackjack.domain.calculateScore
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Deck
import blackjack.domain.generateCustomDeck
import blackjack.domain.person.Dealer
import blackjack.domain.person.Person
import blackjack.domain.person.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PersonStateTest {
    private lateinit var deck: Deck
    private lateinit var person: Person

    @BeforeEach
    fun setUp() {
        person = Player("player1")
    }

    @Test
    fun `보유한 카드의 점수가 21보다 크면 BUST를 반환한다`() {
        val customCards = listOf(CardNumber.JACK, CardNumber.JACK, CardNumber.TWO)
        deck = generateCustomDeck(customCards)

        repeat(customCards.size) { person.draw(deck) }

        assertAll(
            { (person.calculateScore() > 21) shouldBe true },
            { PersonState.from(person) shouldBe PersonState.BUST },
        )
    }

    @Test
    fun `플레이어가 보유한 카드의 점수가 21 이하면 HIT를 반환한다`() {
        val customCards = listOf(CardNumber.JACK, CardNumber.SIX)
        deck = generateCustomDeck(customCards)

        repeat(customCards.size) { person.draw(deck) }

        assertAll(
            { (person.calculateScore() <= 21) shouldBe true },
            { PersonState.from(person) shouldBe PersonState.HIT },
        )
    }

    @Test
    fun `딜러가 보유한 카드의 점수가 16을 초과한 경우 STAY를 반환한다`() {
        val customCards = listOf(CardNumber.JACK, CardNumber.SEVEN)
        deck = generateCustomDeck(customCards)
        person = Dealer()

        repeat(customCards.size) { person.draw(deck) }

        PersonState.from(person) shouldBe PersonState.STAY
    }

    @Test
    fun `딜러가 보유한 카드의 점수가 16이하인 경우 HIT을 반환한다`() {
        val customCards = listOf(CardNumber.JACK, CardNumber.SIX)
        deck = generateCustomDeck(customCards)
        person = Dealer()

        repeat(customCards.size) { person.draw(deck) }

        PersonState.from(person) shouldBe PersonState.HIT
    }
}
