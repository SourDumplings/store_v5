<!--
 * @Author: SourDumplings
 * @Date: 2019-07-13 17:05:32
 * @Link: https://github.com/SourDumplings/
 * @Email: changzheng300@foxmail.com
 * @Description: 黑马程序员JavaWeb2018阶段项目：电子商务企业实战项目
 -->
# store_v5
黑马程序员JavaWeb2018阶段项目：电子商务企业实战项目

采用技术：非框架的Servlet、JSP、JSTL、JDBC、C3P0等

环境需求：

- MySQL数据库，并运行store_07.sql
- Redis数据库

部署到Linux：

1. 修改配置文件
/store_v5/src/cn/itcast/store/utils/JedisUtils.java：修改redis数据库服务端位置

/store_v5/src/c3p0-config.xml：修改mysql数据库服务端配置

2. 利用eclipse导出store_v5.war包，并导到linux服务器中

3. 在服务器的mysql下创建store_07数据库，运行store_07.sql中的命令

4. 将store_v5.war放到tomcat的webapps下即可，或者在tomcat页面中部署该war包也行


前端：http://host/store_v5/

后台：http://host/store_v5/admin/



