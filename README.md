## 협력하는 객체들의 공동체

> 시너지를 생각하라. 전체는 부분의 합보다 크다

객체지향 소프트웨어란 '실세계의 투영'이며, 객체란 '현실세계에 존재하는 사물에 대한 추상화이다.'   
실세계를 모방하는 것이 아닌, 새로운 세계를 창조하는 것이다.

### from my POV
##### 객체지향 소프트웨어란?
내가 창조하는 프로그램 세계관이다.
##### 객체란?
내가 창조한 세계에서 살아가는 각각의 개체이다.
***
## 협력하는 사람들
각각의 객체는 '요청-응답'의 관계로 협력한다.  
이러한 협력은 연쇄적으로 발생한다.

***
## 역할, 책임, 협력
##### 역할이란?
관련성 높은 책임의 집합으로, 어떤 협력에 참여하는 특정한 사람이 협력 안에서 차지하는 책무를 의미한다.  
역할은 협력에 참여하는 객체에 대한 일종의 페르소나이다.

- 여러 객체가 동일한 역할을 수행할 수 있다.
- 역할은 대체 가능성을 의미한다.
- 각 객체는 책임을 수행하는 방법을 자율적으로 선택할 수 있다.
- 하나의 객체가 동시에 여러 역할을 수행할 수 있다.


##### 책임이란?
메서드의 집합으로, 해야할 일의 집합이자, 역할에 적합한 책임을 수행해야 한다.
책임이 불분명한 객체는 애플리케이션의 미래 역시 불분명하게 만든다.

##### 협력이란?
특정한 책임을 수행하는 역할들 간의 연쇄적인 요청-응답을 통해 목표를 달성해낸다.

##### 객체지향 설계?
적절한 객체에게 적절한 책임을 할당하는 것

##### 객체란?
협력에 참여하는 주체이자, 객체지향 패러다임의 중심이다.
상태와 행동을 함께 지닌 실체라고 정의하기도 한다.

- 객체는 충분히 협력적이어야 한다.
    - 전지전능한 객체는 자멸하고 만다.
    - 명령에 수동적이지 않은, 요청에 응답한다.
- 객체는 충분히 자율적이어야 한다.
    - 내부와 외부를 명확히 구분할 것
    - What은 알 수 있지만, How는 알 수 없다.
- 자신의 상태를 직접 관리하고, 상태를 기반으로 스스로 판단하고 행동할 수 있음을 의미한다.

###### -> 객체는 상태와 행위를 하나의 단위로 묶는 자율적인 존재이다.

##### 협력과 메시지
객체지향의 세계에서 존재하는 오직 한가지 의사소통 수단은 '메시지'이다.  
송신자(sender) - 수신자(receiver)간의 협력은 메시지로 이루어진다.

##### 메서드와 자율성
객체가 수신된 메시지를 처리하는 방법을 메서드(method)라고 부른다.
***

## 객체지향의 본질
- 객체지향이란 시스템을 상호작용하는 '자율적인 객체들의 공동체'로 바라보고 객체를 이용해 시스템을 분할하는 방법이다.
- 자율적인 객체란 '상태와 행위'를 함께 지니며, 스스로 자기 자신을 책임진다.
- 객체는 다른 객체와 '협력'한다.
    - 각 객체는 협력 내에서 정해진 '역할'을 수행하며, 역할은 관련된 '책임'의 집합이다.
- 객체는 다른 객체와 협력하기 위해 메시지를 전송하고, '메시지'를 수신한 객체는 메시지를 처리하는 데 적합한 '메서드'를 자율적으로 선택한다.

##### 객체를 지향하라
객체지향 프로그래밍을 클래스 중심이 아닌,객체의 관점으로 사고의 중심을 전환하라.

### from my POV
클래스는 단지, 객체를 만들어주는 '도구'에 불과하다.   
흔히 설명들은 '붕어빵틀'도 오답이다. 이는 제 3의 존재가 개입하기 때문이다.  
클래스는 객체 스스로가 원하는 시기에 스스로를 만들기위해 선택한 도구에 불과하다.

객체의 역할, 책임, 협력, 메시지를 주고받는 객체들의 동적인 관계에 집중하자. 