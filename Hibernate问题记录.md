1. 使用关联映射时，需要数据库必须建立关联映射

   Many-to-One,则必定有外键存在

2. 查询语句 HQL 面向对象

3. Hibernate不允许修改主键，即调用主键set方法

4. 由于Hibernate懒加载，在Article对象访问其属性的User对象时，报错