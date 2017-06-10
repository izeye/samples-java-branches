var ArrayList = Java.type('java.util.ArrayList');
var list = new ArrayList();
list.add('a');
list.add('b');
list.add('c');

for each (var element in list) {
    print(element);
}

var map = new java.util.HashMap();
map.put('foo', 'val1');
map.put('bar', 'val2');

for each (var key in map.keySet()) {
    print(key);
}

for each (var value in map.values()) {
    print(value);
}
