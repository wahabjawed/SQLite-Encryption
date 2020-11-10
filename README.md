SQLite-Encryption
============================

The SQLite-Encryption is a Java based utility which allows you to convert an SQLite Database into an encrypted .sqlite file. Three different encryption algorithms are supported:

``` 
	shift cipher
	substitution cipher
	transposition cipher
``` 

These encryption algorithms are less secure compared to [AES](https://www.sqlite.org/see/doc/trunk/www/readme.wiki), but are more efficient in terms of storage and complexity. 
The AES based encryption has performance issues during bulk DML-based operation. 
It also increases the size of the database by 70%. On the contrary, these basic ciphers provides efficiency at the cost of reduced security.

## How to Compile it


#### 1. Requirements

- [sqlite-jdbc-3.32.3.2](https://github.com/xerial/sqlite-jdbc)
- [org.json](https://mvnrepository.com/artifact/org.json/json/20200518)

It is already included the POM.xml of the project. Run Maven build to download it.

#### 2. Steps

1. [Download snapshot of this repository][SQLite-Encryption], unzip and open it. **use IntelliJ IDEA to open the project**

2. Configuration

You can configure the following parameter in the App.java file

```   
    String DATABASE_NAME = "test.db" //path to the sqlite db
    String CONFIG_FILE_NAME = "table_col_map.json"  //path to the json file which has table column mapping
````

The **table_col_map.json** contains the tables, their primary key and the columns to be encrypted.
You need to update it as per your db schema.

```json
[{
  "tableName": "Customer",
  "primaryKey": "CustomerId",
  "toEncrypt": [
    "FirstName",
    "LastName",
    "Company"
  ]},
  ...
]
```


**run using IntelliJ IDEA**

OR

3. Run in terminal
```   
    javac src/co.techelix/*.java -d classes
    java -cp classes App.java
````

## Issues

[[Back to top]](https://github.com/wahabjawed/SQLite-Encryption#index)

You can report the bugs at the [issue tracker](https://github.com/wahabjawed/SQLite-Encryption/issues)

**OR**

You can [message me](https://www.facebook.com/wahab.jawed) if you can't get it to work. In fact, you should message me anyway.

## Contributing

[[Back to top]](https://github.com/wahabjawed/SQLite-Encryption#index)

This program was developed to secure an SQLite database with multi-lingual data. These basic ciphers were selected to provide weak encryption without impacting performance.
There could be other ciphers that could provide stronger encryption without degrading performance. If you know one, don't be shy to make a Pull request :)

For making contribution:

1. Fork it
2. Clone it

```
    git clone https://github.com/wahabjawed/SQLite-Encryption.git
    cd SQLiteEncrypt
```

**use IntelliJ IDEA to open the project**

### Contributers

[[Back to top]](https://github.com/wahabjawed/SQLite-Encryption#index)

- [@wahabjawed](https://github.com/wahabjawed/)   [visit website](https://www.linkedin.com/in/abdul-wahab-47745163/)

## License

Copyright 2020 Abdul Wahab

Licensed under the MIT License, (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

       https://opensource.org/licenses/MIT

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[[Back to top]](https://github.com/wahabjawed/SQLite-Encryption#index)
