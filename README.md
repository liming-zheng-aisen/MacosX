# 什么是DySpring
   [DySpring](https://github.com/1308404897/DySpring)是基于 *spring、spring mvc、 spring boot、spring cloud* 的原理而设置出来，与spring功能类似，restful规范，强调前后端分离，它将会更好的支持nacos注册中心，以及实现服务的伸缩特性 *（动态增减服务器，减的规则：一段时间内流量远远没有达到预期；减的三大约束：1.不能再接受新的请求;2.未完成的事情必须做完；3.删除注册中心的服务信息。增的规则：服务压力达到闸值或智能地根据平时的流量情况预先增加服务）* ，为了更好的治理服务，我将打造出一套服务治理方案，统一管理起来，让DevOPS更为爽的架构，以后不需要整合第三方服务，例如jenkins、k8s等。我们的宗旨是： __不再为技术选型而感到困难，让每一位开发者更关注业务__
# 如何快速使用DySpring

  ### 1.下载源码
  
    https://github.com/1308404897/DySpring
    
  ### 2.使用maven打包安装到本地仓库
  
    mvn install
    
  ### 3.在工程pom中加入依赖(目前最新的版本是（1.0-SNAPSHOT）)
    <dependency>
            <groupId>com.duanya</groupId>
            <artifactId>dyboot</artifactId>
            <version>${dyboot-v}</version>
    </dependency>
    
  ### 4.在程序入口的类上使用@DyBootApplication，并在main方法调用 DyBootApplicationWeb.run(Mian.class);
    例如：
      @DyBootApplication
      public class Mian {
      public static void main(String[] args) {
          DyBootApplicationWeb.run(Mian.class);
     }
     }
     
 ## @DyBootApplication注解的作用
@DyBootApplication作用是启动dyboot的配置文件加载、类加载、上下文初始化，默认配置等功能，它相当于@DyScanner@DyAutoConfiguration的注解组合，如果有需要的话，手动配置扫描的路径，以及启动配置功能

 ## @DyScanner注解的作用
@DyScanner程序启动时配置程序扫描包的位置，参数 packageNames 的数据类型是String数组，可以传入多个值，例如@DyScanner(packageNames = {com.a,com.b})

 ## @DyAutoConfiguration注解的作用
@DyAutoConfiguration是启动dyboot默认配置功能

 ## @DyConfiguration注解的作用
 @DyConfiguration是声明一个配置类，主要作用是告诉dyboot要执行里面的内容，一般与@DyBean结合使用，例如创建一个bean并注册到dyboot的上下文中：
   @DyConfiguration
   public class DyAppConfig{
      @DyBean
      public Student initStudent(){
       return new Student();
      }
   }
   
 ## @DyBean注解的作用
 声明一个bean，只能作用与方法上面以及必须与@DyConfiguration一起使用，参数只要一个，value代表bean的标识名字，默认情况下是当前类的父类名字，如果父类是Object则value就是当前类的名字

 ## @DyValue注解的作用
 只能作用与类的属性上面，其作用是将配置文件的属性值注入到类的属性中，可以设置默认值，其表达式为@DyValue("${配置文件对应的属性}:默认值")

 ## @DyComponent注解的作用（单例）
 能在类上面使用，它的作用是告诉dyboot创建当前类的对象，并注册到DySpringApplicationContent上下文中
 
 ## @DyService注解的作用（单例）
 与@DyComponent作用一致，只是用来标识业务层
  
 ## @DyRestController注解的作用（多例）
 与@DyComponent作用类似，但不同的是它是prototype以及它并不是注册到DySpringApplicationContent是注册在mvc的DyServletContext上下文中
 
 ## @DyRequestMapping注解的作用
 以作用在类上也可以作用在方法上，主要作用是映射请求的路径，它可以支持多种请求，参数有两个，第一个参数是value代表请求的路径，默认情况下是"/" ，第二个是method枚举类型的参数代表请求的方式，默认为DyMethod.GET，也就是GET请求
  
 ## @DyGet注解的作用
 只能作用于方法上面，代表get请求，相当于@DyRequestMapping(value="/",method=DyMethod.GET),但它只要一个参数value，代表请求的路径
 
 ## @DyPost注解的作用
 与 @DyGet作用一样，代表post请求
 
 ## @DyPut注解的作用
 与 @DyGet作用一样，代表put请求
  
 ## @DyDelete 注解的作用
 与 @DyGet作用一样，代表delete请求
 
 ## @DyPathVariable注解的作用
 只能作用于方法的参数上面，其作用为获取路径最后的"/"的值为参数值，例如：
   @DyGet("/{str}")
   public String getStr（@DyPathVariable String str){
    return str;
   }
  
  ## @DyRequestParameter注解的作用
  只能作用于方法的参数上面，其作用是获取请求路径的参数注入到方法的参数列表上面，参数列表如下:
  字段名 | 作用 |
  ______|______|
  value | 对应的参数名称
  defaultValue | 默认值
  request | 是否必须的，默认为true
  doc | 参数说明
  
 
 
 
