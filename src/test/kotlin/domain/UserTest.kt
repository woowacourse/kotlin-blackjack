package domain

import domain.card.Cards
import domain.card.TrumpCard
import domain.card.TrumpCardNumber
import domain.card.TrumpCardPattern
import domain.status.Blackjack
import domain.user.Gamer
import domain.user.Gamers
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserTest {
    @DisplayName("게이머 생성")
    @Test
    fun nameSplitTest() {
        val inputNames = "test1,test2,test3"
        val gamers = Gamers(inputNames)
        listOf("test1", "test2", "test3")
        assertThat(gamers.names).isEqualTo(listOf(Gamer("test1"), Gamer("test2"), Gamer("test3")))
    }

    @DisplayName("카드 객체 생성")
    @Test
    fun carList() {
        assertThat(TrumpCard(TrumpCardNumber.FIVE, TrumpCardPattern.CLOVER).getScore()).isEqualTo(5)
    }

    @DisplayName("카드들의 점수 계산")
    @Test
    fun carsListScore() {
        //given
        val fiveCard = TrumpCard(TrumpCardNumber.FIVE, TrumpCardPattern.CLOVER)
        val cards = Cards(mutableListOf(fiveCard, fiveCard, fiveCard))
        //then
        assertThat(cards.getTotalScore()).isEqualTo(15)
    }

    @DisplayName("에이스 카드 점수 계산")
    @Test
    fun carsListAceScore() {
        //given
        val fiveCard = TrumpCard(TrumpCardNumber.FIVE, TrumpCardPattern.CLOVER)
        val aceCard = TrumpCard(TrumpCardNumber.ACE, TrumpCardPattern.CLOVER)
        val cards = Cards(mutableListOf(fiveCard, fiveCard, aceCard))
        //then
        assertThat(cards.getTotalScore()).isEqualTo(21)
    }

    @DisplayName("에이스 카드 점수 계산이 손해인 경우")
    @Test
    fun carsListAceScore2() {
        //given
        val fiveCard = TrumpCard(TrumpCardNumber.FIVE, TrumpCardPattern.CLOVER)
        val aceCard = TrumpCard(TrumpCardNumber.ACE, TrumpCardPattern.CLOVER)
        val cards = Cards(mutableListOf(fiveCard, fiveCard, fiveCard, fiveCard, aceCard))
        //then
        assertThat(cards.getTotalScore()).isEqualTo(21)
    }

    @DisplayName("덱에서 카드를 꺼내는 경우")
    @Test
    fun dealCard() {
        //given
        val fiveCard = TrumpCard(TrumpCardNumber.FIVE, TrumpCardPattern.CLOVER)
        val aceCard = TrumpCard(TrumpCardNumber.ACE, TrumpCardPattern.CLOVER)
        val cards = Cards(mutableListOf(fiveCard, aceCard))
        //then
        assertThat(cards.dealCard()).isEqualTo(
            TrumpCard(
                TrumpCardNumber.ACE,
                TrumpCardPattern.CLOVER
            )
        )
    }

    @DisplayName("덱에서 꺼낼 카드가 없는 경우")
    @Test
    fun createDeck() {
        //given
        val cards = Cards(mutableListOf())
        //then

        assertThrows<EmptyCardException> { cards.dealCard() }
    }

    @DisplayName("카드를 받지 않는 경우")
    @Test
    fun noDealCard() {
        //given
        val cards = Cards(mutableListOf())
        //then

        assertThrows<EmptyCardException> { cards.dealCard() }
    }

    @DisplayName("블랙잭 상태인 경우")
    @Test
    fun blackjack() {
        //given
        val fiveCard = TrumpCard(TrumpCardNumber.KING, TrumpCardPattern.CLOVER)
        val aceCard = TrumpCard(TrumpCardNumber.ACE, TrumpCardPattern.CLOVER)
        val deck = Cards(mutableListOf(fiveCard, aceCard))
        val gamer = Gamer("testUser")
        //when
        gamer.receiveCard(deck.dealCard())
        gamer.receiveCard(deck.dealCard())
        gamer.changeStatus()
        //then
        assertThat(gamer.status).isInstanceOf(Blackjack().javaClass)
    }
}
