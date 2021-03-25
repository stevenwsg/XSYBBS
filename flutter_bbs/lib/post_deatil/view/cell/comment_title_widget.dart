import 'package:flutter/material.dart';

class CommentTitleWidget extends StatelessWidget {
  final int commentNum;

  CommentTitleWidget(this.commentNum);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(
        left: 16,
        top: 16,
      ),
      child: Text("评论  $commentNum"),
    );
  }
}
