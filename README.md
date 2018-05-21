# SimpleORM
简单实现对象和SQL自动映射的ORM数据层框架
## 支持对象和SQL自动映射(表需有主键)，使用自写的粗糙数据库连接池，功能简单，可供给想自己封装框架的同学做参考交流渠道，代码内有详细注释，api目录内有接口文档
### 基本架构图
![](https://github.com/tongweixu/SimpleORM/raw/master/doc/架构图.PNG)  
#### 基本调用过程
调用QueryFactory,根据配置信息，工厂模式返回不同数据库对应的Query对象，Query对象为对象到SQL映射的实现，Query执行方法时会调用DBManager的静态方法，DBManager持有Configuration对象的引用，该对象为配置文件的配置信息，包括数据库类型，url,连接池连接数等等，返回数据库连接，同时Query对象会调用TableContext实现数据库表到java对象的关联，TableContext静态代码块执行中，会初始化获得表的信息，更新类结构，加载po包下面所有的类，便于重用，提高效率！
同时Query会根据TypeConvertorFactory拿到不同的数据库对应的数据库数据类型到java类型的数据类型转换类。更多请看代码内注释。
