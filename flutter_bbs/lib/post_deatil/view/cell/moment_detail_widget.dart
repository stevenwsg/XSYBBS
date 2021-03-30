import 'package:flutter/material.dart';
import 'package:flutter_bbs/post_deatil/model/bean/note.dart';

class MomentDetailWidget extends StatelessWidget {
  final Note note;

  MomentDetailWidget(this.note);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(left: 20, top: 10, bottom: 10, right: 20),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          _buildHeaderWidget(),
          _buildContentWidget(),
          _buildIconWidget(),
          _buildReplayWidget(),
        ],
      ),
    );
  }

  Widget _buildHeaderWidget() {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Container(
          margin: EdgeInsets.only(right: 10),
          child: ClipOval(
            child: Image.asset(
              "images/logo.webp",
              width: 80,
              height: 80,
            ),
          ),
        ),
        Expanded(
            child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              note.title ?? "",
              style: TextStyle(color: Colors.black54, fontSize: 20),
              maxLines: 1,
              overflow: TextOverflow.ellipsis,
            ),
            Container(
              margin: EdgeInsets.only(top: 20),
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    note.typeid ?? "",
                    style: TextStyle(color: Colors.black45, fontSize: 16),
                  ),
                  Expanded(child: Container()),
                  Text(
                    note.updatedAt?.substring(0, 10) ?? "",
                    style: TextStyle(color: Colors.black45, fontSize: 16),
                  )
                ],
              ),
            ),
          ],
        )),
      ],
    );
  }

  Widget _buildContentWidget() {
    return Container(
      margin: EdgeInsets.only(top: 10),
      child: Expanded(
        child: Text(
          note.content ?? "",
          style: TextStyle(color: Colors.black54, fontSize: 16),
        ),
      ),
    );
  }

  Widget _buildIconWidget() {
    return Container(
      margin: EdgeInsets.only(top: 20),
      child: Flex(
        direction: Axis.horizontal,
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: [
          Row(
            children: [
              Image.asset(
                "images/zan.webp",
                width: 20,
                height: 20,
              ),
              Container(
                margin: EdgeInsets.only(left: 5),
                child: Text(
                  note.zancount?.toString() ?? "",
                  style: TextStyle(fontSize: 14),
                ),
              )
            ],
          ),
          Row(
            children: [
              Image.asset(
                "images/replay.webp",
                width: 20,
                height: 20,
              ),
              Container(
                margin: EdgeInsets.only(left: 5),
                child: Text(
                  note.replaycount?.toString() ?? "",
                  style: TextStyle(fontSize: 14),
                ),
              )
            ],
          )
        ],
      ),
    );
  }

  Widget _buildReplayWidget() {
    return Container(
      margin: EdgeInsets.only(
        top: 20,
      ),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Expanded(
              child: TextField(
            decoration: InputDecoration(
              hintText: ' 开始你的评论吧',
              hintStyle: TextStyle(fontFamily: 'MaterialIcons', fontSize: 16),
              contentPadding: EdgeInsets.only(top: 8, bottom: 8),
              border: OutlineInputBorder(
                borderSide: BorderSide.none,
                borderRadius: BorderRadius.all(Radius.circular(5)),
              ),
              filled: true,
            ),
          )),
          Container(
            margin: EdgeInsets.only(left: 20),
            child: OutlinedButton(
              onPressed: () {},
              child: Text("评论"),
            ),
          )
        ],
      ),
    );
  }
}
