# Getting Started01

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

192.168.1.14
           账号：root
           密码：crain123

           mysql： 
           账号：root 
           密码：123 
           版本：5.7.11（社区版） 
           路径：/usr/local/mysql

           nginx: 
           版本：1.8.0 
           路径：/usr/local/nginx

           tomcat: 
           端口：8080 
           版本：7.0.94 
           路径：/usr/local/tomcat

           redis: 
           端口：6379 
           密码：123456 
           版本：5.0.4 
           路径：/usr/local/redis

192.168.1.148 
          账号：root
          密码：root

          nginx: 
          版本：1.8.0 
          路径：/usr/local/nginx

          tomcat: 
          端口：8080 
          版本：7.0.94 
          路径：/usr/local/tomcat

# Getting Started02

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

===maven lifecycle 与 plugin
https://blog.csdn.net/himdarkless/article/details/81985832

lifecycle
maven的生命周期分为3种

1.default
2.clean
3.site
大概流程如图所示



其中比较重要的几个步骤有以下几个

1.clean
用于清除之前构建生成的所有文件

其中具体为清楚了Target目录中的所有文件，包括该目录

i.e：删除了install生成的所有文件

2.validate
用于验证项目是否真确，并且其说有必要信息是否都可用

3.compile
编译项目的源代码，主要是java文件

一般是编译scr/main/java或是scr/test/java里面的文件

4.test
用合适的测试框架来进行测试，测试compile中编译出来的代码

测试的东西一般不加包和部署

5.packaging
获取compile中编译好的代码并将其打包成可分类的格式，i.e:JAR

6.vertify
这步是用来验证test

检查test的结果是否满足标准

7.install
将软件包安装到本地存储库中

确保本地其他项目可能需要使用他（eg:装了core才能用oms）

9.deploy
复制最终的包至远程仓库

共享给其它开发人员和项目



PS:在install的时候可能会出现乱码，此时对着install点右键，选择create xxx install

      在command line里写 install -Dmaven.test.skip=true -f pom.xml 然后用新写的命令代替旧的install即可

 

plugin
<dependency>
帮组分析项目依赖

依赖就是在maven里面要用哪个包就在<denpendency>标签里面写东西

一般不用自己写

可以在google里面搜索“maven xxx repository”

或者直接在http://mvnrepository.com/里面搜索xxx

<resources>
将资源文件过滤

resources用来处理资源

compiler用来编译java文件

<jetty>
快速在web上部署

进行调试的时候比较方便和节省时间

<build>
可以分为

<project build>全局配置：为全局有效

<profile build>配置：为针对不同的profile配置

build里面有<resource>和<plugin>两种标签

他们都是把一些默认方法放在其他文件路径的文件放到“src/main/java”里面

<packaging>
打包方式主要有JAR和WAR两种

其中JAR用于比较小的项目，好处为不用依赖包，因为他把应用依赖的所有依赖包和程序打包在一个全量包里，他说packaging的默认方式

WAR适用于需要部署的项目

<scope>
适用范围主要分为test和provided两种

test对测试范围有效

provided对编译和测试过程都有效

 

PS：

1.匹配符**可以匹配路径，*只能匹配名字

2.如果启动失败先看错误信息

3.jetty：run要create一个再运行，不用直接运行，因为直接运行可能会调用到了其他人的profiles

4.运行maven之前先看一块profile的配置环境有没有勾选错别人的环境
