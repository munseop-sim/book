## 파이썬 문법

### static method  생성  
@staticmethod -> 메소드 위에 해당 어노테이션 추가

---

### 파이썬에서 자기자신의 타입을 파라미터로 입력받거나, 결과로 리턴할 때에서 TypeHint를 주면 에러가 발생하나
'from __future__ import annotations'를 하면 에러가 발생되지 않음.  
참고URL -> https://stackoverflow.com/questions/33533148/how-do-i-type-hint-a-method-with-the-type-of-the-enclosing-class

---

### 파이썬에서 interface, abstract 클래스사용
```python
from abc import *

class 클래스명(metaclass=ABCMeta):
    #인터페이스 처럼 사용
    @abstractmethod
    def 메서드명:
        raise NotImplementedError

    @abstractmethod
    def 메서드명(self):
        pass   
```