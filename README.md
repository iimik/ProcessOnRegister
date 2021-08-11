# ProcessOnRegister
> **免责声明**：
> 本项目仅做研究学习使用，不可用做商业用途或不法行为，因此造成的所有责任均由使用者负责，开发者不负任何责任。
ProcessOn微信注册

## 下载注册器

* [github](https://github.com/likly/ProcessOnRegister/releases)

## 开启你的表演

```shell
java -jar processon-register.jar {你的邀请链接}
```

### ChromeDriver

运行环境需要安装`ChromeDriver`。

* MAC 

MAC安装`ChromeDriver`可以使用`brew`进行安装：

```shell
brew install chromedriver
```

### 临时邮箱

注册使用了`https://www.linshiyouxiang.net/`作为临时邮箱，请确保能够访问该地址。

支持的邮箱域名：

* linshiyouxiang.net
* iffygame.com
* maileven.com
* smuggroup.com
* chapedia.net
* meantinc.com
* powerencry.com
* worldzipcodes.net
* chapedia.org
* chasefreedomactivate.com
* wellsfargocomcardholders.com

> 如`https://www.linshiyouxiang.net/`的域名邮箱地址池与上述不同，需要更改代码配置。


## 原理解析

### 原理基础

根据**ProcessON**公布的邀新规则，每邀请一个新用户注册并绑定微信，即可获取3张文件数。原文如图所示：

![微信邀新规则](static/images/ProcessOn-wechat-rule.png)



### 流程分析

![ProcessOn微信注册流程](http://assets.processon.com/chart_image/5f6d62fa7d9c08039fb93cee.png)