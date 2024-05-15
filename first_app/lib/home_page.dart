
import 'package:first_app/contacts_page.dart';
import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  const HomePage();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Center(
          child: Text("Home Page",
            style: TextStyle(fontSize: 25),),
        )),
        drawer: Drawer(
          child: ListView(
            children: [
              DrawerHeader(child: CircleAvatar(
                backgroundImage: AssetImage("assets/photo.jpeg"),
               // backgroundImage: NetworkImage(
                //    "data:image/jpeg;base64,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"),
                //radius: 200,
              )),
              ListTile(
                title: Text("Home", style: TextStyle(fontSize: 24),),
                onTap: () {},
                leading: Icon(Icons.home),
              ),
              ListTile(
                title: Text("Contacts", style: TextStyle(fontSize: 24),),
                onTap: () {
                  Navigator.push(context, MaterialPageRoute(builder: (context)=>ContactsPage()));
                },
                leading: Icon(Icons.contacts),
              ),
              ListTile(
                title: Text("Meteo", style: TextStyle(fontSize: 24),),
                onTap: () {},
                leading: Icon(Icons.ac_unit_sharp),
              ),
              ListTile(
                title: Text("ChatBot", style: TextStyle(fontSize: 24),),
                onTap: () {},
                leading: Icon(Icons.adb),
              ),
              ListTile(
                title: Text("Mask Detection", style: TextStyle(fontSize: 24),),
                onTap: () {},
                leading: Icon(Icons.add_a_photo_sharp),
              )
            ],
          ),
        )
    );
  }
}