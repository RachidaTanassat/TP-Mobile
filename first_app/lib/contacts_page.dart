import 'package:flutter/material.dart';

class ContactsPage extends StatefulWidget {
  @override
  State<ContactsPage> createState() => _ContactPageState();
}

class _ContactPageState extends State<ContactsPage> {
  List<String> contact = [];
  TextEditingController textController = TextEditingController();

  void addToList() {
    setState(() {
      contact.add(textController.text);
      textController.clear();
    });
  }

  void deleteItem(int index) {
    setState(() {
      contact.removeAt(index);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Contact"),
      ),
      body: Padding(
        padding: EdgeInsets.all(10),
        child: Column(
          children: [
            TextField(
              controller: textController,
              decoration: InputDecoration(
                enabledBorder: OutlineInputBorder(
                  borderSide: BorderSide(
                    color: Colors.blueAccent,
                    width: 1,
                  ),
                  borderRadius: BorderRadius.all(Radius.circular(15)),
                ),
                focusedBorder: OutlineInputBorder(
                  borderSide: BorderSide(
                    color: Colors.amber,
                    width: 1,
                  ),
                  borderRadius: BorderRadius.all(Radius.circular(15)),
                ),
              ),
            ),
            MaterialButton(
              onPressed: addToList,
              child: Text("Ajouter"),
              color: Colors.blueAccent[100],
            ),
            Expanded(
              child: ListView.builder(
                itemCount: contact.length,
                itemBuilder: (context, index) {
                  return Card(
                    child: ListTile(
                      title: Text(contact[index]),
                      leading: CircleAvatar(
                        child: Text(contact[index].substring(0, 1).toUpperCase()),
                      ),
                      trailing: IconButton(
                        icon: Icon(Icons.delete),
                        onPressed: () {
                          deleteItem(index);
                        },
                      ),
                    ),
                    elevation: 5,
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
