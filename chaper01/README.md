# chapter01. 객체, 설계

1. 소프트웨어의 3가지 목적 (마)
    1. 실행중에 제대로 작동 → 당연
    2. 변경을 위한 존재 → 일정에 맞추다보면 변경하기 불가능한 코드를 작성하곤 한다. 😭
        1. 객체 사이의 의존성(결합도)가 높으면 자연스레 변경하기 어려운 코드가 된다. (책에서 배운 **낮은 결합도 높은 응집도**를 가진 코드를 작성해야 한다)
        2. 많은 요소들의 대해 변경에 대비하면 낭비 일수도 있다. 
    3. 코드를 읽는 사람과의 의사소통
        1. 생각을 글로 표현하는것도 어렵다. 하물며 하나의 로직을 다른사람이 알아 볼 수 있는 코드를 작성한다는 것은 사람마다 생각하는 방법이 다르기 때문에 글로 표현하는 것보다 어렵다. 
        2. 한 객체의 행동(메소드?)에 대해 다른 객체에서 결정하도록 하는것은 좋지 않다. 객체 자신의 성격(프로퍼티?)은 가급적 자신이 결정하도록 코드를 작성하는 것이 좋다. → 각 객체는 자신을 스스로 책임져야 한다.
        3. 객체 = 자율적인 존재 = 의인화
2. 절차지향 → 절차지향은 지양하고 객체지향으로 설계를 하자!
    1. 정의 : 프로세스와 데이터를 별도의 모듈에 위치시키는 방식
    2. 데이터의 변경으로 인한 영향을 지역적으로 고립시키기 어렵다
    3. 객체간의 의존성(결합도)가 높다. → 하나의 객체 변경시에 관련된 객체를 모두 변경해야 될 수도 있는 상황이 벌어진다.
3. 객체지향 설계 = 설계란 코드를 배치하는 것. = 레고 ㅋㅋ

---

### 참고링크

**리팩토링의 중요성**

[https://www.youtube.com/watch?v=mNPpfB8JSIU](https://www.youtube.com/watch?v=mNPpfB8JSIU)

**소프트웨어 아키텍처의 중요성**

[https://www.youtube.com/watch?v=4E1BHTvhB7Y](https://www.youtube.com/watch?v=4E1BHTvhB7Y)

---

### 파이썬 관련 문법

파이썬 프로퍼티 선언

```python
def __init__(self):
        self._when: datetime = None # 일반적으로 프로퍼티명 앞에 '_'를 붙이면 private
				self.what: string = None # 프로퍼티명 앞에 아무것도 붙이지 않고 선언한 경우 public
```

파이썬에서는 하나의 클래스에 ‘__init__’ 메소드는 하나만 지정가능하다

대신에 args, kwargs 를 이용해서 처리 할 수 있다.

```python
def __init__(self, *args, **kwargs):
        #args -- tuple of anonymous arguments
        #kwargs -- dictionary of named arguments
```

파이썬 dict자료형에서 key 검색

```python
# 첫번째 방법
if 'key_name' in dict.keys() :
# 두번쨰 방법
dict.has_key('key_name')
```

private 메소드

```python
def __메소드명(self, [param1….])
```

파라미터 타입 지정

```python
def 메소드명(self, param:int):
# int타입의 파라미터를 입력받는다
```

결과타입 지정

```python
def 메소드명(self)→int:
# int타입의 결과타입을 반환한다.
```