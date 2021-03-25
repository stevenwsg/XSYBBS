import 'package:flutter/material.dart';

class CommentEmptyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: 40, bottom: 40),
      child: Center(
        child: Text("评论内容为空，赶紧发表条评论吧"),
      ),
    );
  }
}
