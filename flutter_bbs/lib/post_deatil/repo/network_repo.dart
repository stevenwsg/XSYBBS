import 'package:data_plugin/bmob/bmob_query.dart';
import 'package:data_plugin/bmob/response/bmob_error.dart';
import 'package:data_plugin/utils/dialog_util.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bbs/post_deatil/model/bean/comment.dart';
import 'package:flutter_bbs/post_deatil/model/bean/note.dart';
import 'package:flutter_bbs/post_deatil/utils/momet_bridge.dart';
import 'package:toast/toast.dart';

class NetWorkRepo {
  // 根据帖子Id拉取帖子信息
  static Note getNoteInfo(
      BuildContext context, String noteId, Function(Note note) update) {
    BmobQuery<Note> query = BmobQuery();
    query.queryObject(noteId).then((value) {
      Note note = Note.fromJsonMap(value);
      update(note);
    }).catchError((e) {
      showError(context, BmobError.convert(e).error);
    });
  }

  // 根据帖子Id拉取评论信息
  static List<Comment> getCommentInfo(BuildContext context, String noteId,
      Function(List<Comment> comment) update) {
    BmobQuery<Comment> query = BmobQuery();
    query.addWhereEqualTo("noteid", noteId);
    query.queryObjects().then((value) {
      List<Comment> list = List();
      value.forEach((element) {
        list.add(Comment.fromJsonMap(element));
      });
      update(list);
    }).catchError((e) {
      showError(context, BmobError.convert(e).error);
    });
  }

  // 发表评论
  static void addComment(BuildContext context, String noteId, String content,
      Function(Comment comment) update) async {
    Comment comment = Comment();
    comment.noteid = noteId;
    comment.content = content;

    Map map = await MomentBridge.getUserInfo();
    comment.userid = map[MomentBridge.KEY_USER_ID];
    comment.username = map[MomentBridge.KEY_USER_NAME];

    comment.save().then((value) {
      Toast.show("评论发表成功", context);
      update(comment);
    }).catchError((e) {
      showError(context, BmobError.convert(e).error);
    });
  }
}
