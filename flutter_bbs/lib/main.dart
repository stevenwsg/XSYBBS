import 'package:data_plugin/bmob/bmob.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bbs/post_deatil/utils/constant.dart';
import 'package:flutter_bbs/post_deatil/view/post_deatil_page.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    /**
     * 非加密方式初始化
     */
    Bmob.init(
        "https://api2.bmob.cn", Constant.BMOB_APP_ID, Constant.BMOB_API_KEY);
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: PostDetailPage(),
    );
  }
}
