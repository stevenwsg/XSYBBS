import 'package:flutter/services.dart';

class MomentBridge {
  static const String BRIDGE_NAME = "flutter.bbs/moment";

  static const String METHOD_GET_USER_INFO = "getUserInfo";
  static const String KEY_USER_ID = "key_user_id";
  static const String KEY_USER_NAME = "key_user_name";

  static const _methodChannel = const MethodChannel(BRIDGE_NAME);

  static Future<Map> getUserInfo() async {
    try {
      Map res = await _methodChannel.invokeMethod(METHOD_GET_USER_INFO);
      print("getUserInfo suc" + res.toString());
      return res;
    } catch (e) {
      print("getUserInfo error" + e.toString());
    }
    return Map();
  }
}
