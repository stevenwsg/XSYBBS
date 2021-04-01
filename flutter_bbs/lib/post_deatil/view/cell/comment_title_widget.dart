import 'package:flutter/material.dart';

class CommentTitleWidget extends StatelessWidget {
  final int commentNum;

  CommentTitleWidget(this.commentNum);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(left: 20, top: 20, bottom: 10),
      child: Text(
        "评论  $commentNum",
        style: TextStyle(fontSize: 18),
      ),
    );
  }
}
