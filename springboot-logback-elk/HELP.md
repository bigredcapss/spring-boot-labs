#目标： 基于SpringBoot使用Logback将日志写入ELK平台

---
### 环境下载

* [Elasticsearch6.3.2](https://www.elastic.co/cn/downloads/past-releases/elasticsearch-6-3-2)
* [Kibana6.3.2](https://www.elastic.co/cn/downloads/past-releases/kibana-6-3-2)
* [Logstash6.3.2](https://www.elastic.co/cn/downloads/past-releases/logstash-6-3-2)
---
### 说明1: Elasticsearch
Elasticsearch6.3.2启动失败解决办法：
启动失败现象：启动ElasticSearch闪退，并显示： “此时不应有 \Java\jdk1.8.0_181\bin\java.exe，，，”
#### 1.检查JDK环境变量配置
> 若为C:\Program Files (x86)\Java\jdk1.8.0_144
> 则改为
> C:\Progra~2\Java\jdk1.8.0_144

> 若为C:\Program Files\Java\jdk1.8.0_144
> 则改为
> C:\Progra~1\Java\jdk1.8.0_144
---
启动报错信息为：
>ElasticsearchException[X-Pack is not supported and Machine Learning is not available for [windows-x86]; you can use the other X-Pack features (unsupported) by setting xpack.ml.enabled: false in elasticsearch.yml]
#### 2.修改elasticsearch.yml
> 在../config/elasticsearch.yml添加如下配置
> xpack.ml.enabled: false
---
### 说明2: Kibana
#### 1.修改../config/kibana.yml
> 将kibana服务的ip和端口放开，配置成服务器ip和端口，测试学习时，可使用localhost
> server.port: 5601
> server.host: "127.0.0.1"
> elasticsearch.url: "http://127.0.0.1:9200"
---
### 说明3: logstash
#### 1.在../config文件夹下新建logstash.conf文件，内容如下：
``` 
    input{
    	# 建立两个不同的输入，将两个输入的日志分别输入到不同的索引中
    	tcp {
    		#模式选择为server
    		mode => "server"
    		#ip和端口根据自己情况填写，端口默认4560,对应项目中logback-spring.xml里appender中的destination
    		host => "127.0.0.1"
    		port => 4560
    		# 设定type以区分每个输入源
    		type => "elk1"
    		#格式为json
    		codec => json_lines
    	}
    	tcp {
    		#模式选择为server
    		mode => "server"
    		host => "127.0.0.1"
    		port => 4561
    		# 设定type以区分每个输入源
    		type => "elk2"
    		#格式为json
    		codec => json_lines
    	}
    	tcp {
    		#模式选择为server
    		mode => "server"
    		host => "127.0.0.1"
    		port => 4562
    		# 设定type以区分每个输入源
    		type => "elk3"
    		#格式为json
    		codec => json_lines
    	}
    	tcp {
    		#模式选择为server
    		mode => "server"
    		host => "127.0.0.1"
    		port => 4563
    		# 设定type以区分每个输入源
    		type => "elk4"
    		#格式为json
    		codec => json_lines
    	}
    }
    filter {
      #过滤器，根据需要填写
    }
    output{
    	# For detail config for elasticsearch as output,
      	# See: https://www.elastic.co/guide/en/logstash/current/plugins-outputs-elasticsearch.html
      	if [type] == "elk1" {
    		elasticsearch{
    			action=>"index"
    			#这里是es的地址，多个es要写成数组的形式
    			hosts=>["127.0.0.1:9200"]
    			#用于kibana过滤，可以填项目名称
    			#index => "springboot-logback-elk-%{+YYYY.MM.dd}"
    			index => "elk1"
    			codec => "json"
    		}
    	}
    	if [type] == "elk2" {
    		elasticsearch{
    			action=>"index"
    			#这里是es的地址，多个es要写成数组的形式
    			hosts=>["127.0.0.1:9200"]
    			#用于kibana过滤，可以填项目名称
    			#index => "springboot-logback-elk-%{+YYYY.MM.dd}"
    			index => "elk2"
    			codec => "json"
    		}
    	}
    	if [type] == "elk3" {
    		elasticsearch{
    			action=>"index"
    			#这里是es的地址，多个es要写成数组的形式
    			hosts=>["127.0.0.1:9200"]
    			#用于kibana过滤，可以填项目名称
    			#index => "springboot-logback-elk-%{+YYYY.MM.dd}"
    			index => "elk3"
    			codec => "json"
    		}
    	}
    	if [type] == "elk4" {
    		elasticsearch{
    			action=>"index"
    			#这里是es的地址，多个es要写成数组的形式
    			hosts=>["127.0.0.1:9200"]
    			#用于kibana过滤，可以填项目名称
    			#index => "springboot-logback-elk-%{+YYYY.MM.dd}"
    			index => "elk4"
    			codec => "json"
    		}
    	}
    	stdout{codec => rubydebug}
    }
```
---
#### 启动服务
先后启动es,kibana之后，进入logstash目录，从命令行启动logstash
> logstash -f ../config/logstash.conf
---
可参考：
* [SpringBoot配置ELK日志分析系统搭建](https://blog.csdn.net/weixin_43184769/article/details/84971532?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control&dist_request_id=1619575962823_09915&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control)
* [Windows环境下，SpringBoot项目集成ELK日志分析平台教程](https://blog.csdn.net/qq_37200262/article/details/114005281)

