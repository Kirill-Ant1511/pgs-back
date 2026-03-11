# Документация проекта `pgs-backend`

## 1. Общая информация

- Технологический стек: Spring Boot + REST API + Liquibase.
- Базовый URL в документации указан как относительный путь (например, `/plan`, `/report`).
- Формат данных: JSON.
- В контроллерах нет явного префикса `/api`, поэтому маршруты соответствуют путям из `@RequestMapping`.

## 2. REST API

Ниже перечислены все эндпоинты из контроллеров в `src/main/java/pal/comp/pgsbackend/controller`.

---

## 2.1 Машины (`/machine`)

Источник: `MachineController.java`

### `GET /machine`
Получить список машин.

- Query params: нет
- Ответ: `List<ResponseMachineDto>`

### `POST /machine`
Создать машину.

- Тело: `RequestMachineDto`
- Ответ: `ResponseMachineDto`

### `PUT /machine/{id}`
Обновить машину.

- Path params: `id: Long`
- Тело: `RequestMachineDto`
- Ответ: `ResponseMachineDto`

### `DELETE /machine/{id}`
Удалить машину.

- Path params: `id: Long`
- Ответ: пустой (`void`)

---

## 2.2 Планы (`/plan`)

Источник: `PlanController.java`

### `GET /plan`
Получить список планов с фильтрацией.

- Query params (все optional):
  - `plotId: Long`
  - `typeWorkId: Long`
  - `subtypeWorkId: Long`
  - `productionName: String`
  - `isActive: Boolean`
  - `page: Integer`
  - `size: Integer`
- Логика:
  - если переданы `page` и `size`, используется пагинация;
  - иначе возвращается список без пагинации.
- Ответ: `List<ResponsePlanDto>`

### `GET /plan/by-fk`
Получить один план по составному ключу.

- Query params (required):
  - `plotId: Long`
  - `typeWorkId: Long`
  - `subtypeWorkId: Long`
  - `productionName: String`
  - `isActive: Boolean`
- Ответ: `ResponsePlanDto`

### `GET /plan/{id}`
Получить план по ID.

- Path params: `id: Long`
- Ответ: `ResponsePlanDto`

### `POST /plan`
Создать план.

- Тело: `RequestCreatePlanDto`
- Ответ: `ResponsePlanDto`

### `POST /plan/add-machine`
Привязать машину к плану.

- Тело: `RequestAddMachineDto` (`planId`, `machineId`)
- Ответ: `ResponsePlanDto`

### `PATCH /plan/{id}`
Обновить план.

- Path params: `id: Long`
- Тело: `RequestUpdatePlanDto`
- Ответ: `ResponsePlanDto`

### `DELETE /plan/{id}`
Удалить план.

- Path params: `id: Long`
- Ответ: пустой (`void`)

---

## 2.3 Участки (`/plot`)

Источник: `PlotController.java`

### `GET /plot`
Получить список участков.

- Query params (optional):
  - `nameSubstring: String`
- Ответ: `List<PlotDto>`

### `GET /plot/{id}`
Получить участок по ID.

- Path params: `id: Long`
- Ответ: `PlotDto`

### `GET /plot/planing`
Получить участки для режима планирования.

- Query params: нет
- Ответ: `List<PlotDto>`

### `POST /plot`
Создать участок.

- Тело: `PlotDto`
- Ответ: `PlotDto`

### `PATCH /plot/{id}`
Обновить участок.

- Path params: `id: Long`
- Тело: `PlotDto`
- Ответ: `PlotDto`

### `DELETE /plot/{id}`
Удалить участок.

- Path params: `id: Long`
- Ответ: пустой (`void`)

---

## 2.4 Отчеты (`/report`)

Источник: `ReportController.java`

### `GET /report`
Получить список отчетов с фильтрацией.

- Query params (все optional):
  - `planId: Long`
  - `plotId: Long`
  - `typeWorkId: Long`
  - `subtypeWorkId: Long`
  - `productionName: String`
  - `startDate: LocalDate`
  - `endDate: LocalDate`
  - `constDate: LocalDate`
  - `page: Integer`
  - `size: Integer`
- Логика:
  - если переданы `page` и `size`, используется пагинация;
  - иначе — выборка без пагинации.
- Ответ: `List<ResponseReportDto>`

### `POST /report`
Создать отчет.

- Тело: `RequestCreateReportDto`
- Ответ: `ResponseReportDto`

### `DELETE /report/{id}`
Удалить отчет.

- Path params: `id: Long`
- Ответ: пустой (`void`)

---

## 2.5 Подвиды работ (`/subtype-work`)

Источник: `SubtypeWorkController.java`

### `GET /subtype-work`
Получить все подвиды работ.

- Query params: нет
- Ответ: `List<SubtypeWorkDto>`

### `GET /subtype-work/with-filters`
Получить подвиды работ с фильтрами и пагинацией.

- Query params (optional):
  - `name: String`
  - `code: String`
  - `typeWorkId: Long`
  - `pageNumber: Integer`
  - `pageSize: Integer`
- Ответ: `List<SubtypeWorkDto>`

### `GET /subtype-work/{id}`
Получить подвид работы по ID.

- Path params: `id: Long`
- Ответ: `SubtypeWorkDto`

### `GET /subtype-work/by-type-work/{id}`
Получить подвиды работ по виду работы.

- Path params: `id: Long` (ID вида работы)
- Ответ: `List<SubtypeWorkDto>`

### `GET /subtype-work/by-name`
Получить подвид работы по имени.

- Query params:
  - `name: String` (required)
- Ответ: `SubtypeWorkDto`

### `GET /subtype-work/planing`
Получить подвиды работ для планирования.

- Query params (required):
  - `plotId: Long`
  - `typeWorkId: Long`
- Ответ: `List<SubtypeWorkDto>`

### `POST /subtype-work`
Создать подвид работы.

- Тело: `SubtypeWorkDto`
- Ответ: `SubtypeWorkDto`

### `PATCH /subtype-work/{id}`
Обновить подвид работы.

- Path params: `id: Long`
- Тело: `UpdateSubtypeWorkDto`
- Ответ: `SubtypeWorkDto`

### `DELETE /subtype-work/{id}`
Удалить подвид работы.

- Path params: `id: Long`
- Ответ: пустой (`void`)

---

## 2.6 Виды работ (`/type-work`)

Источник: `TypeWorkController.java`

### `GET /type-work`
Получить виды работ.

- Query params (optional):
  - `name: String`
  - `code: String`
- Ответ: `List<TypeWorkDto>`

### `GET /type-work/{id}`
Получить вид работы по ID.

- Path params: `id: Long`
- Ответ: `TypeWorkDto`

### `GET /type-work/by-name`
Получить вид работы по имени.

- Query params:
  - `name: String` (required)
- Ответ: `TypeWorkDto`

### `GET /type-work/planing`
Получить виды работ для планирования.

- Query params:
  - `plotId: Long` (required)
- Ответ: `List<TypeWorkDto>`

### `POST /type-work`
Создать вид работы.

- Тело: `TypeWorkDto`
- Ответ: `TypeWorkDto`

### `PATCH /type-work/{id}`
Обновить вид работы.

- Path params: `id: Long`
- Тело: `UpdateTypeWorkDto`
- Ответ: `TypeWorkDto`

### `DELETE /type-work/{id}`
Удалить вид работы.

- Path params: `id: Long`
- Ответ: пустой (`void`)

---

## 2.7 Пользователи (`/users`)

Источник: `UserController.java`

### `GET /users`
Получить список пользователей.

- Query params: нет
- Ответ: `List<ResponseUserDto>`

### `GET /users/{telegramId}`
Получить пользователя по Telegram ID.

- Path params: `telegramId: String`
- Ответ: `ResponseUserDto`

### `POST /users`
Создать пользователя.

- Тело: `RequestUserDto`
- Ответ: `ResponseUserDto`

### `POST /users/add-plot`
Привязать участок к пользователю.

- Тело: `RequestAddPlotForUserDto` (`userId`, `plotId`)
- Ответ: `ResponseUserDto`

### `PATCH /users/{id}`
Обновить пользователя.

- Path params: `id: Long`
- Тело: `RequestUpdateUserDto`
- Ответ: `ResponseUserDto`

### `DELETE /users/{id}`
Удалить пользователя.

- Path params: `id: Long`
- Ответ: пустой (`void`)

---

## 3. Схема базы данных

Ниже описана актуальная схема по Liquibase миграциям из:
- `src/main/resources/db/changelog/init/init_db.yaml`
- `src/main/resources/db/changelog/changeset/*.yaml`

## 3.1 Таблица `plots`

| Поле | Тип | Null | Ограничения |
|---|---|---|---|
| `id` | `bigint` | NO | PK, auto increment |
| `name` | `varchar(255)` | NO | UNIQUE (`uk_plots_name`) |

## 3.2 Таблица `type_works`

| Поле | Тип | Null | Ограничения |
|---|---|---|---|
| `id` | `bigint` | NO | PK, auto increment |
| `code` | `varchar(255)` | NO |  |
| `name` | `varchar(255)` | NO | UNIQUE (`uk_type_works_name`) |

## 3.3 Таблица `subtype_works`

| Поле | Тип | Null | Ограничения |
|---|---|---|---|
| `id` | `bigint` | NO | PK, auto increment |
| `code` | `varchar(255)` | NO |  |
| `name` | `varchar(255)` | NO | UNIQUE (`uk_subtype_works_name`) |
| `unit_metering` | `varchar(255)` | NO |  |
| `type_work_id` | `bigint` | NO | FK -> `type_works(id)` (`fk_subtype_works_type_works`) |

## 3.4 Таблица `plans`

| Поле | Тип | Null | Ограничения |
|---|---|---|---|
| `id` | `bigint` | NO | PK, auto increment |
| `plot_id` | `bigint` | NO | FK -> `plots(id)` (`fk_plans_plots`) |
| `type_work_id` | `bigint` | NO | FK -> `type_works(id)` (`fk_plans_type_works`) |
| `subtype_work_id` | `bigint` | NO | FK -> `subtype_works(id)` (`fk_plans_subtype_works`) |
| `production_name` | `varchar(255)` | NO | default `''` |
| `volume` | `float` | NO |  |
| `start_date` | `date` | YES |  |
| `end_date` | `date` | YES |  |
| `is_active` | `boolean` | NO | default `true` |

Доп. ограничение: UNIQUE (`plot_id`, `type_work_id`, `subtype_work_id`, `production_name`) -> `uk_plans_foreign_key_and_production_name`.

## 3.5 Таблица `reports`

| Поле | Тип | Null | Ограничения |
|---|---|---|---|
| `id` | `bigint` | NO | PK, auto increment |
| `plan_id` | `bigint` | NO | FK -> `plans(id)` (`fk_reports_plans`) |
| `fact` | `float` | NO |  |
| `delta` | `float` | NO | default `0` |
| `date` | `date` | NO |  |
| `who_send` | `varchar(255)` | NO |  |
| `machine` | `varchar(255)` | YES |  |
| `comment` | `text` | YES |  |

## 3.6 Таблица `users`

История: таблица создана как `project_managers`, затем переименована в `users`.

| Поле | Тип | Null | Ограничения |
|---|---|---|---|
| `id` | `bigint` | NO | PK, auto increment |
| `name` | `varchar(255)` | NO |  |
| `surname` | `varchar(255)` | NO |  |
| `telegram_id` | `varchar(255)` | NO | UNIQUE |
| `role` | `varchar(255)` | NO | default `USER`, CHECK `role IN ('PM', 'USER')` |

## 3.7 Таблица `user_plots`

Связующая таблица пользователей и участков (many-to-many).

| Поле | Тип | Null | Ограничения |
|---|---|---|---|
| `user_id` | `bigint` | NO | FK -> `users(id)` (`fk_user_plots_user`) |
| `plot_id` | `bigint` | NO | FK -> `plots(id)` (`fk_user_plots_plot`) |

Ключи и ограничения:
- PK (`user_id`, `plot_id`) -> `pk_user_plots`
- UNIQUE (`plot_id`, `user_id`) -> `uk_user_plots_ids` (дублирует уникальность PK в другом порядке)

## 3.8 Таблица `machines`

| Поле | Тип | Null | Ограничения |
|---|---|---|---|
| `id` | `bigint` | NO | PK, auto increment |
| `name` | `text` | NO | UNIQUE |

## 3.9 Таблица `machine_plans`

Связующая таблица машин и планов (many-to-many).

| Поле | Тип | Null | Ограничения |
|---|---|---|---|
| `machine_id` | `bigint` | NO | FK -> `machines(id)` (`fk_machine_plan_machine`) |
| `plan_id` | `bigint` | NO | FK -> `plans(id)` (`fk_plan_machine_plan`) |

Ключи и ограничения:
- PK (`machine_id`, `plan_id`) -> `pl_machine_plans`
- UNIQUE (`machine_id`, `plan_id`) -> `uk_machine_plans_idsЦ` (фактически дублирует PK)

## 4. Связи между таблицами (кратко)

- `subtype_works.type_work_id` -> `type_works.id`
- `plans.plot_id` -> `plots.id`
- `plans.type_work_id` -> `type_works.id`
- `plans.subtype_work_id` -> `subtype_works.id`
- `reports.plan_id` -> `plans.id`
- `user_plots.user_id` -> `users.id`
- `user_plots.plot_id` -> `plots.id`
- `machine_plans.machine_id` -> `machines.id`
- `machine_plans.plan_id` -> `plans.id`

## 5. Замечания по актуальному состоянию

- В API встречается название маршрута `planing` (без второго `n`) в нескольких контроллерах: это текущий контракт, менять нужно только осознанно.
- В миграции `add-machine-column.yaml` уникальный constraint назван `uk_machine_plans_idsЦ` (с символом `Ц`), это текущее имя в схеме.
- В контроллерах используются `@Valid`, поэтому фактические правила валидации зависят от аннотаций в конкретных DTO.

