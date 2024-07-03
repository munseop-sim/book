# chapter04. 설계품질과 트레이드오프

- **객체지향 설계**란 올바른 객체에게 올바른 책임을 할당하면서 낮은 결합도와 높은 응집도를 가진 구조를 창조하는 활동
    - 객체지향 설계의 핵심 : 책임
    - 책임을 할당하는 작업이 응집도,결합도와 같은 설계품질과 깊은 연관
- 객체의 상태가 아니라 객체의 행동에 초점을 맞추는 것은 결합도와 응집도를 합리적인 수준으로 유지할 수 있는 중요한 원칙
- 구현 : 변경될 가능성이 높은 부분??
- 인터페이스 : 상대적으로 안정적인 부분
- 응집도
    - 모듈내부에 포함된 내부요소들이 연관돼 있는 정도
    - 변경이 발생할 때 모듈 내부에서 발생하는 변경의 정도
    - 응집도가 높을수록 변경의 대상과 범위가 명확해지기 때문에 코드를 변경하기 쉽다.
- 결합도
    - 의존성의 정도를 나타내며 다른 모듈에 대해 얼마나 많은 지식을 갖고 있는지를 나타내는 척도 (어떤모듈이 다른 모듈의 대해 자세하게 알고 있다면 결합도가 높다고 말할 수 있음.)
    - 한 모듈이 변경되기 위해서 다른 모듈의 변경을 요구하는 정도 또는 변경의 원인을 이용해 결합도의 개념을 설명 할 수 있음.
    - 

## 객체지향 설계

1. 상태 또는 책임을 분할의 중심축으로 객체를 분할 → 객체의 책임은 무엇인가?
2. 캡슐화 → 객체지향의 가장 중요한 원칙
    1. 내부의 데이터를 반환하는 접근자(accessor)와 데이터를 변경하는 수정자(mutator)를 추가하는 것
    2. 외부에서 알 필요가 없는 부분을 감춤으로써 대상을 단순화하는 추상화의 한 종류
    3. 캡슐화 대상 : 변경 가능성이 높은 부분
    4. 캡슐화의 정도가 응집도/결합도에 영향을 미침
    5. 변경될 수 있는 어떤 것이라도 감추는 것을 의미
        1. 데이터 캡슐화 : 내부 속성을 외부로부터 감추는 것
    6. 내부의 구현 변경으로 인해 외부의 객체가 영향을 받는다면 캡슐화를 위반한 것

## 데이중심의 설계

1. 객체 내부에 저장되는 데이터를 기반으로 시스템을 분할 → 객체 내부에 저장해야 하는 데이터가 무엇인가?
    1. 변경에 취약
    2. 객체의 상태(데이터, 내부)에 초점
2. 문제점
    1. 캡슐화 위반
        1. 설계 시, 협력에 관한 고민없이 객체가 사용될 문맥을 추측할 수 밖에 없는 경우, 캡슐화를 위반하는 과도한 접근자와 수정자를 가지게 되는 경향이 있는데  이는 접근자와 수정자에 과도하게 의존하게 된다. 이를 “추측에 의한 설계전략 (desing-by-guessing strategy)”라고 부른다. → 대부분의 내부 구현이 퍼블릭하게 노출됨.(변경에 취약한 설계)
    2. 높은 결합도
        1. 여러 데이터 객체들을 사용하는 제어 로직이 특정 객체 안에 집중되기 때문에 하나의 제어 객체가 다수의 데이터 객체에 강하게 결합하게 됨.
        2. 전체 시스템을 하나의 거대한 의존성 덩어리로 만들어 버리기 때문에 어떤 변경이라도 일단 발생하고 나면 시스템 전체가 요동칠 수밖에 없음.
    3. 낮은 응집도
        1. 변경의 이유가 서로 다른 코드들을 하나의 모듈 안에 뭉쳐놓았기 때문에 변경과 아무런 상관이 없는 코드들이 영향을 받게됨.
        2. 하나의 요구사항 변경을 반영하기 위해 동시에 여러 모듈을 수정해야 한다.
    4. 너무 이른 시기에  데이터에 관해 결정하도록 강요
    5. 협력이라는 문맥을 고려하지 않고 객체를 고립시킨 채 오퍼레이션을 결정한다.
    6. 객체를 고립시킨 채 오퍼레이션을 정의하도록 만든다.