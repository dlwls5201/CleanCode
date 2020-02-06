# 객체와 자료구조

### 자료 추상화

자료를 세세하게 공개하기보다는 추상적인 개념으로 표현하는 편이 좋다.

### 자료/객체 비대칭

자료 구조는 자료를 그대로 공개하며 별다른 함수는 제공하지 않는다.
객체는 추상화 뒤로 자료를 숨긴 채 자료를 다루는 함수만 공개한다.

**절차적인 도형**

```java
public class Square {
    public Point topLeft;
    public double side;
}
public class Rectangle {
    public Point topLeft;
    public double height;
    public double width;
}

public class Circle {
    public Point center;
    public double radius;
}

public class Geometry {
    public final double PI = 3.141592653589793;
    public double area(Object shape) throws NoSuchShapeException
    {
        if (shape instanceof Square) {
            Square s = (Square)shape;
            return s.side * s.side;
        }
        else if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle)shape;
            return r.height * r.width;
        }
        else if (shape instanceof Circle) {
            Circle c = (Circle)shape;
            return PI * c.radius * c.radius;
        }
        throw new NoSuchShapeException();
    }
}
```

1. (자료 구조를 사용하는) 절차적인 코드는 기존 자료 구조를 변경하지 않으면서 새 함수를 추가하기 쉽다.

> 위 코드에서 기존 자료구조는 Square, Rectangle, Circle를 의미한다.
코드를 보면 도형을 변경하지 않고 Geometry에 새로운 함수를 추가하기 쉽다는 의미로 필자는 해석한다.

2. 절차적인 코드는 새로운 자료 구조를 추가하기 어렵다. 그러려면 모든 함수를 고쳐야 한다.

> 새로운 자료 구조 즉 도형이 추가되면 모든 함수(area) 내부의 코드를 수정해야 한다!   


**객체적인 도형**

```java
public class Square implements Shape {
    private Point topLeft;
    private double side;

    public double areaO {
        return side*side;
    }
}

public class Rectangle implements Shape {
    private Point topLeft;
    private double height;
    private double width;

    public double area() {
        return height * width;
    }
}

public class Circle implements Shape {
    private Point center;
    private double radius;
    public final double PI = 3.141592653589793;

    public double area() {
        return PI * radius * radius;
    }
}
```

1. 객체 지향 코드는 기존 함수를 변경하지 않으면서 새 클래스를 추가하기 쉽다.

> area()를 변경하지 않으면서 새로운 도형을 추가하기 쉽다

2. 객체 지향 코드는 새로운 함수를 추가하기 어렵다. 그러려면 모든 클래스를 고쳐야 한다.

> Shape 인터페이스는 area() 함수를 가지고 있는데 새 함수가 추가되면 모든 도형에 함수를 추가해 주어야한다.

- 다시 말해, 객체 지향 코드에서 어려운 변경은 절차적인 코드에서 쉬우며, 절차적인 코드에서 어려운 변경은 객체 지향 코드에서 쉽다!
- 복잡한 시스템을 짜다 보면 새로운 함수가 아니라 새로운 자료 타입이 필요한 경우가 생긴다. 이때는 클래스와 객체 지향 기법이 가장 적합하다.
반면, 새로운 자료 타입이 아니라 새로운 함수가 필요한 경우도 생긴다. 이때는 절차적인 코드와 자료 구조가 좀 더 적합하다.


### 디미터 법칙

- 모듈은 자신이 조작하는 객체의 속사정을 몰라야 한다는 법칙

### 자료 전달 객체

- DTO(Data Transfer Object)
- 자료 구조체의 전형적인 형태는 공개 변수만 있고 함수가 없는 클래스다.
- 흔히 DTO는 데이터베이스에 저장된 가공되지 않은 정보를 애플리케이션 코드에서 사용할 객체로 변환하는 일련의 단계에서 가장 처음으로 사용하는 구조체다.

### 결론

- 객체는 동작을 공개하고 자료를 숨긴다. 그래서 기존 동작을 변경하지 않으면서 새 객체 타입을 추가하기는 쉬운 반면, 기존 객체에 새 동작을 추가하기는 어렵다.
- 자료 구조는 별다른 동작 없이 자료를 노출한다. 그래서 기존 자료 구조에 새 동작을 추가하기는 쉬우나，기존 함수에 새 자료 구조를 추가하기는 어렵다.

> (어떤) 시스템을 구현할 때，새로운 자료 타입을 추가하는 유연성 이 필요하면 객체가 더 적합하다.
다른 경우로 새로운 동작을 추가하는 유연성이 필요하면 자료 구조와 절차적인 코드가 더 적합하다.


