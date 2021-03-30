import 'package:data_plugin/bmob/table/bmob_object.dart';

class Note extends BmobObject {
  String content;
  String createdAt;
  String objectId;
  int replaycount;
  String title;
  int top;
  String typeid;
  String updatedAt;
  String userid;
  int zancount;

  Note.fromJsonMap(Map<String, dynamic> map)
      : content = map["content"],
        createdAt = map["createdAt"],
        objectId = map["objectId"],
        replaycount = map["replaycount"],
        title = map["title"],
        top = map["top"],
        typeid = map["typeid"],
        updatedAt = map["updatedAt"],
        userid = map["userid"],
        zancount = map["zancount"];

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['content'] = content;
    data['createdAt'] = createdAt;
    data['objectId'] = objectId;
    data['replaycount'] = replaycount;
    data['title'] = title;
    data['top'] = top;
    data['typeid'] = typeid;
    data['updatedAt'] = updatedAt;
    data['userid'] = userid;
    data['zancount'] = zancount;
    return data;
  }

  @override
  Map getParams() {
    toJson();
  }

  @override
  String toString() {
    return 'Note{content: $content, createdAt: $createdAt, objectId: $objectId, replaycount: $replaycount, title: $title, top: $top, typeid: $typeid, updatedAt: $updatedAt, userid: $userid, zancount: $zancount}';
  }
}
