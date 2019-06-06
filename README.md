#访客管理系统
本项目为本科毕业设计，后端采用SpringBoot框架，代码在[webvisit-os](https://github.com/StrangerNing/webvisit/tree/master/webvisit-os)内；
前端采用Vue.js框架，代码在[webvisit-fe](https://github.com/StrangerNing/webvisit/tree/master/webvisit-fe)内，
使用Vue-element-admin模板，模板代码前往[原作者仓库](http://panjiachen.github.io/vue-element-admin)。

##项目预览
请前往[预览地址](http://www.znzn.me)。

##项目依赖

- MySQL数据库，数据库建表请见[SQL建表文件](https://github.com/StrangerNing/webvisit/tree/master/webvisit-os/sql)。
- Redis服务器。
- FastDFS文件服务器。
- [node](http://nodejs.org/)。

请在[application.yml](https://github.com/StrangerNing/webvisit/blob/master/webvisit-os/src/main/resources/application.yml)中配置数据库地址、Redis服务器地址、FastDFS服务器地址。

需要手动在数据库内插入新用户、公司、部门等，用户密码请用[加密工具](https://github.com/StrangerNing/webvisit/blob/master/webvisit-os/src/main/java/com/webvisit/utils/MD5Util.java)先生成加密密码插入数据库。

后续可能会写好相关页面，但毕设中需求没有这个内容，所以先略过。

##开发
前端开发过程：
```bash
#进入项目目录
cd webvisit-fe

#安装依赖
npm install

#启动项目
npm run dev
```
一般来讲启动完成后会自动打开浏览器，若没有，在地址栏输入 http://localhost

## 发布

```bash
# 构建测试环境
npm run build:stage

# 构建生产环境
npm run build:prod
```

