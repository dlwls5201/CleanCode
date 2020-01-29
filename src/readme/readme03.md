# 함수

- 함수가 읽기 쉽고 이해하기 쉬운 이유는 무엇일까?
- 의도를분명히 표현하는 함수를 어떻게 구현할 수 있을까?
- 함수에 어떤 속성을 부여해야 처음 읽는 사람이 프로그램 내부를 직관적으로 파악할 수 있을까？

```java
public static String renderPageWithSetupsAndTeardowns(
    PageData pageData, boolean isSuite
) throws Exception {

    boolean isTestPage = pageData.hasAttribute("Test");

    if (isTestPage) {
        WikiPage testPage = pageData.getWikiPage() ;
        StringBuffer newPageContent = new StringBuffer();

        includeSetupPages(testPage, newPageContent, isSuite);

        newPageContent.append(pageData.getContent());

        includeTeardownPages(testPage, newPageContent, isSuite);

        pageData.setContent(newPageContent.toString());
        }
    return pageData.getHtml();
}
```
위 함수는 설정 페이지와 해제 페이지를 테스트 페이지에 넣은 후 해당 테스트 페이지를 HTML로 랜더링하는 코드이다.

### 작게 만들어라!

```java
public static String renderPageWithSetupsAndTeardowns(
    PageData pageData, boolean isSuite
) throws Exception {
    if (isTestPage(pageData))
    includeSetupAndTeardownPages(pageData, isSuite);

    return pageData.getHtml();
}
```

### 한 가지만 해라

첫 번째 코드는 버퍼를 생성하고, 페이지를 가져오고, 상속된 페이지를 검색하고, 경로를 렌더링하고, 불가사의한 문자열을 덧붙이고, HTML을 생성한다.
하지만 두 번째 코드는 '설정 페이지와 해제 페이지를 테스트 페이지에 넣는다.'

### 함수 당 추상화 수준은 하나로

getHtml()은 추상화 수준이 아주 높다. 반면，String pagePathName = PathPanser.render(pagepath)는 추상화 수준이 중간이다.
그리고 .append(\n”)와 같은 코드는 추상화 수준이 아주 낮다. 한 함수 내에 추상화 수준을 섞으면 코드를 읽는 사람이 헷갈린다. 특정 표현이 근본 개념인지 아니면 세부사항인지 구분하기 어려운 탓이다.

### 서술적인 이름을 사용하라

“코드를 읽으면서 짐작했던 기능을 각 루틴이 그대로 수행한다면 깨끗한 코드라 불러도 되겠다.” 한 가지만 하는 작은 함수에 좋은 이름을 붙인다면 이런 원칙을 달성함에 있어 이미 절반은 성공했다.
함수가 작고 단순할수록 서술적인 이름을 고르기도 쉬워진다.

### 함수 인수

함수에서 이상적인 인수 개수는 0개(무항)다. 다음은 1개(단항)고， 다음은 2개(이항)다. 3개(삼항)는 가능한 피하는 편이 좋다.
4개 이상(다항)은 특별한 이유가 필요하다. 특별한 이유가 있어도 사용하면 안 된다.

**동사와 키워드**

- 함수의 의도나 인수의 순서와 의도를 제대로 표현하려면 좋은 함수 이름이 수다. 단항 함수는 함수와 인수가 동사/명사 쌍을 이뤄야 한다.

예를 들어, write(name)은 누구나 곧바로 이해한다. ‘name’이 무엇이든‘ write’는 뜻이다.
좀 더 나은 이름은 writeField(name)이다. 그러면 ‘name’이 'field’라는 사실이 분명히 드러난다.

- 함수 이름에 키워드를 추가하는 형식이다. 즉, 함수 이름에 인수 이름을 넣는다.

예를 들어, assertEquals보다 assertExpectedEqualsActual(expected, actual)이 더 좋다. 그러면 인수 순서를 기억할 필요가 없어진다.

### 부수 효과를 일으키지 마라

```java
public class UserValidator {
    private Cryptographer cryptographer;

    public boolean checkPassword(String userName, String password) {
        User user = UserGateway.findByName(userName);

        if (user != User.NULL) {
            String codedPhrase = user.getPhraseEncodedByPassword();
            String phrase = cryptographer.decrypt(codedPhrase, password);
            if ("Valid Password".equals(phrase)) {
                Session.initialize();
                return true;
            }
        }
    }
    return false;
}
```
함수가 일으키는 부수 효과는 Session.initialize() 호출이다. checkPassword 함수는 이름 그대로 암호를 확인한다.
이름만 봐서는 세션을 초기화한다는 사실이 드러나지 않는다. 그래서 함수 이름만 보고 함수를 호출하는 사용자는 사용자를 인증하면서 기존 세션 정보를 지워버릴 위험에 처한다.

**출력인수**

```java
public void appendFooter(StringBuffer report)
```

인수 s가 출력 인수라는 사실은 분명하지만 함수 선언부를 찾아보고 나서야 알 수 있다.
함수 선언부를 찾아보는 행위는 코드를 보다가 주춤하는 행위와 동급이다.
인지적으로 거슬린다는 뜻이므로 피해야 한다.

```java
report.append Footer()
```

일반적으로 출력 인수는 피해야 한다. 함수에서 상태를 변경해야 한다면 함수가속한 객체 상태를 변경하는 방식을 택한다.

### 명령과 조회를 분리하라

```java
public boolean set(String attribute, String value);
```

이 함수는 이름이 attribute인 속성을 찾아 값을 value로 설정한 후 성공하면 true를 반환하고 실패하면 false를 반환한다.
그래서 다음과 같이 괴상한 코드가 나온다.

```java
if (set("username", "unclebob"))...
```

함수를 호출하는 코드만 봐서는 의미가 모호하다. “set”이라는 단어가 동사인지 형용사인지 분간하기 어려운 탓이다.

```java
if (attributeExists('username")) {
setAttribute('username", "unclebob");
}
```

함수는 뭔가를 수행하거나 뭔가에 답하거나 둘 중 하나만 해야 한다. 둘 다 하면 안 된다.
객체 상태를 변경하거나 아니면 객체 정보를 반환하거나 둘 중 하나다. 둘 다 하면 혼란을 초래한다.







