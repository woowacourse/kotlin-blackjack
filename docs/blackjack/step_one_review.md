# 수정 사항

## Project 구조

- [x] : ktlint hook 설정
- [x] : package 분리

## Domain

- [x] : 딜러와 플레이어에서 발생하는 중복 코드를 제거(Participant super class)
- [x] : Deck 내부 로직 뽑아서 쓰는걸로 수정
- [x] : Cards 의 합은 Participant 객체가 하도록 수정
- [x] : isBust, isBlackJack 은 Participant 가 가지는게 맞음(상태를 가지도록 해보면 어떨까..?)
- [x] : ParticipantHands 대신 Participant 가 자율성을 가지고 행동하도록 하자
- [x] : 승부를 판단하는 로직은 Participant 객체가 하자
- [x] : GameResult 에 "승", "패", "무" UI로 빼자

# 질문

https://github.com/woowacourse/kotlin-blackjack/pull/77#discussion_r1518568849

참여자(Participant)가 Hand 의 상태를 가져와 판단하기 보다는
Hand 가 자신의 상태(cards)를 기반으로 스스로 점수가 몇 점인지, 버스트인지, 블랙잭인지 판단하는 것이 좋다고 생각했습니다.

## Dealer

```kotlin
private fun hitPlayer(
    player: Player,
    deck: Deck,
) {
    while (inputView.inputWhetherHit(player)) {
        player.hit(deck.pull())
        outputView.showPlayerHandCards(player)
    }
}

private fun hitDealer(
    dealer: Dealer,
    deck: Deck,
) {
    while (dealer.canHit()) {
        outputView.showDealerHitCard() // 이 부분을 어떻게 처리할지 
        dealer.hit(deck.pull())
    }
}
```

Controller 의 로직 중에 참여자가 hit 할지 말지를 판단하는 로직이 있습니다.
이 로직을 개인적으로 비지니스 로직으로 느꼈기 때문에, Dealer, Player 가 자신의 상태를 가지고 판단하도록 하고 싶었습니다.  
그러나, 중간에 View 를 호출하는 부분이 있어서 어떻게 처리해야할지 고민이 되었습니다.

1) 고차함수 혹은 전략 패턴을 활용해서 View 의 로직을 담아서 넘기는 방법
   현재 console 을 활용한 블랙잭 게임에서는 그럴일 없겠지만, View 객체가 죽을 경우
   죽은 View를 참조하기 때문에 논리 예외가 발생할 것입니다.. 따라서, 다음 방법을 채택했습니다.

2) 또 다른 방법은 직접 Observer 패턴을 구현하여 사용하는 것인데 오버 엔지니어링이 우려되어 채택하지 않았습니다😱
