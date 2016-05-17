## 简介
你还在使用手动方式生成Activity?

你还在为刚生成的Activity未加入AndroidManifest.xml导致运行失败而懊悔？ 

你还在手动查找与配置Action及Category？ 

**Android ADT-extensions 来啦!**

主要功能如下：
- 支持Activity/Service/BroadcastReceiver/ContentProvider四大组件向导式生成
- 支持生成的组件自动在AndroidManifest.xml配置及分组（Activity在一起，Service在一起）
- 支持自定义action,category的添加，及排序
- 支持Android组件属性编辑
- **支持[Android-ORM](https://github.com/Jamlng/Android-ORM)**

## 安装
Marketplace client

1. 点击Eclipse菜单栏 “Help->Marketplace…” 打开插件市场
2. 输入 “ADT extensions” 关键字并执行搜索
3. 查找结果列表中的 “Android ADT extensions” 并点击 “Install”按钮进行安装

## 教程
新建Activity/Service/Broadcaster组件

- 选择包,右键菜单中选择“ADT-extensions>New Activity...”
- 在打开的向导页中,选择组件类型,并输入类名, 定制action/category
- 勾选想要生成的方法如onCreate/onReceive等
- 最后点击完成,完成组件的创建
- **如果提示AndroidManifest.xml不同步,可以按F5刷新

新建Provider

- 选择包,右键菜单中选择“ADT-extensions>New Provider”
- 输入authorities信息

新建ORM Provoder

- 选择包含ORM注解的.java文件或所在的package
- 右键菜单中选择“ADT-extensions>New ORM Privoder”
- 输入数据库名及选择需要创建的数据表

## 截图
![screen shotscreen shot](https://marketplace.eclipse.org/sites/default/files/aorm_elcipse_2.png)

## 参考

http://ieclipse.cn/p/adt-extensions
