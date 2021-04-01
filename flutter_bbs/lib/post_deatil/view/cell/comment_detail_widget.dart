import 'package:flutter/material.dart';
import 'package:flutter_bbs/post_deatil/model/bean/comment.dart';

class CommentDetailWidget extends StatelessWidget {
  final Comment comment;

  CommentDetailWidget(this.comment);

  @override
  Widget build(BuildContext context) {
    return Container(
        margin: EdgeInsets.only(left: 20, top: 8, bottom: 8, right: 20),
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text(
              comment.username + " : ",
              style: TextStyle(fontSize: 16, color: Colors.blue),
            ),
            Expanded(
                child: Text(
              comment.content,
              style: TextStyle(fontSize: 16),
              maxLines: 1,
              overflow: TextOverflow.ellipsis,
            )),
          ],
        ));
  }
}
