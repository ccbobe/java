# mysal索引使用，创建及其优化

---

## mysql索引理论及其原理

> 索引:是一种特殊的文件(InnoDB数据表上的索引是表空间的一个组成部分)，它们包含着对数据表里所有记录的引用指针。

- 普通索引:没有任何限制，MyIASM中默认的BTREE类型的索引，也是我们大多数情况下用到的索引。

  使用方法

  直接创建索引

  ```mysql
  create index index_name on table (column(length))
  ```

  修改表结构的方法添加索引

  ```mysql
  alter table table_name add index index_name on (column (length))
  ```

  创建表格的同时创建索引

  ```mysql
  create table table_name (
      id int(11) not null auto_increment,
    	name varchar(3) not null ,
    	title char(255) not null,
    	time int(10) null default null,
    	primary key(id),
    	index index_name (title(30))
  )
  ```

  删除表格索引

  ```mysql
  drop index index_name on table
  ```

-  唯一索引:与普通索引类似，不同的就是：索引列的值必须唯一，但允许有空值（注意和主键不同）。如果是组合索引，则列值的组合必须唯一，创建方法和普通索引类似。

  创建唯一索引

  ```mysql
  create unique index index_name on table (column(length))
  ```

  修改表结构创建唯一索引

  ```mysql
  alter table table_name add unique  index_name on(column(length))
  ```
  创建表结构的时候直接指定

  ``` mysql
  create table `table` (
  `id` int(11) NOT NULL AUTO_INCREMENT ,
  `title` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
  `time` int(10) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE indexName (title(length))
  );
  ```

- 全文索引（FULLTEXT）:

  FULLTEXT索引仅可用于 MyISAM 表；他们可以从CHAR、VARCHAR或TEXT列中作为CREATE TABLE语句的一部分被创建，或是随后使用ALTER TABLE 或CREATE INDEX被添加。////对于较大的数据集，将你的资料输入一个没有FULLTEXT索引的表中，然后创建索引，其速度比把资料输入现有FULLTEXT索引的速度更为快。不过切记对于大容量的数据表，生成全文索引是一个非常消耗时间非常消耗硬盘空间的做法。创建表结构的时候直接创建全文索引

  ```mysql
  CREATE TABLE `table` (
  `id` int(11) NOT NULL AUTO_INCREMENT ,
  `title` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
  `time` int(10) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`),
  FULLTEXT (content)
  );
  ```

  修改表结构创建全文索引

  ```mysql
  ALTER TABLE article ADD FULLTEXT index_content(content)
  ```

  直接创建索引

  ```mysql
  create fulltext index index_context on article(conrext)
  ```

- 单列索引、多列索引

  多个单列索引与单个多列索引的查询效果不同，因为执行查询时，MySQL只能使用一个索引，会从多个索引中选择一个限制最为严格的索引。

  ​

- 组合索引（最左前缀）

  平时用的SQL查询语句一般都有比较多的限制条件，所以为了进一步榨取MySQL的效率，就要考虑建立组合索引。例如上表中针对title和time建立一个组合索引：ALTER TABLE article ADD INDEX index_titme_time (title(50),time(10))。建立这样的组合索引，其实是相当于分别建立了下面两组组合索引：

  –title,time

  –title

  为什么没有time这样的组合索引呢？这是因为MySQL组合索引“最左前缀”的结果。简单的理解就是只从最左面的开始组合

  ```mysql
  – 使用到上面的索引
  SELECT * FROM article WHREE title='测试' AND time=1234567890;
  SELECT * FROM article WHREE utitle='测试';
  – 不使用上面的索引
  SELECT * FROM article WHREE time=1234567890;
  ```

  ## MySQL索引的优化

  ---

  因此索引也会有它的缺点：虽然索引大大提高了查询速度，同时却会降低更新表的速度，如对表进行INSERT、UPDATE和DELETE。因为更新表时，MySQL不仅要保存数据，还要保存一下索引文件。建立索引会占用磁盘空间的索引文件。一般情况这个问题不太严重，但如果你在一个大表上创建了多种组合索引，索引文件的会膨胀很快。索引只是提高效率的一个因素，如果你的MySQL有[大数据](http://lib.csdn.net/base/hadoop)量的表，就需要花时间研究建立最优秀的索引，或优化查询语句。下面是一些总结以及收藏的MySQL索引的注意事项和优化方法。

  * ## 何时使用聚集索引或非聚集索引？

    | 动作描述      | 使用聚集索引 | 使用非聚集索引 |
    | --------- | ------ | ------- |
    | 列经常被分组排序  | 使用     | 使用      |
    | 返回某范围内的数据 | 使用     | 不使用     |
    | 一个或极少不同值  | 不使用    | 不使用     |
    | 小数目的不同值   | 使用     | 不使用     |
    | 大数目的不同值   | 不使用    | 使用      |
    | 频繁更新的列    | 不使用    | 使用      |
    | 外键列       | 使用     | 使用      |
    | 主键列       | 使用     | 使用      |
    | 频繁修改索引列   | 不使用    | 使用      |

    事实上，我们可以通过前面聚集索引和非聚集索引的定义的例子来理解上表。

  *  ##索引不会包含有NULL值的列

  * ## 使用短索引

  *  ## 索引列排序

     MySQL查询只使用一个索引，因此如果where子句中已经使用了索引的话，那么order by中的列是不会使用索引的。因此数据库默认排序可以符合要求的情况下不要使用排序操作；尽量不要包含多个列的排序，如果需要最好给这些列创建复合索引。

  * ## like语句操作

    一般情况下不鼓励使用like操作，如果非使用不可，如何使用也是一个问题。like “%aaa%” 不会使用索引而like “aaa%”可以使用索引。

  * ## 不要在列上进行运算

    例如：select * from users where YEAR(adddate)<2007，将在每个行上进行运算，这将导致索引失效而进行全表扫描，因此我们可以改成：select * from users where adddate<’2007-01-01′。关于这一点可以围观：[一个单引号引发的MYSQL性能损失。](http://www.zendstudio.net/archives/single-quotes-or-no-single-quotes-in-sql-query)

    ## 查看索引

    ---

    ```mysql
    mysql中如何查看索引？
    show index from table_name;
    show keys from table_name;
    ```
