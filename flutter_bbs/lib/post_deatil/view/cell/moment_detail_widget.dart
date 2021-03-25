import 'package:flutter/material.dart';
import 'package:flutter_bbs/post_deatil/model/bean/moment_detail.dart';

class MomentDetailWidget extends StatelessWidget {
  final MomentDetail momentDetail;

  MomentDetailWidget(this.momentDetail);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(left: 16, top: 8, bottom: 8, right: 16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Text(
            "${momentDetail.userName}  发表了",
            style: TextStyle(fontSize: 16),
          ),
          Text(
            momentDetail.detailContent,
            style: TextStyle(fontSize: 20),
          ),
        ],
      ),
    );
  }
}
