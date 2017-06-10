var list = new java.util.ArrayList();
list.add("s1");
list.add("s2");
list.add("s3");

var jsArray = Java.from(list);
print(jsArray);
print(Object.prototype.toString.call(jsArray));

var array = [3, 5, 7, 11];
print(Object.prototype.toString.call(array));
var javaArray = Java.to(array, "int[]");
print(Object.prototype.toString.call(javaArray));
