import 'package:flutter/material.dart';

class PostDetailPage extends StatefulWidget {
  final Map<String, String> _map;

  PostDetailPage(this._map);

  @override
  State<StatefulWidget> createState() {
    return PostDetailState();
  }
}

class PostDetailState extends State {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("帖子详情"),
      ),
      body: Center(
        child: Text(
          "帖子详情",
          style: TextStyle(fontSize: 20, color: Colors.blueAccent),
        ),
      ),
    );
  }
}
