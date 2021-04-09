import 'dart:convert';
import 'dart:typed_data';

class Utils {
  static Uint8List getProfile(String profile) {
    return Base64Decoder().convert(profile);
  }
}
