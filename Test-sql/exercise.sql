-- 1、查询"01"课程比"02"课程成绩高的学生的信息及课程分数

	SELECT
		stu.*,
		sc1.s_score AS `1_score`,
		sc2.s_score AS `2_score`
	FROM
		student stu
		INNER JOIN score sc1 ON stu.s_id = sc1.s_id
		AND sc1.c_id = '01'
		LEFT JOIN score sc2 ON stu.s_id = sc2.s_id
		AND sc2.c_id = '02'
	WHERE
		sc1.s_score > sc2.s_score;

-- 2、查询"01"课程比"02"课程成绩低的学生的信息及课程分数

	SELECT
		stu.*,
		sc1.s_score AS `1_score`,
		sc2.s_score AS `2_score`
	FROM
		student stu
		INNER JOIN score sc1 ON stu.s_id = sc1.s_id
		AND sc1.c_id = '01'
		LEFT JOIN score sc2 ON stu.s_id = sc2.s_id
		AND sc2.c_id = '02'
	WHERE
		sc1.s_score < sc2.s_score;

-- 3、查询平均成绩大于等于60分的同学的学生编号和学生姓名和平均成绩

	SELECT
		st.s_id,
		st.s_name,
		round( avg( s_score ), 2 ) AS avg_score
	FROM
		student st
		JOIN score sc ON st.s_id = sc.s_id
	GROUP BY
		sc.s_id
	HAVING
		round( avg( s_score ), 2 ) >= 60;

-- 4、查询平均成绩小于60分的同学的学生编号和学生姓名和平均成绩
        -- (包括有成绩的和无成绩的)

	SELECT
		st.s_id,
		st.s_name,
		round( avg( s_score ), 2 ) AS avg_score
	FROM
		student st
		INNER JOIN score sc ON st.s_id = sc.s_id
	GROUP BY
		sc.s_id
	HAVING
		round( avg( s_score ), 2 ) < 60 UNION
	SELECT
		st.s_id,
		st.s_name,
		0 AS avg_score
	FROM
		student st
	WHERE
		st.s_id NOT IN ( SELECT DISTINCT s_id FROM score )

-- 5、查询所有同学的学生编号、学生姓名、选课总数、所有课程的总成绩

	SELECT
		st.s_id,
		st.s_name,
		count( sc.c_id ) AS sum_course,
		sum( sc.s_score ) AS sum_score
	FROM
		student st
		LEFT JOIN score sc ON st.s_id = sc.s_id
	GROUP BY
		st.s_id;

-- 6、查询"李"姓老师的数量

	SELECT
		count( * )
	FROM
		teacher
	WHERE
		t_name LIKE CONCAT( "李", "%" );

-- 7、查询学过"张三"老师授课的同学的信息

	SELECT
		st.*
	FROM
		student st
		INNER JOIN score sc ON st.s_id = sc.s_id
	WHERE
		sc.c_id IN ( SELECT c_id FROM course WHERE t_id = ( SELECT t_id FROM teacher WHERE t_name = '张三' ) );

-- 8、查询没学过"张三"老师授课的同学的信息

	SELECT
		*
	FROM
		student st1
	WHERE
		s_id NOT IN (
	SELECT
		st.s_id
	FROM
		student st
		INNER JOIN score sc ON st.s_id = sc.s_id
	WHERE
		sc.c_id IN ( SELECT c_id FROM course WHERE t_id = ( SELECT t_id FROM teacher WHERE t_name = '张三' ) )
	);

-- 9、查询学过编号为"01"并且也学过编号为"02"的课程的同学的信息

	SELECT
		st.*
	FROM
		student st,
		score sc1,
		score sc2
	WHERE
		st.s_id = sc1.s_id
		AND st.s_id = sc2.s_id
		AND sc1.c_id = '01'
		AND sc2.c_id = '02';

-- 10、查询学过编号为"01"但是没有学过编号为"02"的课程的同学的信息

	SELECT
		*
	FROM
		student
	WHERE
		s_id IN ( SELECT s_id FROM score sc1 WHERE c_id = '01' )
		AND s_id NOT IN ( SELECT s_id FROM score sc1 WHERE c_id = '02' )

-- 11、查询没有学全所有课程的同学的信息

	SELECT
		*
	FROM
		student
	WHERE
		s_id  IN ( SELECT s_id FROM score GROUP BY s_id HAVING count( * ) < ( SELECT count( * ) FROM course ) );

-- 12、查询至少有一门课与学号为"01"的同学所学相同的同学的信息

	SELECT
		*
	FROM
		student
	WHERE
		s_id IN ( SELECT DISTINCT s_id FROM score WHERE c_id IN ( SELECT sc.c_id FROM score sc WHERE sc.s_id = '01' ) );

-- 13、查询和"01"号的同学学习的课程完全相同的其他同学的信息

	SELECT
		*
	FROM
		student
	WHERE
		s_id IN (
	SELECT
		sc2.s_id
	FROM
		score sc2
	WHERE
		s_id <> '01'
		AND sc2.c_id IN ( SELECT c_id FROM score sc1 WHERE s_id = '01' )
	GROUP BY
		sc2.s_id
	HAVING
		count( * ) = ( SELECT count( * ) FROM score sc1 WHERE s_id = '01' )
	);

-- 14、查询没学过"张三"老师讲授的任一门课程的学生姓名

	SELECT
		*
	FROM
		student
	WHERE
		s_id NOT IN (
	SELECT
		s_id
	FROM
		score sc1
	WHERE
		c_id IN ( SELECT c_id FROM course WHERE t_id = ( SELECT t_id FROM teacher WHERE t_name = '张三' ) )
	GROUP BY
		s_id
		);

-- 15、查询两门及其以上不及格课程的同学的学号，姓名及其平均成绩
SELECT
	st.s_id,
	st.s_name,
	st.s_sex,
	st.s_birth,
	round(avg( sc2.s_score ))
FROM
	student st
	INNER JOIN score sc2 ON st.s_id = sc2.s_id
WHERE
	st.s_id IN ( SELECT s_id FROM score sc1 WHERE s_score < 60 GROUP BY s_id HAVING count( 1 ) >= 2 )
GROUP BY
	st.s_id;

-- 16、检索"01"课程分数小于60，按分数降序排列的学生信息

SELECT
	st1.*
FROM
	student st1
WHERE
	s_id IN ( SELECT s_id FROM score sc1 WHERE c_id = '01' AND s_score < 60 ORDER BY s_score DESC )

-- 17、按平均成绩从高到低显示所有学生的所有课程的成绩以及平均成绩

SELECT
	s_id,
	( SELECT s_score FROM score WHERE c_id = '01' AND s_id = sc1.s_id ) as `01`,
	( SELECT s_score FROM score WHERE c_id = '02' AND s_id = sc1.s_id ) as `02`,
	( SELECT s_score FROM score WHERE c_id = '03' AND s_id = sc1.s_id ) as `03`,
	ROUND(avg(s_score))
FROM
	score sc1 group by s_id order by avg(s_score) DESC


-- 18.查询各科成绩最高分、最低分和平均分：以如下形式显示：课程ID，课程name，最高分，最低分，平均分，及格率，中等率，优良率，优秀率

	SELECT
		sc.c_id,
		co.c_name,
		MAX(s_score) '最高分',
		MIN( s_score ) '最低分',
		ROUND(AVG(s_score),2) as '平均分',
		ROUND((SUM( CASE WHEN sc.s_score >= 60 THEN 1 ELSE 0 END )/SUM( CASE WHEN sc.s_score THEN 1 ELSE 0 END )),2) AS '及格率',
		ROUND((SUM( CASE WHEN sc.s_score >= 70 AND sc.s_score <= 80 THEN 1 ELSE 0 END )/SUM( CASE WHEN sc.s_score THEN 1 ELSE 0 END )),2 ) AS '中等率',
		ROUND((SUM( CASE WHEN sc.s_score >= 80 AND sc.s_score <= 90 THEN 1 ELSE 0 END )/SUM( CASE WHEN sc.s_score THEN 1 ELSE 0 END )),2 ) AS '优良率',
		ROUND((SUM( CASE WHEN sc.s_score >= 90 THEN 1 ELSE 0 END )/SUM(CASE WHEN sc.s_score THEN 1 ELSE 0 END)),2) AS '优秀率'
	FROM
		score sc LEFT JOIN course co ON sc.c_id = co.c_id
	GROUP BY
		sc.c_id

-- 19、按各科成绩进行排序，并显示排名(实现不完全)
SELECT
	a.s_id,
	a.c_id,
	@i := @i + 1 AS i保留排名,
	@k := ( CASE WHEN @score = a.s_score THEN @k ELSE @i END ) AS rank不保留排名,
	@score := a.s_score AS score
FROM
	( SELECT s_id, c_id, s_score FROM score WHERE c_id = '01' ORDER BY s_score DESC ) a,
	( SELECT @k := 0, @i := 0, @score := 0 ) s UNION
SELECT
	a.s_id,
	a.c_id,
	@i := @i + 1 AS i,
	@k := ( CASE WHEN @score = a.s_score THEN @k ELSE @i END ) AS `rank`,
	@score := a.s_score AS score
FROM
	( SELECT s_id, c_id, s_score FROM score WHERE c_id = '02' ORDER BY s_score DESC ) a,
	( SELECT @k := 0, @i := 0, @score := 0 ) s UNION
SELECT
	a.s_id,
	a.c_id,
	@i := @i + 1 AS i,
	@k := ( CASE WHEN @score = a.s_score THEN @k ELSE @i END ) AS `rank`,
	@score := a.s_score AS score
FROM
	( SELECT s_id, c_id, s_score FROM score WHERE c_id = '03' ORDER BY s_score DESC ) a,
	( SELECT @k := 0, @i := 0, @score := 0 ) s


-- 20、查询学生的总成绩并进行排名
	SELECT
		s_id,
		sum( s_score )
	FROM
		score sc
	GROUP BY
		s_id
	ORDER BY
		sum( s_score ) DESC

-- 21、查询不同老师所教不同课程平均分从高到低显示
SELECT
	te1.t_id,
	te1.t_name,
	round(avg(sc1.s_score))
FROM
	teacher te1
	INNER JOIN course co1 ON te1.t_id = co1.t_id
	INNER JOIN score sc1 ON co1.c_id = sc1.c_id
GROUP BY
	( te1.t_id )
	order by avg(sc1.s_score)

-- 22、查询所有课程的成绩第2名到第3名的学生信息及该课程成绩
select d.*,c.排名,c.s_score,c.c_id from (select a.s_id,a.s_score,a.c_id,@i:=@i+1 as 排名 from score a,(select @i:=0)s where a.c_id='01')c
left join student d on c.s_id=d.s_id where 排名 BETWEEN 2 AND 3 UNION
select d.*,c.排名,c.s_score,c.c_id from (select a.s_id,a.s_score,a.c_id,@j:=@j+1 as 排名 from score a,(select @j:=0)s where a.c_id='02')c
left join student d on c.s_id=d.s_id where 排名 BETWEEN 2 AND 3 UNION
select d.*,c.排名,c.s_score,c.c_id from (select a.s_id,a.s_score,a.c_id,@k:=@k+1 as 排名 from score a,(select @k:=0)s where a.c_id='03')c
 left join student d on c.s_id=d.s_id where 排名 BETWEEN 2 AND 3;

23、统计各科成绩各分数段人数：课程编号,课程名称,[100-85],[85-70],[70-60],[0-60]及所占百分比


        select distinct f.c_name,a.c_id,b.`85-100`,b.百分比,c.`70-85`,c.百分比,d.`60-70`,d.百分比,e.`0-60`,e.百分比 from score a
                left join (select c_id,SUM(case when s_score >85 and s_score <=100 then 1 else 0 end) as `85-100`,
                                            ROUND(100*(SUM(case when s_score >85 and s_score <=100 then 1 else 0 end)/count(*)),2) as 百分比
                                from score GROUP BY c_id)b on a.c_id=b.c_id
                left join (select c_id,SUM(case when s_score >70 and s_score <=85 then 1 else 0 end) as `70-85`,
                                            ROUND(100*(SUM(case when s_score >70 and s_score <=85 then 1 else 0 end)/count(*)),2) as 百分比
                                from score GROUP BY c_id)c on a.c_id=c.c_id
                left join (select c_id,SUM(case when s_score >60 and s_score <=70 then 1 else 0 end) as `60-70`,
                                            ROUND(100*(SUM(case when s_score >60 and s_score <=70 then 1 else 0 end)/count(*)),2) as 百分比
                                from score GROUP BY c_id)d on a.c_id=d.c_id
                left join (select c_id,SUM(case when s_score >=0 and s_score <=60 then 1 else 0 end) as `0-60`,
                                            ROUND(100*(SUM(case when s_score >=0 and s_score <=60 then 1 else 0 end)/count(*)),2) as 百分比
                                from score GROUP BY c_id)e on a.c_id=e.c_id
                left join course f on a.c_id = f.c_id

-- 24、查询学生平均成绩及其名次

        select a.s_id,
                @i:=@i+1 as '不保留空缺排名',
                @k:=(case when @avg_score=a.avg_s then @k else @i end) as '保留空缺排名',
                @avg_score:=avg_s as '平均分'
        from (select s_id,ROUND(AVG(s_score),2) as avg_s from score GROUP BY s_id)a,(select @avg_score:=0,@i:=0,@k:=0)b;
-- 25、查询各科成绩前三名的记录
            -- 1.选出b表比a表成绩大的所有组
            -- 2.选出比当前id成绩大的 小于三个的
        select a.s_id,a.c_id,a.s_score from score a
            left join score b on a.c_id = b.c_id and a.s_score<b.s_score
            group by a.s_id,a.c_id,a.s_score HAVING COUNT(b.s_id)<3
            ORDER BY a.c_id,a.s_score DESC

-- 26、查询每门课程被选修的学生数

        select c_id,count(s_id) from score a GROUP BY c_id

-- 27、查询出只有两门课程的全部学生的学号和姓名
        select s_id,s_name from student where s_id in(
                select s_id from score GROUP BY s_id HAVING COUNT(c_id)=2);

-- 28、查询男生、女生人数
        select s_sex,COUNT(s_sex) as 人数  from student GROUP BY s_sex

-- 29、查询名字中含有"风"字的学生信息

        select * from student where s_name like '%风%';

-- 30、查询同名同性学生名单，并统计同名人数

        select a.s_name,a.s_sex,count(*) from student a  JOIN
                    student b on a.s_id !=b.s_id and a.s_name = b.s_name and a.s_sex = b.s_sex
        GROUP BY a.s_name,a.s_sex



-- 31、查询1990年出生的学生名单

        select s_name from student where s_birth like '1990%'

-- 32、查询每门课程的平均成绩，结果按平均成绩降序排列，平均成绩相同时，按课程编号升序排列

    select c_id,ROUND(AVG(s_score),2) as avg_score from score GROUP BY c_id ORDER BY avg_score DESC,c_id ASC

-- 33、查询平均成绩大于等于85的所有学生的学号、姓名和平均成绩

    select a.s_id,b.s_name,ROUND(avg(a.s_score),2) as avg_score from score a
        left join student b on a.s_id=b.s_id GROUP BY s_id HAVING avg_score>=85

-- 34、查询课程名称为"数学"，且分数低于60的学生姓名和分数

        select a.s_name,b.s_score from score b LEFT JOIN student a on a.s_id=b.s_id where b.c_id=(
                    select c_id from course where c_name ='数学') and b.s_score<60

-- 35、查询所有学生的课程及分数情况；


        select a.s_id,a.s_name,
                    SUM(case c.c_name when '语文' then b.s_score else 0 end) as '语文',
                    SUM(case c.c_name when '数学' then b.s_score else 0 end) as '数学',
                    SUM(case c.c_name when '英语' then b.s_score else 0 end) as '英语',
                    SUM(b.s_score) as  '总分'
        from student a left join score b on a.s_id = b.s_id
        left join course c on b.c_id = c.c_id
        GROUP BY a.s_id,a.s_name


 -- 36、查询任何一门课程成绩在70分以上的姓名、课程名称和分数；
            select a.s_name,b.c_name,c.s_score from course b left join score c on b.c_id = c.c_id
                left join student a on a.s_id=c.s_id where c.s_score>=70



-- 37、查询不及格的课程
        select a.s_id,a.c_id,b.c_name,a.s_score from score a left join course b on a.c_id = b.c_id
            where a.s_score<60

--38、查询课程编号为01且课程成绩在80分以上的学生的学号和姓名；
        select a.s_id,b.s_name from score a LEFT JOIN student b on a.s_id = b.s_id
            where a.c_id = '01' and a.s_score>80

-- 39、求每门课程的学生人数
        select count(*) from score GROUP BY c_id;

-- 40、查询选修"张三"老师所授课程的学生中，成绩最高的学生信息及其成绩


        -- 查询老师id
        select c_id from course c,teacher d where c.t_id=d.t_id and d.t_name='张三'
        -- 查询最高分（可能有相同分数）
        select MAX(s_score) from score where c_id='02'
        -- 查询信息
        select a.*,b.s_score,b.c_id,c.c_name from student a
            LEFT JOIN score b on a.s_id = b.s_id
            LEFT JOIN course c on b.c_id=c.c_id
            where b.c_id =(select c_id from course c,teacher d where c.t_id=d.t_id and d.t_name='张三')
            and b.s_score in (select MAX(s_score) from score where c_id='02')


-- 41、查询不同课程成绩相同的学生的学生编号、课程编号、学生成绩
    select DISTINCT b.s_id,b.c_id,b.s_score from score a,score b where a.c_id != b.c_id and a.s_score = b.s_score


-- 42、查询每门功成绩最好的前两名
        -- 牛逼的写法
    select a.s_id,a.c_id,a.s_score from score a
        where (select COUNT(1) from score b where b.c_id=a.c_id and b.s_score>=a.s_score)<=2 ORDER BY a.c_id


-- 43、统计每门课程的学生选修人数（超过5人的课程才统计）。要求输出课程号和选修人数，查询结果按人数降序排列，若人数相同，按课程号升序排列
        select c_id,count(*) as total from score GROUP BY c_id HAVING total>5 ORDER BY total,c_id ASC

-- 44、检索至少选修两门课程的学生学号
        select s_id,count(*) as sel from score GROUP BY s_id HAVING sel>=2

-- 45、查询选修了全部课程的学生信息
        select * from student where s_id in(
            select s_id from score GROUP BY s_id HAVING count(*)=(select count(*) from course))


--46、查询各学生的年龄
    -- 按照出生日期来算，当前月日 < 出生年月的月日则，年龄减一

    select s_birth,(DATE_FORMAT(NOW(),'%Y')-DATE_FORMAT(s_birth,'%Y') -
                (case when DATE_FORMAT(NOW(),'%m%d')>DATE_FORMAT(s_birth,'%m%d') then 0 else 1 end)) as age
        from student;


-- 47、查询本周过生日的学生
    select * from student where WEEK(DATE_FORMAT(NOW(),'%Y%m%d'))=WEEK(s_birth)
    select * from student where YEARWEEK(s_birth)=YEARWEEK(DATE_FORMAT(NOW(),'%Y%m%d'))

    select WEEK(DATE_FORMAT(NOW(),'%Y%m%d'))

-- 48、查询下周过生日的学生
    select * from student where WEEK(DATE_FORMAT(NOW(),'%Y%m%d'))+1 =WEEK(s_birth)

-- 49、查询本月过生日的学生

    select * from student where MONTH(DATE_FORMAT(NOW(),'%Y%m%d')) =MONTH(s_birth)

-- 50、查询下月过生日的学生
    select * from student where MONTH(DATE_FORMAT(NOW(),'%Y%m%d'))+1 =MONTH(s_birth)