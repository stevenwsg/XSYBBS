import 'package:flutter/material.dart';
import 'package:flutter_bbs/post_deatil/model/bean/comment_detail.dart';

class CommentDetailWidget extends StatelessWidget {
  final CommentDetail commentDetail;

  CommentDetailWidget(this.commentDetail);

  @override
  Widget build(BuildContext context) {
    return Container(
        margin: EdgeInsets.only(left: 16, top: 8, bottom: 8, right: 16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Text(
              commentDetail.userName,
              style: TextStyle(fontSize: 16, color: Colors.blue),
            ),
            Text(
              commentDetail.commentContent,
              style: TextStyle(fontSize: 20),
            ),
          ],
        ));
  }
}
