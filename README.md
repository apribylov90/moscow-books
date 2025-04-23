# Проект по автоматизации тестирования для книжного магазина [Moscow Books](https://www.moscowbooks.ru/)

> Moscow Books — один из ведущих магазинов по продаже книг.

## **Содержание:**
____

* <a href="#tools">Технологии и инструменты</a>

* <a href="#cases">Примеры автоматизированных тест-кейсов</a>

* <a href="#jenkins">Сборка в Jenkins</a>

* <a href="#console">Запуск из терминала</a>

* <a href="#allure">Allure отчет</a>

* <a href="#video">Примеры видео выполнения тестов на Selenoid</a>
____
<a id="tools"></a>
## Технологии и инструменты:

<p align="center">  
<a href="https://www.java.com/"><img src="assets/logo/java.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://junit.org/junit5/"><img src="assets/logo/junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://maven.apache.org/"><img src="assets/logo/maven.svg" width="100" height="50"  alt="Maven"/></a>  
<a href="https://www.selenium.dev/"><img src="assets/logo/selenium.svg" width="50" height="50"  alt="Selenium"/></a>  
<a href="https://aerokube.com/selenoid/"><img src="assets/logo/selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>  
<a href="https://github.com/allure-framework/allure2"><img src="assets/logo/allure.svg" width="50" height="50"  alt="Allure"/></a>    
<a href="https://www.jenkins.io/"><img src="assets/logo/jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>  
</p>

* Автотесты написаны на **Java**.
* В качестве билдера используется **Maven**.
* **JUnit 5** и **Selenium** - тестовые фреймворки.
* Для удаленного запуска реализована задача в **Jenkins** с генерацией отчета **Allure** и отправкой результата в **Telegram** через бота.

____
<a id="cases"></a>
**Примеры тест-кейсов:**</a>
____
- Вход пользователя
- Поиск по автору
- Проверка функционала избранного

____
<a id="jenkins"></a>
## Сборка в Jenkins
____
<p align="center">  
<a><img src="assets/screen/" alt="Jenkins" width="950"/></a>  
</p>


### **Параметры сборки в Jenkins:**
- *browser (браузер, по умолчанию chrome)*


<a id="console"></a>
## Команды для запуска из терминала
___
***Локальный запуск:***

```bash  
mvn clean test
"-Dbrowser=${browser}"
```
___
<a id="allure"></a>
## Allure отчет
___

### Основная страница отчёта

<p align="center">  
<img title="Allure Overview Dashboard" src="assets/screen/" width="850">  
</p>  

### Тест-кейсы

<p align="center">  
<img title="Allure Tests" src="assets/screen/" width="850">  
</p>

### Графики

<p align="center">  
<img title="Allure Graphics" src="assets/screen/" width="850">

<img title="Allure Graphics" src="assets/screen/" width="850">  
</p>

____
<a id="video"></a>
## Пример видео выполнения тестов на Selenoid
____
<p align="center">
<img title="Selenoid Video" src="assets/video/moscowbook-test-0.gif" width="550" height="350"  alt="video">   
</p>