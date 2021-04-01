import 'package:data_plugin/bmob/table/bmob_object.dart';

class Comment extends BmobObject {
  String content;
  String createdAt;
  String noteid;
  String objectId;
  String updatedAt;
  String userid;
  String username;

  Comment.fromJsonMap(Map<String, dynamic> map)
      : content = map["content"],
        createdAt = map["createdAt"],
        noteid = map["noteid"],
        objectId = map["objectId"],
        updatedAt = map["updatedAt"],
        userid = map["userid"],
        username = map["username"];

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['content'] = content;
    data['createdAt'] = createdAt;
    data['noteid'] = noteid;
    data['objectId'] = objectId;
    data['updatedAt'] = updatedAt;
    data['userid'] = userid;
    data['username'] = username;
    return data;
  }

  @override
  Map getParams() {
    toJson();
  }

  @override
  String toString() {
    return 'Comment{content: $content, createdAt: $createdAt, noteid: $noteid, objectId: $objectId, updatedAt: $updatedAt, userid: $userid, username: $username}';
  }
}
