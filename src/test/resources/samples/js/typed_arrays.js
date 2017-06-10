var IntArray = Java.type("int[]");

var array = new IntArray(5);
array[0] = 5;
array[1] = 4;
array[2] = 3;
array[3] = 2;
array[4] = 1;

try {
    array[5] = 23;
}
catch (ex) {
    print(ex.message);
}

array[0] = "17";
print(array[0])

array[0] = "wrong type";
print(array[0])
