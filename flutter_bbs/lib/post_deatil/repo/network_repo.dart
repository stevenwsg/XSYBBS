import 'package:data_plugin/bmob/bmob_query.dart';
import 'package:data_plugin/bmob/response/bmob_error.dart';
import 'package:data_plugin/utils/dialog_util.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bbs/post_deatil/model/bean/comment.dart';
import 'package:flutter_bbs/post_deatil/model/bean/note.dart';

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
}
