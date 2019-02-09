# XSYBBS
## 校园BBS+校园论坛 2018/7/20





图片效果的话，大家可以看看这篇博客 ，https://blog.csdn.net/qq_29375837/article/details/82657976


我把apk下载地址放这块，大家需要的话可以体验体验https://download.csdn.net/download/qq_29375837/10580988


为期一个月的在校实习结束了，7个人一组，组内其他6个人都考研，也不会做Android，我一个人撸完了，说多了都是泪


开发上全是体力劳动，并没有用到高深的技术


主要是业务逻辑处理比较麻烦，发帖，评论，点赞，取消点赞等



## 实现功能：

### 【系统功能】

用户角色划分：


1、普通（游客）用户


是该论坛的一种用户，普通用户以游客身份登录本系统，只具有察看帖子的功能，不能留言。


2、注册用户


具备普通用户所有的能力。并且是已经注册了的用户，可以登录、修改个人信息，并且可以发表留言。


3、版主


具备注册用户所有的能力，其主要权限是对分论坛进行文章管理。


4、管理员


管理员登录以后可以对论坛系统进行管理，包括论坛的基本信息、用户设置，还可以修改管理员密码。



  
  
  
  
用户个人信息管理


1、个人资料查看:查看自己的所有资料。


2、编辑个人资料:修改除帐号密码外的所有资料


3、个人头像，系统默认一个个人头像，并提供12个左右的头像供用户选择，若用户不满意系统自带的头像，可以自己上传头像，头像会被系统按照一定的算法进行压缩以适应论坛的外观和结构，不会产生大的变形。


4、好友管理，删除、添加、编辑用户的好友。


5、短消息管理，收件箱、发件箱、草稿箱中的信息删除、编辑等操作。


6、论坛外观选择，选择自己喜欢的外观类型。







### 版主后台




版主的管理界面除了个人后台的所有功能外，还包括以下的功能：


1、帖子管理：斑竹有权对自己管理范围内的帖子进行删除、编辑操作。主要操作包括：
删除帖子、帖子加精、帖子置顶。


2、用户管理：对违章用户进行惩罚，具体如下：封用户ID，没收用户ID一定时间。


3、版块公告发布：此权限需要管理员允许。






### 管理员后台




管理员后台拥有斑竹后台的所有内容，除此之外还包含以下权限：


1、全论坛用户帐户的删除权限


2、公告发布


3、论坛设置(UBB,HTML支持等)


4、版主设置(添加,更改,废除版主)


5、后台帐户管理(密码修改)


6、版块管理:添加版块,子版块,设置版块斑竹;


编辑版块,子版块,变换斑竹;


删除版块,子版块,同时废除斑竹








## 感谢第三方开源：



 * circleimageview圆形头像：https://github.com/hdodenhof/CircleImageView

 * 腾讯Bugly收集crash：https://bugly.qq.com/v2/

 * Bmob文件存储：https://bmob.cn/


 * 集成环信 XMPP协议实现聊天，加好友：https://www.easemob.com/

 * Toasty美化UI：https://github.com/GrenderG/Toasty

 * 首页BBS轮播图：https://github.com/youth5201314/banner

 * 兼容7.0+的头像选择器：https://github.com/thewyp/AvatarStudio


 * 6.0+权限申请 ：https://github.com/yewei02538/HiPermission


 * Glide图片加载：https://github.com/bumptech/glide


 * 百度地图定位：http://lbsyun.baidu.com/index.php?title=%E9%A6%96%E9%A1%B5
 
  
 * Leakcanary检测内存泄漏：https://github.com/square/leakcanary
 
 






开源之后希望能对大家有所帮助~~~~~~



如果对大家有帮助的话，可以star或者fork一下  (✺ω✺)





# 毕业设计 
## 2018/12/27
### 由于要去实习，所以毕设就选了这个曾经做过的项目进行修改，从而节省时间。
 
 
####   下面是相对master主分支修改的内容：
 
 
 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;  对界面进行不断美化
 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;  对代码不断进行优化
 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;  修改bug
 * 2018/12/27&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;修改关于界面 
 
 * 2018/12/29&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;注销登录后，下次需要重新登录，还需要释放四个fragment资源，避免内存泄漏，发生OOM
 
 * 2018/12/29&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;将修改密码界面从登录界面搬到 个人中心界面
 
 * 2018/12/29&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;解决搜索页面重复加载问题（每次搜索前清空集合）  
 
 * 2019/01/05&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;集成leakcanary检测内存泄漏 

 * 2019/02/05&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;个人主页面 添加 退出系统选项
 
 * 2019/02/09&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;解决内存泄漏问题 主要是因为线程未关闭，利用AsyncTask系统回调来使用后台线程，关闭后台线程
 
 * 后续会陆续进行更新
 * 后续会陆续进行更新
 * 后续会陆续进行更新 
