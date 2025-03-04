# 🃏 kotlin-blackjack

## 📚️ 개요

블랙잭 게임을 구현한다.

## 🛠️ 구현할 기능

- [ ] 카드는 각각의 문양과 값을 가지고 있다.
- [ ] 카드의 값은 2부터 10까지의 숫자와 A, Q, K, J의 문자가 있다.
- [ ] 카드의 문양은 클로버, 하트, 스페이드, 다이아몬드가 있다.
- [ ] ACE는 1과 11 중 최적의 값을 선택한다.
- [ ] 카드를 가진 손에서 점수를 계산할 수 있다.
- [ ] 카드 덱은 모든 종류의 카드를 가지고 있다.
- [ ] 한 번 뽑은 카드는 다시 뽑을 수 없다.
- [ ] 21을 넘어가면 버스트된다.
- [ ] 21을 넘지 않는다면 플레이어는 계속 패를 뽑을 수 있다.
- [ ] 딜러는 점수가 16 이하일 경우, 패를 뽑아야 한다.
- [ ] 딜러는 점수가 17 이상일 경우, 패를 뽑을 수 없다.
- [ ] 딜러보다 21에 가까운 점수를 가진 플레이어는 승리한다.
- [ ] 딜러가 21 이상의 점수를 가진 경우 버스트가 되지 않은 플레이어는 승리한다.
- [ ] 게임이 종료된 경우 딜러와 각 플레이어별 결과를 출력한다.
- [ ] 플레이어 목록을 `,` 기준으로 입력받는다.
- [ ] 플레이어 목록은 최소 1명, 최대 7명이다.

## 🖥️ 출력 예시

```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

딜러와 pobi, jason에게 2장의 나누었습니다.
딜러: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 승패
딜러: 1승 1패
pobi: 승 
jason: 패
```