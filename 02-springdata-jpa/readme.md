### 自定义操作
1. jpql（原生SQL）
   a. @Query() 查询如果返回单个实体，就用pojo接收，如果是多个，就用list接收
   1. 
2. 规定的方法名
   就是说只需要按照Jpa提供的规范来书写方法名，就可以实现一些功能

下面三种可以用来动态条件查询
3. Query By Example  
   只能支持字符串查询，不支持嵌套的方式查询
4. 通过Specifications
5. 通过 Querydsl