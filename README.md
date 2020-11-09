SQLite-Encryption
============================

The SQLite-Encryption is a Java based utility which allows you to convert an SQLite Database into an encrypted .sqlite file. Four different encryption algorithms are supported:

	shift cipher
	substitution cipher
	transposition cipher
	
These encryption algorithms are less secure compared to [AES](https://www.sqlite.org/see/doc/trunk/www/readme.wiki), but are more efficient in terms of storage and complexity. 
The AES based encryption has performance issues during bulk DML-based operation. 
It also increased the size of the database by 70%. On the contrary, these basic ciphers provides efficiency at the cost of reduced security.

## How to Compile it

Build script currently generates only solution 

#### 1. Requirements

- [sqlite-jdbc-3.32.3.2.jar](https://github.com/xerial/sqlite-jdbc)

#### 2. Steps

1. [Download snapshot of this repository][SQLite-Encryption], unzip and open it
2. Run 

**Following these steps and building all binaries in their out directory**


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
    cd dbEncrypt
```

use IntelliJ IDEA to open the project

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
