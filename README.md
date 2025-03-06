# kotlin-blackjack

## 소개

블랙잭 게임을 변형한 프로그램  
블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

# 기능 명세서

- [x] 플레이어는 이름으로 구분된다.
- [x] 딜러는 플레이어에게 카드를 나눠준다.
- [ ] 게임을 완료한 후 각 플레이어별로 승패를 알 수 있다.
- [x] 딜러 또는 플레이어가 갖고 있는 카드를 확인할 수 있다.
- [x] 딜러 또는 플레이어의 카드 총합을 알 수 있다.
- [x] 딜러의 최종 승패 결과를 알 수 있다.
- [x] 플레이어의 최종 결과를 알 수 있다.

## 카드

- [x] 카드는 랭크와 수트로 구성된다.
    - [x] 랭크의 종류는 Ace, 숫자, 캐릭터이다.
        - [x] Ace는 1 또는 11로 계산된다.
        - [x] 숫자는 2부터 10까지 존재하며, 그 숫자로 계산된다.
        - [x] 캐릭터 카드 `Jack`, `Queen`, `King`은 10으로 계산된다.
    - [x] 수트의 종류는 `스페이드(♤, Spade)`, `하트(♡, Heart)`, `다이아몬드(◇, Diamond)`, `클로버(♧, Clover)`가 있다.

## 승패 조건

- [x] 게임을 시작하면 딜러는 한 장의 카드를, 플레이어는 두 장의 카드를 지급받는다.
- [x] 플레이어는 모든 카드의 합이 21 미만이 될 수 있을 경우 계속해서 카드를 뽑을 수 있다.
    - [x] 이 때, 플레이어 카드의 합이 21 이하가 될 수 없는 플레이어는 반드시 패배한다.
- [x] 모든 플레이어들의 결정이 끝나면 딜러는 숫자 합이 17 이상이 될 수 있을 때까지 카드를 받는다.
- [ ] 딜러의 카드 숫자 합이 21을 초과할 수 밖에 없을 경우 남은 플레이어는 전부 승리한다.
- [ ] 아직 승패가 결정되지 않았다면, 딜러와 플레이어 중 카드의 합이 21에 가까운 사람이 이긴다.
    - [ ] 딜러와 플레이어의 숫자가 같다면, 무승부로 처리한다.
