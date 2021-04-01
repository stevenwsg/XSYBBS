import 'package:flutter/material.dart';

class DividerWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(left: 20, right: 20, top: 10),
      child: Divider(
        height: 1,
        thickness: 1,
        color: Colors.black12,
      ),
    );
  }
}
