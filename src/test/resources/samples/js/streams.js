var list = new java.util.ArrayList();
list.add('ddd2');
list.add('aaa2');
list.add('bbb1');
list.add('aaa1');
list.add('bbb3');
list.add('ccc');
list.add('bbb2');
list.add('ddd1');

list
    .stream()
    .filter(function (element) {
        return element.startsWith("aaa");
    })
    .sorted()
    .forEach(function (element) {
        print(element);
    });
