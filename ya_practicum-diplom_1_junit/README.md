# ДИПЛОМ
# 1 часть дипломной работы
## Юнит-тестирование


## Окружение
| Название |  Версия |
|----------|--------|
|jacoco-maven-plugin | 0.8.7 |
|mockito-core | 3.12.4 |
|junit | 4.13.2 |

### Клонирование репозитория

`git clone https://github.com/groTex22/Diplom_1`

## Описание
В проекте выполнены тесты классов с использованием моков, параметризации
Подробнее в описании к работе
Ссылка на урок: [Спринт 9/9 → Тема 1/2: Дипломная работа → Урок 3/7: Задание 1: юнит-тесты]( https://practicum.yandex.ru/learn/qa-automation-engineer-java/courses/a25b3ff1-fef4-431f-8c36-ed0bc1dfa048/sprints/283960/topics/dbe7c2dd-d034-4fec-8870-e4c9a44e4968/lessons/113ea47f-d4a0-4fe0-9f73-e7bf2316296e/#00c7b438-67de-47e3-8ab6-d6e70349c74a )
Вопрос: 

### Отчет покрытия тестов
[Отчет](target/site/jacoco/index.html)

<div id="breadcrumb" class="breadcrumb">&nbsp;</div>
<h1>Отчет по покрытию классов из задания</h1>
<table id="coveragetable" class="coverage" cellspacing="0">
<thead>
<tr>
<td id="a" class="sortable">Element</td>
<td id="b" class="down sortable bar">Missed Instructions</td>
<td id="c" class="sortable ctr2">Cov.</td>
<td id="d" class="sortable bar">Missed Branches</td>
<td id="e" class="sortable ctr2">Cov.</td>
<td id="f" class="sortable ctr1">Missed</td>
<td id="g" class="sortable ctr2">Cxty</td>
<td id="h" class="sortable ctr1">Missed</td>
<td id="i" class="sortable ctr2">Lines</td>
<td id="j" class="sortable ctr1">Missed</td>
<td id="k" class="sortable ctr2">Methods</td>
<td id="l" class="sortable ctr1">Missed</td>
<td id="m" class="sortable ctr2">Classes</td>
</tr>
</thead>
<tbody>
<tr>
<td id="a1"><a class="el_class" href="Burger.html">Burger</a></td>
<td id="b2" class="bar"><img title="133" src="../jacoco-resources/greenbar.gif" alt="133" width="120" height="10" /></td>
<td id="c0" class="ctr2">100&nbsp;%</td>
<td id="d0" class="bar"><img title="4" src="../jacoco-resources/greenbar.gif" alt="4" width="120" height="10" /></td>
<td id="e0" class="ctr2">100&nbsp;%</td>
<td id="f2" class="ctr1">0</td>
<td id="g0" class="ctr2">9</td>
<td id="h2" class="ctr1">0</td>
<td id="i0" class="ctr2">23</td>
<td id="j2" class="ctr1">0</td>
<td id="k0" class="ctr2">7</td>
<td id="l2" class="ctr1">0</td>
<td id="m2" class="ctr2">1</td>
</tr>
<tr>
<td id="a4"><a class="el_class" href="IngredientType.html">IngredientType</a></td>
<td id="b3" class="bar"><img title="24" src="../jacoco-resources/greenbar.gif" alt="24" width="21" height="10" /></td>
<td id="c1" class="ctr2">100&nbsp;%</td>
<td id="d3" class="bar">&nbsp;</td>
<td id="e3" class="ctr2">n/a</td>
<td id="f3" class="ctr1">0</td>
<td id="g5" class="ctr2">1</td>
<td id="h3" class="ctr1">0</td>
<td id="i5" class="ctr2">3</td>
<td id="j3" class="ctr1">0</td>
<td id="k5" class="ctr2">1</td>
<td id="l3" class="ctr1">0</td>
<td id="m3" class="ctr2">1</td>
</tr>
<tr>
<td id="a3"><a class="el_class" href="Ingredient.html">Ingredient</a></td>
<td id="b4" class="bar"><img title="21" src="../jacoco-resources/greenbar.gif" alt="21" width="18" height="10" /></td>
<td id="c2" class="ctr2">100&nbsp;%</td>
<td id="d4" class="bar">&nbsp;</td>
<td id="e4" class="ctr2">n/a</td>
<td id="f4" class="ctr1">0</td>
<td id="g1" class="ctr2">4</td>
<td id="h4" class="ctr1">0</td>
<td id="i3" class="ctr2">8</td>
<td id="j4" class="ctr1">0</td>
<td id="k1" class="ctr2">4</td>
<td id="l4" class="ctr1">0</td>
<td id="m4" class="ctr2">1</td>
</tr>
<tr>
<td id="a0"><a class="el_class" href="Bun.html">Bun</a></td>
<td id="b5" class="bar"><img title="15" src="../jacoco-resources/greenbar.gif" alt="15" width="13" height="10" /></td>
<td id="c3" class="ctr2">100&nbsp;%</td>
<td id="d5" class="bar">&nbsp;</td>
<td id="e5" class="ctr2">n/a</td>
<td id="f5" class="ctr1">0</td>
<td id="g3" class="ctr2">3</td>
<td id="h5" class="ctr1">0</td>
<td id="i4" class="ctr2">6</td>
<td id="j5" class="ctr1">0</td>
<td id="k3" class="ctr2">3</td>
<td id="l5" class="ctr1">0</td>
<td id="m5" class="ctr2">1</td>
</tr>
</tbody>
</table>
<div class="footer">&nbsp;</div>

## Запуск теста
`mvn clean test`

## Для создания отчета в Jacoco ввести команду
`mvn clean verify`



#### ________________ ↓↓↓чтобы проверка не была рутиной ↓↓↓ _______________
![картинка](https://avatars.mds.yandex.net/i?id=5931a1aa8781130ab76cc5ede08ae1f1_l-4219930-images-thumbs&n=13)

