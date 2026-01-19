<div style="display: flex; justify-content: center; width: 100%; font-size: 32px; color: steelblue">
PGS Backend
</div>

# Models
```txt
plots - участки:
    - id: bigint
    - name: varchar(255) - Название участка
    
type_works - виды работ:
    - id: bigint
    - name: varchar(255) - Название вида работ
    
subtype_works - типы работ:
    - id: bigint
    - name: varchar(255) - Названия подтипа работ
    - type_work_id: bigint
    
plans - планы:
    - id: bigint
    - plot_id: bigint - id участка
    - type_work_id: bigint - id вида работ
    - subtype_work_id: bigint - id подтипа работ
    - production_name: varchar(255) - nullable - Название выработки
    - volume: float - Объём работы который нужно выполнить
    - start_date: date - nullable - Дата начала работ
    - end_date: date - nullable - Дата окончания работ
    
work_reports - отчёты по работым:
    - id: bigint
    - plan_id: bigint - id плана
    - fact: float - факт выполненой работы
    - delta: float - разница между текущим фактом и последним отправленным фактом
    - date: date - дата отправки отчёта
    - who_send: varchar(255) - кто отправил
    - machine: varchar(255) - nullable - название станка
    - comment: text - nullable - комментарии к выполненой работе
    
```

