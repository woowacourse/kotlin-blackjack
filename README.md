# kotlin-blackjack

## Class 다이어그램

![ClassDiagram.png](image%2FClassDiagram.png)

<br>

## 기능 구현 사항

- [x] 게임에 참여할 사람 이름 입력받기
- [ ] 딜러와 플레이어에게 카드 나눠주기
- [ ] 가진 카드 출력하기
- [x] 플레이어 Hit 여부 확인하기

- 결과출력
    - [ ] Hand 출력
    - [ ] score 합 계산
    - [ ] score 합 출력
    - [ ] 최종 승패 계산
        - 승
            - [ ] 딜러<플레이어
            - [ ] 딜러 Bust
        - 무
            - [ ] 딜러==플레이어
        - 패
            - [ ] 플레이어 Bust
            - [ ] 플레이어<딜러
    - [ ] 최종 승패 출력

# Model

## card

-------------------

### Denomination

- [x] (2~11) 숫자 객체 생성
    - A는 초기값 11

### Suit

- [x] 다이아, 클로버, 스페이드, 하트 문양 Enum Class

### Card

-[x] Denomination, Suit 조합으로 된 객체 생성

### Deck

- [x] Card의 종류 52가지 뭉치
- [x] 카드 한장 빼기

### Hand

- [x] 2장 이상 존재해야함.
- [x] List<Card> 형태 객체 생성.

----------------

<br>

## game

-----------

### Action

- [x] hit: Hand에 카드 1장 추가
- [x] hit: return true
- [x] stay: retrun false

### ScoreCalculation

- [x] 스코어 계산하기
- [x] 만약 버스트 and ACE 가지고 있으면 ACE -> 1

### Result

- [x] 승패 여부 가지고 있기
- [x] 스코어 비교해서 승 패 여부 확인

----------------

<br>

## player

-------------------

### Dealer

- [x] 딜러객체 생성
- [x] 딜러의 Hand의 합이 17 이하면 draw

### PlayerEntry

- [x] Player List 생성
- [x] Player수는 1개 이상

### Player

- [x] Player 객체 생성

-------------------