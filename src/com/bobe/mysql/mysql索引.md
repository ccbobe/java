# mysal索引使用，创建及其优化

---

## mysal索引理论及其原理

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

- 全文索引（FULLTEXT）:

- 单列索引、多列索引

- 组合索引（最左前缀）

  ​