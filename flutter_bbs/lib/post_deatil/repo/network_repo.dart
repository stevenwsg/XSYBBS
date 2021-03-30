import 'dart:convert';

import 'package:data_plugin/bmob/bmob_query.dart';
import 'package:data_plugin/bmob/response/bmob_error.dart';
import 'package:data_plugin/utils/dialog_util.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_bbs/post_deatil/model/bean/note.dart';

class NetWorkRepo {
  // 根据帖子Id拉取帖子信息
  static Note getNoteInfo(
      BuildContext context, String noteId, Function(Note note) update) {
    BmobQuery<Note> query = BmobQuery();
    query.queryObject(noteId).then((value) {
      var s = jsonEncode(value.toString());
      Note note = Note.fromJsonMap(value);
      update(note);
    }).catchError((e) {
      showError(context, BmobError.convert(e).error);
    });
  }

  // 根据帖子Id拉取评论信息

}
