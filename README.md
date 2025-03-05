# 🎰 kotlin-lotto

## 📚️ 개요

구매한 로또의 당첨 결과와 수익률을 계산한다.

## 🛠️ 구현할 기능

- [x] 게임에 참여할 플레이어의 이름을 입력한다.
    - [x] 플레이어의 이름은 중복될 수 없다.
    - [x] `,`를 기준으로 분리한다.
    - [x] 플레이어는 1명 이상이어야 한다
- [x] 게임에 사용되는 카드를 생성할 수 있다.
    - [x] 게임에 사용되는 52장의 카드를 생성한다
    - [x] 원하는 개수만큼의 카드를 뽑을 수 있다
    - [x] 카드는 중복될 수 없다
    - [x] 카드는 0장일 수 없다
- [x] 딜러와 플레이어는 기본적으로 2장의 카드를 갖는다.
- [x] 딜러는 1장, 플레이어는 2장의 카드를 공개한다.
- [x] 카드에 따라 숫자를 계산할 수 있다
    - [x] King, Queen, Jack은 각각 10
    - [x] Ace는 1 또는 11
- [ ] 플레이어는 카드의 총 합이 21이하이면 카드를 더 받을지 말지 선택할 수 있다.
- [ ] 딜러는 카드의 총 합이 16이하이면 반드시 1장의 카드를 받아야 한다.
- [ ] 딜러는 카드의 총 합이 17 이상이면 카드를 받을 수 없다.
- [ ] 플레이어와 딜러는 카드 숫자의 총 합을 통해 점수를 계산할 수 있다.
- [ ] 게임에 따른 최종 승패를 계산할 수 있다

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