import 'package:flutter/material.dart';
import 'package:flutter_bbs/post_deatil/model/bean/comment.dart';
import 'package:flutter_bbs/post_deatil/model/bean/comment_empty_bean.dart';
import 'package:flutter_bbs/post_deatil/model/bean/comment_title_bean.dart';
import 'package:flutter_bbs/post_deatil/model/bean/divider_bean.dart';
import 'package:flutter_bbs/post_deatil/model/bean/note.dart';
import 'package:flutter_bbs/post_deatil/repo/network_repo.dart';
import 'package:flutter_bbs/post_deatil/view/cell/comment_detail_widget.dart';
import 'package:flutter_bbs/post_deatil/view/cell/comment_empty_widget.dart';
import 'package:flutter_bbs/post_deatil/view/cell/comment_title_widget.dart';
import 'package:flutter_bbs/post_deatil/view/cell/divider_widget.dart';

import 'cell/moment_detail_widget.dart';

class PostDetailPage extends StatefulWidget {
  final Map<String, dynamic> _map;

  PostDetailPage(this._map);

  @override
  State<StatefulWidget> createState() {
    return PostDetailState();
  }
}

class PostDetailState extends State<PostDetailPage> {
  String _noteId;
  List<Object> items = List();

  @override
  void initState() {
    super.initState();
    _noteId = widget._map["noteId"] as String;
    _initData();
  }

  void _initData() {
    NetWorkRepo.getNoteInfo(context, _noteId, (note) {
      setState(() {
        items.insert(0, note);
      });
    });
    NetWorkRepo.getCommentInfo(context, _noteId, (comments) {
      setState(() {
        items.add(DividerBean());

        if (comments.isEmpty) {
          items.add(CommentEmptyBean());
        } else {
          items.add(CommentTitleBean(comments.length));
          items.addAll(comments);
        }
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          leading: BackButton(onPressed: () {}),
          title: Text("帖子详情"),
          centerTitle: true,
        ),
        body: ListView.builder(
          itemCount: items.length,
          itemBuilder: (context, index) {
            return _buildListViewCell(
                items[index]); //根据数据去构造不同的widget填充到ListView中
          },
        ));
  }

  Widget _buildListViewCell(Object object) {
    if (object is Note) {
      return MomentDetailWidget(object, (String content) {
        NetWorkRepo.addComment(context, _noteId, content, (comment) {
          setState(() {
            if (items[items.length - 1] is CommentEmptyBean) {
              items.removeAt(items.length - 1);
              items.add(comment);
            } else {
              items.add(comment);
            }
          });
        });
      });
    } else if (object is Comment) {
      return CommentDetailWidget(object);
    } else if (object is DividerBean) {
      return DividerWidget();
    } else if (object is CommentEmptyBean) {
      return CommentEmptyWidget();
    } else if (object is CommentTitleBean) {
      return CommentTitleWidget(object.commentNum);
    } else {
      return Container();
    }
  }
}
