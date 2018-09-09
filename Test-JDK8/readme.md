# DateTimeAPI

## 实体类介绍

### Instant

​	Instant immutable and thread-safe,代表时间轴上某一唯一的时间点(最小单位纳秒);为了表示时间的准确性,使用long记录秒值(以1970-01-01T00:00:00Z为0计算,之前为负,之后为正),使用int记录纳秒值;

### LocalDate

​	LocalDate immutable and thread-safe, 代表ISO-8601日历体系中*无时区*日期(例2018-09-05,最小单位为日);可用于表示生日

### LocalDateTime

​	LocalDateTime immutable and thread-safe,代表ISO-8601日历体系中*有时区*的的时间(例2007-12-03T10:15:30,最小单位为秒)

### LocalTime

​	LocalTime immutable and thread-safe,代表ISO-8601日历体系中*无时区*的的时间(例10:15:30)

### DateTimeFormatter

​	格式化解析和打印date-time的工具类;可以通过Local,Chronology, ZoneId, and DecimalStyle创建;

​	主要的方法有两个,format(格式化)和parse(\解析)