import 'package:flutter/material.dart';
import 'package:flutter_bbs/post_deatil/model/bean/note.dart';
import 'package:flutter_bbs/post_deatil/repo/network_repo.dart';

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
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("帖子详情"),
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
      return MomentDetailWidget(object);
    }
  }
}
